package com.assignment.library.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.assignment.library.model.dto.BookDto;
import com.assignment.library.model.dto.UserDto;
import com.assignment.library.model.entities.User;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
public class UserDetailUtil {
    private UserDetailUtil(){}
    
    public static void updateEntityFields(UserDto userDto, User userEntity) {
        if(Objects.nonNull(userDto) && Objects.nonNull(userEntity)) {
            Optional.ofNullable(userDto.getUsername()).ifPresent(userEntity::setUsername);
            Optional.ofNullable(userDto.getFirstName()).ifPresent(userEntity::setFirstName);
            Optional.ofNullable(userDto.getLastName()).ifPresent(userEntity::setLastName);
            userEntity.setMembershipActive(userDto.isMembershipActive());
        }
    }
    
    public static void updateDtoFields(User savedUser, UserDto userDto) {
        if(Objects.nonNull(userDto) && Objects.nonNull(savedUser)) {
            Optional.ofNullable(savedUser.getUsername()).ifPresent(userDto::setUsername);
            Optional.ofNullable(savedUser.getFirstName()).ifPresent(userDto::setFirstName);
            Optional.ofNullable(savedUser.getLastName()).ifPresent(userDto::setLastName);
            Optional.ofNullable(savedUser.getId()).ifPresent(userDto::setId);
            Optional.ofNullable(savedUser.getCreatedAt()).ifPresent(userDto::setCreatedAt);
            Optional.ofNullable(savedUser.getUpdatedAt()).ifPresent(userDto::setUpdatedAt);
            
            savedUser.setMembershipActive(userDto.isMembershipActive());
    
            Optional.ofNullable(savedUser.getBooks()).ifPresent(books -> {
                userDto.setBooksIssued(books.stream().map(book -> {
                    BookDto bookDto = new BookDto();
                    BookDetailUtil.updateDtoFields(book, bookDto);
                    return bookDto;
                }).collect(Collectors.toSet()));
            });
        }
    }
}
