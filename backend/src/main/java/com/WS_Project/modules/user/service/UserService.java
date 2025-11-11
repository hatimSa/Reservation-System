package com.WS_Project.modules.user.service;

import com.WS_Project.modules.user.dto.request.UserRequestDTO;
import com.WS_Project.modules.user.dto.request.PasswordRequestDTO;
import com.WS_Project.modules.user.dto.response.UserResponseDTO;
import com.WS_Project.modules.user.entity.User;
import com.WS_Project.modules.user.mapper.UserMapper;
import com.WS_Project.modules.user.repository.UserRepository;
import com.WS_Project.modules.restaurant.dto.response.RestaurantResponseDTO;
import com.WS_Project.modules.restaurant.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        // encoder le mot de passe
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UserMapper.toResponse(userRepository.save(user));
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponse(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return UserMapper.toResponse(userRepository.save(user));
    }

    public List<RestaurantResponseDTO> getUserRestaurant(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getRestaurant() != null) {
            return List.of(RestaurantMapper.toResponse(user.getRestaurant()));
        }
        return List.of();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // ===== méthodes pour /me =====

    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec email: " + email));
        return UserMapper.toResponse(user);
    }

    public UserResponseDTO updateUserByEmail(String email, UserRequestDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec email: " + email));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return UserMapper.toResponse(userRepository.save(user));
    }

    public void updatePassword(String email, PasswordRequestDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec email: " + email));

        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe actuel incorrect");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
    }
}
