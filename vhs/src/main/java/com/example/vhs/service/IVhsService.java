package com.example.vhs.service;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.User;
import com.example.vhs.entity.Vhs;

import java.util.List;

public interface IVhsService {
    List<Vhs> getAll();
    Vhs getByVhsId(int vhsId);
    Vhs create(Vhs vhs);
    Vhs update(Vhs vhs);
    void delete(int vhsId);
    List<Vhs> getAllAvailable();
    Vhs setAvailable(int vhsId);
    Vhs setUnavailable(int vhsId);
    Rental rentVhs(User user, int vhsId);
}
