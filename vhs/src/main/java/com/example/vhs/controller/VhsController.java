package com.example.vhs.controller;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.User;
import com.example.vhs.entity.Vhs;
import com.example.vhs.service.VhsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vhs")
@Slf4j
public class VhsController {

    public final VhsService vhsService;

    public VhsController(VhsService vhsService) {
        this.vhsService = vhsService;
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Vhs>> getAllVhs() {
        log.info("GET api/vhs/getAll");
        return ResponseEntity.ok(vhsService.getAll());
    }

    @GetMapping(value = "/{vhsId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Vhs> getById(@PathVariable int vhsId) {
        log.info("GET api/vhs/" + vhsId);
        return ResponseEntity.ok(vhsService.getByVhsId(vhsId));
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Vhs> createVhs(@Valid @RequestBody Vhs vhs) {
        log.info("POST api/vhs - " + vhs.toString());
        return ResponseEntity.ok(vhsService.create(vhs));
    }

    @PutMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Vhs> updateVhs(@Valid @RequestBody Vhs vhs) {
        log.info("PUT api/vhs - " + vhs.toString());

        return ResponseEntity.ok(vhsService.update(vhs));
    }

    @DeleteMapping(value = "{vhsId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteVhsById(@PathVariable int vhsId) {
        log.info("DELETE api/vhs/" + vhsId);
        vhsService.delete(vhsId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAllAvailable")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Vhs>> getAllAvailableVhs() {
        log.info("GET api/vhs/getAllAvailable");
        return ResponseEntity.ok(vhsService.getAllAvailable());
    }

    @PostMapping(value = "/rentVhs/{vhsId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Rental> rentVhs(@Valid @RequestBody User user, @PathVariable int vhsId) {
        log.info("GET api/vhs/rentVhs/" + vhsId + " - " + user.toString());
        return ResponseEntity.ok(vhsService.rentVhs(user, vhsId));
    }
}