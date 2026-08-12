package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.dto.UserResponseDto;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminUserRestController {

    private final UserService userService;
    @Autowired
    public AdminUserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserResponseDto::fromUser)
                .collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserResponseDto.fromUser(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody User user) {
        User saved = userService.saveUser(user);
        return ResponseEntity
                .created(URI.create("api/admin/" + saved.getId()))
                .body(UserResponseDto.fromUser(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existing = userService.getUserById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        userService.updateUser(user);
        User saved = userService.getUserById(id);
        return ResponseEntity.ok(UserResponseDto.fromUser(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User existing = userService.getUserById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
