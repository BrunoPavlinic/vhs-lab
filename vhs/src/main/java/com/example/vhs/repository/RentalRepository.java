package com.example.vhs.repository;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    Rental findByRentalId(int rentalId);
    List<Rental> findAllByRentalUser(User user);
    List<Rental> findAllByRentalUserAndRentalReturnedIsNull(User user);
    List<Rental> findAllByRentalUserAndRentalReturnedIsNotNull(User user);
}
