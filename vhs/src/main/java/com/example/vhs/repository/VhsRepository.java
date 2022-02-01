package com.example.vhs.repository;

import com.example.vhs.entity.Vhs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VhsRepository extends JpaRepository<Vhs, Integer> {
    Vhs findByVhsId(int vhsId);
    List<Vhs> findByAvailableTrue();
}