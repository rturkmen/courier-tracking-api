package com.migros.courier.persistence.repository;

import com.migros.courier.persistence.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findStoreByNameAndLatAndLng(String name, Double lat, Double lng);
}
