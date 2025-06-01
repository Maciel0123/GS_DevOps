package com.clima.gs.gs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clima.gs.gs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}