package com.WS_Project.modules.restaurantTable.service;

import com.WS_Project.modules.restaurantTable.dto.request.RestaurantTableRequestDTO;
import com.WS_Project.modules.restaurantTable.dto.response.RestaurantTableResponseDTO;
import com.WS_Project.modules.restaurantTable.entity.RestaurantTable;
import com.WS_Project.modules.restaurantTable.mapper.RestaurantTableMapper;
import com.WS_Project.modules.restaurantTable.repository.RestaurantTableRepository;
import com.WS_Project.modules.reservation.dto.response.ReservationResponseDTO;
import com.WS_Project.modules.reservation.mapper.ReservationMapper;
import com.WS_Project.modules.restaurant.entity.Restaurant;
import com.WS_Project.modules.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantTableResponseDTO createTable(RestaurantTableRequestDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        RestaurantTable table = RestaurantTableMapper.toEntity(dto, restaurant);
        return RestaurantTableMapper.toResponse(tableRepository.save(table));
    }

    public RestaurantTableResponseDTO getTableById(Long id) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
        return RestaurantTableMapper.toResponse(table);
    }

    public List<RestaurantTableResponseDTO> getAllTables() {
        return tableRepository.findAll().stream()
                .map(RestaurantTableMapper::toResponse)
                .collect(Collectors.toList());
    }

    public RestaurantTableResponseDTO updateTable(Long id, RestaurantTableRequestDTO dto) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
        table.setTableNumber(dto.getTableNumber());
        table.setCapacity(dto.getCapacity());
        table.setLocation(dto.getLocation());
        table.setStatus(dto.getStatus());
        return RestaurantTableMapper.toResponse(tableRepository.save(table));
    }

    public List<ReservationResponseDTO> getTableReservations(Long id) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
        return table.getReservations().stream()
                .map(ReservationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }
}
