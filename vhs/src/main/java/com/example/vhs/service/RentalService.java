package com.example.vhs.service;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.User;
import com.example.vhs.entity.Vhs;
import com.example.vhs.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RentalService implements IRentalService{

    public final RentalRepository rentalRepository;
    public VhsService vhsService;

    @Value("${api.rental.dailyLateReturnFee}")
    public int dailyLateReturnFee;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Autowired
    public void setVhsService(@Lazy VhsService vhsService) {
        this.vhsService = vhsService;
    }

    public VhsService getVhsService() {
        return vhsService;
    }


    @Override
    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getByRentalId(int rentalId) {
        return rentalRepository.findByRentalId(rentalId);
    }

    @Override
    public Rental create(Rental rental) {
        rental.setRentalId(0);
        Rental newRental =  rentalRepository.save(rental);
        return getByRentalId(newRental.getRentalId());
    }

    @Override
    public Rental update(Rental rental) {
        if(this.getByRentalId(rental.getRentalId()) != null) {
            return rentalRepository.save(rental);
        } else {
            return new Rental();
        }
    }

    @Override
    public void delete(int rentalId) {
        rentalRepository.deleteById(rentalId);
    }

    @Override
    public List<Rental> getAllByUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        return rentalRepository.findAllByRentalUser(user);
    }

    @Override
    public List<Rental> getAllActiveByUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        return rentalRepository.findAllByRentalUserAndRentalReturnedIsNull(user);
    }

    @Override
    public List<Rental> getAllReturnedByUser(int userId) {
        User user = new User();
        user.setUserId(userId);
        return rentalRepository.findAllByRentalUserAndRentalReturnedIsNotNull(user);
    }

    @Override
    public Rental returnRental(int rentalId) {
        Rental rental = rentalRepository.findByRentalId(rentalId);
        if(rental.getRentalReturned() == null) {
            rental.setRentalReturned(new Date());
            //here, we want to calculate the fee that the user is required to pay when he returns the rental
            //and return the fee information with the Rental entity
            //Fee formula = 1 late day => 5 USD (as defined in the RentalConstants class)
            int daysLate = (int)Duration.between(rental.getRentalEnd().toInstant(), rental.getRentalReturned().toInstant()).toDays();
            if(daysLate > 0) {
                rental.setFee(daysLate * dailyLateReturnFee);
            }

            //make the vhs that is returned available again
            this.getVhsService().setAvailable(rental.getRentalVhs().getVhsId());

            return rentalRepository.save(rental);
        } else {
            return new Rental();
        }
    }
}
