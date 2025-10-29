package com.WS_Projet.modules.restaurant.service;

import com.WS_Projet.modules.restaurant.dto.request.CreateRestaurantRequest;
import com.WS_Projet.modules.restaurant.dto.request.UpdateRestaurantRequest;
import com.WS_Projet.modules.restaurant.dto.response.RestaurantResponse;
import com.WS_Projet.modules.restaurant.entity.Restaurant;
import com.WS_Projet.modules.restaurant.mapper.RestaurantMapper;
import com.WS_Projet.modules.restaurant.repository.RestaurantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public RestaurantServiceImpl(RestaurantRepository repository, RestaurantMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RestaurantResponse create(CreateRestaurantRequest req) {
        Restaurant r = mapper.fromCreateRequest(req);
        // Hash password before saving
        if (r.getPassword() != null) {
            r.setPassword(passwordEncoder.encode(r.getPassword()));
        }
        r = repository.save(r);
        return mapper.toResponse(r);
    }

    @Override
    public RestaurantResponse update(Long id, UpdateRestaurantRequest req) {
        Restaurant r = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Restaurant non trouvé: " + id));
        mapper.updateEntityFromRequest(req, r);
        // If password provided, hash it
        if (req.getPassword() != null) {
            r.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        r = repository.save(r);
        return mapper.toResponse(r);
    }

    @Override
    public RestaurantResponse getById(Long id) {
        return repository.findById(id).map(mapper::toResponse).orElse(null);
    }

    @Override
    public List<RestaurantResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
