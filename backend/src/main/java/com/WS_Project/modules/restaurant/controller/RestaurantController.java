package com.WS_Project.modules.restaurant.controller;

import com.WS_Project.modules.restaurant.dto.request.RestaurantRequestDTO;
import com.WS_Project.modules.restaurant.dto.response.RestaurantResponseDTO;
import com.WS_Project.modules.restaurant.service.RestaurantService;
import com.WS_Project.modules.user.dto.response.UserResponseDTO;
import com.WS_Project.modules.restaurantTable.dto.response.RestaurantTableResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurants", description = "Restaurant management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "Create a new restaurant", description = "Create a new restaurant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Restaurant created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required")
    })
    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(@RequestBody RestaurantRequestDTO dto) {
        RestaurantResponseDTO response = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get restaurant by ID", description = "Retrieve a specific restaurant by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurant found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required"),
        @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable Long id) {
        RestaurantResponseDTO response = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all restaurants", description = "Retrieve all restaurants")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurants retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required")
    })
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants() {
        List<RestaurantResponseDTO> response = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get restaurant owner", description = "Get the owner of a specific restaurant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Owner information retrieved"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required"),
        @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GetMapping("/{id}/owner")
    public ResponseEntity<UserResponseDTO> getRestaurantOwner(@PathVariable Long id) {
        UserResponseDTO response = restaurantService.getRestaurantOwner(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get restaurant tables", description = "Get all tables for a specific restaurant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tables retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required"),
        @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GetMapping("/{id}/tables")
    public ResponseEntity<List<RestaurantTableResponseDTO>> getRestaurantTables(@PathVariable Long id) {
        List<RestaurantTableResponseDTO> response = restaurantService.getRestaurantTables(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update restaurant", description = "Update an existing restaurant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Restaurant updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required"),
        @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequestDTO dto) {
        RestaurantResponseDTO response = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete restaurant", description = "Delete a restaurant by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Restaurant deleted successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Owner role required"),
        @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
