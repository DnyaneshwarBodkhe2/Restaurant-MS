package com.api.restaurant.service;

import com.api.restaurant.Repository.DishRepository;
import com.api.restaurant.Repository.TableOrderRepository;
import com.api.restaurant.entity.Dish;
import com.api.restaurant.entity.TableOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private TableOrderRepository tableOrderRepository;

    public Dish addDish(Dish dish) {

        return dishRepository.save(dish);
    }

    public List<Dish> getMenu() {

        return dishRepository.findAll();
    }

    public TableOrder takeOrder(int tableNumber, List<Long> dishIds) {
        TableOrder order = tableOrderRepository.findByTableNumber(tableNumber)
                .orElse(new TableOrder());
        order.setTableNumber(tableNumber);

        List<Dish> dishes = dishRepository.findAllById(dishIds);
        order.setDishes(dishes);

        return tableOrderRepository.save(order);
    }

    public TableOrder addExtraOrder(int tableNumber, List<Long> dishIds) {
        TableOrder order = tableOrderRepository.findByTableNumber(tableNumber)
                .orElseThrow(() -> new RuntimeException("Table order not found"));

        List<Dish> extraDishes = dishRepository.findAllById(dishIds);
        order.getDishes().addAll(extraDishes);

        return tableOrderRepository.save(order);
    }

    public Double getBill(int tableNumber) {
        TableOrder order = tableOrderRepository.findByTableNumber(tableNumber)
                .orElseThrow(() -> new RuntimeException("Table order not found"));

        return order.getDishes().stream()
                .mapToDouble(Dish::getPrice)
                .sum();

    }
}
