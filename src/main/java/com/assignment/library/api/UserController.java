package com.assignment.library.api;

import javax.validation.Valid;

import com.assignment.library.model.dto.UserDto;
import com.assignment.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
@Slf4j
@RestController
@RequestMapping("/library/user")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/id")
    public ResponseEntity<UserDto> getUserById(@RequestParam Long id) {
        log.info("Received request to fetch user with id {}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }
    
    @GetMapping("/email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        log.info("Received request to fetch user with email {}", email);
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    
    @PostMapping
    private ResponseEntity<UserDto> addUserDetails(@Valid @RequestBody UserDto userDto) {
        log.info("Received request to create user with details {}", userDto.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }
    
    @PutMapping
    private ResponseEntity<UserDto> updateUserDetails(@Valid @RequestBody UserDto userDto) {
        log.info("Received request to update user with details {}", userDto.toString());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(userDto));
    }
}
