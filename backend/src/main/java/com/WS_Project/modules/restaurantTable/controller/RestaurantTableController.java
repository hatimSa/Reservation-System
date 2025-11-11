package com.WS_Project.modules.restaurantTable.controller;

import com.WS_Project.modules.restaurantTable.dto.request.RestaurantTableRequestDTO;
import com.WS_Project.modules.restaurantTable.dto.response.RestaurantTableResponseDTO;
import com.WS_Project.modules.restaurantTable.service.RestaurantTableService;
import com.WS_Project.modules.reservation.dto.response.ReservationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService tableService;

    @PostMapping
    public ResponseEntity<RestaurantTableResponseDTO> createTable(@RequestBody RestaurantTableRequestDTO dto) {
        RestaurantTableResponseDTO response = tableService.createTable(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTableResponseDTO> getTableById(@PathVariable Long id) {
        RestaurantTableResponseDTO response = tableService.getTableById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTableResponseDTO>> getAllTables() {
        List<RestaurantTableResponseDTO> response = tableService.getAllTables();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getTableReservations(@PathVariable Long id) {
        List<ReservationResponseDTO> response = tableService.getTableReservations(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTableResponseDTO> updateTable(@PathVariable Long id, @RequestBody RestaurantTableRequestDTO dto) {
        RestaurantTableResponseDTO response = tableService.updateTable(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
