package com.WS_Project.modules.restaurant.service;

import com.WS_Project.modules.restaurant.dto.request.RestaurantRequestDTO;
import com.WS_Project.modules.restaurant.dto.response.RestaurantResponseDTO;
import com.WS_Project.modules.restaurant.entity.Restaurant;
import com.WS_Project.modules.restaurant.mapper.RestaurantMapper;
import com.WS_Project.modules.restaurant.repository.RestaurantRepository;
import com.WS_Project.modules.user.entity.User;
import com.WS_Project.modules.user.repository.UserRepository;
import com.WS_Project.modules.user.dto.response.UserResponseDTO;
import com.WS_Project.modules.user.mapper.UserMapper;
import com.WS_Project.modules.restaurantTable.dto.response.RestaurantTableResponseDTO;
import com.WS_Project.modules.restaurantTable.mapper.RestaurantTableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO dto) {
        Restaurant restaurant = RestaurantMapper.toEntity(dto);
        if (dto.getOwnerId() != null) {
            User owner = userRepository.findById(dto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner user not found"));
            restaurant.setOwner(owner);
        } else {
            throw new RuntimeException("ownerId is required");
        }
        return RestaurantMapper.toResponse(restaurantRepository.save(restaurant));
    }

    public RestaurantResponseDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return RestaurantMapper.toResponse(restaurant);
    }

    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantMapper::toResponse)
                .collect(Collectors.toList());
    }


    public RestaurantResponseDTO updateRestaurant(Long id, RestaurantRequestDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setType(dto.getType());
        if (dto.getOwnerId() != null) {
            User owner = userRepository.findById(dto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner user not found"));
            restaurant.setOwner(owner);
        }
        return RestaurantMapper.toResponse(restaurantRepository.save(restaurant));
    }

    public UserResponseDTO getRestaurantOwner(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        if (restaurant.getOwner() == null) {
            throw new RuntimeException("Restaurant has no owner");
        }
        return UserMapper.toResponse(restaurant.getOwner());
    }

    public List<RestaurantTableResponseDTO> getRestaurantTables(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return restaurant.getTables().stream()
                .map(RestaurantTableMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
