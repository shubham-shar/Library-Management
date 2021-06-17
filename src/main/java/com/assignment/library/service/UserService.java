package com.assignment.library.service;

import java.util.Objects;
import java.util.Optional;

import com.assignment.library.model.dto.UserDto;
import com.assignment.library.model.entities.User;
import com.assignment.library.model.exceptions.EntityNotFoundException;
import com.assignment.library.model.exceptions.EntityAlreadyExistsException;
import com.assignment.library.repositories.UserRepository;
import com.assignment.library.utils.UserDetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
@Slf4j
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public UserDto createUser(UserDto userDto) {
        Optional<User> user = userRepository.findByUsername(userDto.getUsername());
        if (user.isPresent()) {
            throw new EntityAlreadyExistsException("User with email " + user.get() + " already exists");
        }
        
        User userEntity = new User();
        UserDetailUtil.updateEntityFields(userDto, userEntity);
        User savedUser = userRepository.save(userEntity);
        UserDetailUtil.updateDtoFields(savedUser, userDto);
        return userDto;
    }
    
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                            new EntityNotFoundException("User with id " + id + " not found"));
        UserDto userDto = new UserDto();
        UserDetailUtil.updateDtoFields(user, userDto);
        return userDto;
    }
    
    public UserDto updateUser(UserDto userDto) {
        User user = fetchUser(userDto.getUsername(), userDto.getId());
        UserDetailUtil.updateEntityFields(userDto, user);
        User savedUser = userRepository.save(user);
        UserDetailUtil.updateDtoFields(savedUser, userDto);
        return userDto;
    }
    
    public UserDto getUserByEmail(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                                    new EntityNotFoundException("User with email " + username + " not found"));
        UserDto userDto = new UserDto();
        UserDetailUtil.updateDtoFields(user, userDto);
        return userDto;
    }
    
    public User fetchUser(String username, Long id) {
        Optional<User> user = Optional.empty();
        if(Objects.nonNull(id)) {
            log.info("Fetching user by id {}", id);
            user = userRepository.findById(id);
        }
        if(user.isEmpty() && Objects.nonNull(username)) {
            log.info("Fetching user by email {}", username);
            user = userRepository.findByUsername(username);
        }
        
        return user.orElseThrow(() -> new EntityNotFoundException("User with email " + username + " and id " + id +
                                                                          " not found"));
    }
}
