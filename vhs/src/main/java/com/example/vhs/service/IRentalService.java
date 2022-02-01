package com.example.vhs.service;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.User;

import java.util.List;
import java.util.Optional;

public interface IRentalService {
    List<Rental> getAll();
    Rental getByRentalId(int rentalId);
    Rental create(Rental rental);
    Rental update(Rental rental);
    void delete(int rentalId);
    List<Rental> getAllByUser(int userId);
    List<Rental> getAllActiveByUser(int userId);
    List<Rental> getAllReturnedByUser(int userId);
    Rental returnRental(int rentalId);
}
