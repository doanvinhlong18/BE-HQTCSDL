package com.example.HQTCSDL.repository;

import com.example.HQTCSDL.Entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
}
