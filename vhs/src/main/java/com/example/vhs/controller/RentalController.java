package com.example.vhs.controller;

import com.example.vhs.entity.Rental;
import com.example.vhs.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
@Slf4j
public class RentalController {

    public final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Rental>> getAll() {
        log.info("GET api/rental getAll");
        return ResponseEntity.ok(rentalService.getAll());
    }

    @GetMapping(value = "/{rentalId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Rental> getById(@PathVariable int rentalId) {
        log.info("GET api/rental/" + rentalId);
        return ResponseEntity.ok(rentalService.getByRentalId(rentalId));
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) {
        log.info("POST api/rental - " + rental.toString());
        return ResponseEntity.ok(rentalService.create(rental));
    }

    @PutMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Rental> updateRental(@RequestBody Rental rental) {
        log.info("PUT api/rental - " + rental.toString());
        return ResponseEntity.ok(rentalService.update(rental));
    }

    @DeleteMapping(value = "{rentalId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRentalById(@PathVariable int rentalId) {
        log.info("DELETE api/rental/ - " + rentalId);
        rentalService.delete(rentalId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAllByUser/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Rental>> getAllByUserId(@PathVariable int userId) {
        log.info("GET api/rental/getAllByUser/ - " + userId);
        return ResponseEntity.ok(rentalService.getAllByUser(userId));
    }

    @GetMapping(value = "/getAllActiveByUser/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Rental>> getAllActiveByUserId(@PathVariable int userId) {
        log.info("GET api/rental/getAllActiveByUser/ - " + userId);
        return ResponseEntity.ok(rentalService.getAllActiveByUser(userId));
    }

    @GetMapping(value = "/getAllReturnedByUser/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Rental>> getAllReturnedByUserId(@PathVariable int userId) {
        log.info("GET api/rental/getAllReturnedByUser/ - " + userId);
        return ResponseEntity.ok(rentalService.getAllReturnedByUser(userId));
    }

    @PutMapping(value = "/return/{rentalId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Rental> returnRental (@PathVariable int rentalId) {
        log.info("PUT api/rental/return/ - " + rentalId);
        return ResponseEntity.ok(rentalService.returnRental(rentalId));
    }

}
