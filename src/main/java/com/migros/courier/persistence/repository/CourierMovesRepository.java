package com.migros.courier.persistence.repository;

import com.migros.courier.persistence.entity.Courier;
import com.migros.courier.persistence.entity.CourierMoves;
import com.migros.courier.persistence.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierMovesRepository extends JpaRepository<CourierMoves, Long> {

    List<CourierMoves> findByCourierAndStore(Courier courier, Store store);
}
