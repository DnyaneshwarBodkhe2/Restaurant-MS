package com.api.restaurant.Repository;

import com.api.restaurant.entity.TableOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableOrderRepository extends JpaRepository<TableOrder,Long> {
    Optional<TableOrder>findByTableNumber(int tableNumber);
}
