package com.mycompany.repositories;

import com.mycompany.domain.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

    @Query("SELECT a FROM Agreement a WHERE a.active = TRUE ")
    List<Agreement> findAllActive();

    Agreement findByOrderNumber(String orderNumber);
}
