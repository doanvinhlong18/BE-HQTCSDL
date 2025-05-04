package com.example.HQTCSDL.repository;

import com.example.HQTCSDL.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(@Param("email") String email);
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
