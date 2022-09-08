package edu.escuelaing.ieti.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.ieti.dto.UserDto;
import edu.escuelaing.ieti.entities.User;
import edu.escuelaing.ieti.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = new ArrayList<>();
        userService.getAll().forEach((user) -> users.add(user.toDTO()));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.toDTO());
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        System.out.println(userDto.getName());
        User user = userService.create(userDto.toEntity());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user.toDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user, @PathVariable String id) {
        User userMethod = userService.update(user.toEntity(), id);
        if (userMethod == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMethod.toDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.ok(userService.findById(id) == null);
    }

    @GetMapping("/searchName/{queryText}")
    public ResponseEntity<List<UserDto>> findUsersWithNameOrLastNameLike(@PathVariable String queryText) {
        List<UserDto> users = new ArrayList<>();
        userService.findUsersWithNameOrLastNameLike(queryText).forEach((user) -> users.add(user.toDTO()));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/searchDate/{startDate}")
    public ResponseEntity<List<UserDto>> findUsersCreatedAfter(@PathVariable String startDate) throws ParseException {
        List<UserDto> users = new ArrayList<>();
        userService.findUsersCreatedAfter(
                new SimpleDateFormat("dd-MM-yyyy").parse(startDate)).forEach((user) -> users.add(user.toDTO()));
        return ResponseEntity.ok(users);
    }
}
