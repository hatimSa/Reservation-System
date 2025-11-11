package com.WS_Project.modules.user.controller;

import com.WS_Project.modules.user.dto.request.UserRequestDTO;
import com.WS_Project.modules.user.dto.response.UserResponseDTO;
import com.WS_Project.modules.user.service.UserService;
import com.WS_Project.modules.restaurant.dto.response.RestaurantResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication; 
import com.WS_Project.modules.user.dto.request.PasswordRequestDTO;



import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto) {
        UserResponseDTO response = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/restaurant")
    public ResponseEntity<List<RestaurantResponseDTO>> getUserRestaurant(@PathVariable Long id) {
        List<RestaurantResponseDTO> response = userService.getUserRestaurant(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        UserResponseDTO response = userService.updateUser(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
         String email = authentication.getName();
         UserResponseDTO response = userService.getUserByEmail(email);
         return ResponseEntity.ok(response);
}
 @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateCurrentUser(
            Authentication authentication,
            @ModelAttribute UserRequestDTO dto) {
        String email = authentication.getName();
        UserResponseDTO updated = userService.updateUserByEmail(email, dto);
        return ResponseEntity.ok(updated);
    }

@PutMapping("/me/password")
public ResponseEntity<String> updatePassword(
        Authentication authentication,
        @RequestBody PasswordRequestDTO dto) {

    String email = authentication.getName();
    userService.updatePassword(email, dto);
    return ResponseEntity.ok("Mot de passe mis à jour avec succès");
}


}
