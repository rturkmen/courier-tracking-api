package com.migros.courier.persistence.repository;

import com.migros.courier.persistence.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier, Long> {

    Optional<Courier> findByIdentityNumber(Long identityNumber);
}
