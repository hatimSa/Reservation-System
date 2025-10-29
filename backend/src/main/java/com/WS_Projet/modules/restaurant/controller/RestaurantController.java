package com.WS_Projet.modules.restaurant.controller;

import com.WS_Projet.modules.restaurant.dto.request.CreateRestaurantRequest;
import com.WS_Projet.modules.restaurant.dto.request.UpdateRestaurantRequest;
import com.WS_Projet.modules.restaurant.dto.response.RestaurantResponse;
import com.WS_Projet.modules.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getById(@PathVariable Long id) {
        RestaurantResponse resp = service.getById(id);
        if (resp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody CreateRestaurantRequest req) {
        RestaurantResponse created = service.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateRestaurantRequest req) {
        RestaurantResponse updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
