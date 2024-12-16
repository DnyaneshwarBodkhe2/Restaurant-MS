package com.api.restaurant.controller;

import com.api.restaurant.entity.Dish;
import com.api.restaurant.entity.TableOrder;
import com.api.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/menu/addDish")
    public ResponseEntity<Dish> addDish(@RequestBody Dish dish) {
        return ResponseEntity.ok(restaurantService.addDish(dish));
    }


    @GetMapping("/menu")
    public ResponseEntity<List<Dish>> getMenu() {

        return ResponseEntity.ok(restaurantService.getMenu());
    }


    @PostMapping("/orders/{tableNumber}")
    public ResponseEntity<TableOrder> takeOrder(@PathVariable int tableNumber, @RequestBody List<Long> dishIds) {
        return ResponseEntity.ok(restaurantService.takeOrder(tableNumber, dishIds));
    }


    @PutMapping("/orders/{tableNumber}")
    public ResponseEntity<TableOrder> addExtraOrder(@PathVariable int tableNumber, @RequestBody List<Long> dishIds) {
        return ResponseEntity.ok(restaurantService.addExtraOrder(tableNumber, dishIds));
    }

    @GetMapping("/orders/{tableNumber}/bill")
    public ResponseEntity<Double> getBill(@PathVariable int tableNumber) {
        return ResponseEntity.ok(restaurantService.getBill(tableNumber));
    }
}

