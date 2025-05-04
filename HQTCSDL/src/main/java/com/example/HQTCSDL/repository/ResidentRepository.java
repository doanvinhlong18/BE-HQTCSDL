package com.example.HQTCSDL.repository;

import com.example.HQTCSDL.Entity.ResidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository<ResidentEntity, Integer> {
    Optional<ResidentEntity> findByName(String name);

    Optional<ResidentEntity> findByIdNumber(String idNumber);
}
