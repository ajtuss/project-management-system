package com.mycompany.repositories;

import com.mycompany.domain.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemRepository extends JpaRepository<System, Long> {

    Optional<System> findByNameIgnoreCase(String systemName);

    boolean existsByName(String nameValue);
}

