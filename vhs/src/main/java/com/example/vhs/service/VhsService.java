package com.example.vhs.service;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.User;
import com.example.vhs.entity.Vhs;
import com.example.vhs.repository.VhsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class VhsService implements IVhsService{

    public final VhsRepository vhsRepository;
    public RentalService rentalService;

    @Value("${api.rental.rentalPeriodInDays}")
    public int rentalPeriodInDays;

    public VhsService(VhsRepository vhsRepository) {
        this.vhsRepository = vhsRepository;
    }

    @Autowired
    public void setRentalService(@Lazy RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public RentalService getRentalService() {
        return rentalService;
    }

    @Override
    public List<Vhs> getAll() {
        return vhsRepository.findAll();
    }

    @Override
    public Vhs getByVhsId(int vhsId) {
        return vhsRepository.findByVhsId(vhsId);
    }

    @Override
    public Vhs create(Vhs vhs) {
        vhs.setVhsId(0);
        return vhsRepository.save(vhs);
    }

    @Override
    public Vhs update(Vhs vhs) {
        if(this.getByVhsId(vhs.getVhsId()) != null) {
            return vhsRepository.save(vhs);
        } else {
            return new Vhs();
        }
    }

    @Override
    public void delete(int vhsId) {
        vhsRepository.deleteById(vhsId);
    }

    @Override
    public List<Vhs> getAllAvailable() {
        return vhsRepository.findByAvailableTrue();
    }

    @Override
    public Vhs setAvailable(int vhsId) {
        Vhs vhs = this.getByVhsId(vhsId);
        vhs.setAvailable(true);
        return vhsRepository.save(vhs);
    }

    @Override
    public Vhs setUnavailable(int vhsId) {
        Vhs vhs = this.getByVhsId(vhsId);
        vhs.setAvailable(false);
        return vhsRepository.save(vhs);
    }

    @Override
    public Rental rentVhs(User user, int vhsId) {
        //Make sure that the same vhs can't have multiple rentals on the same date
        Vhs vhs = this.getByVhsId(vhsId);
        if(vhs.isAvailable()) {
            this.setUnavailable(vhsId);
            Rental rental = new Rental();
            rental.setRentalUser(user);
            rental.setRentalVhs(vhs);
            rental.setRentalStart(new Date());
            Calendar endDate = Calendar.getInstance();
            endDate.add(Calendar.DATE, rentalPeriodInDays);
            rental.setRentalEnd(endDate.getTime());
            return this.getRentalService().create(rental);
        } else {
            return new Rental();
        }
    }
}
