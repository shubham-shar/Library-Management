package com.assignment.library.model.dto;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import lombok.Data;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
@Data
@Validated
public class UserDto {
    private Long id;
    
    @Email
    @NotNull
    private String username;
    
    private boolean isMembershipActive;
    private String firstName;
    private String lastName;
    private Set<BookDto> booksIssued;
    private Date createdAt;
    private Date updatedAt;
    
    @Override
    public String toString() {
        return "UserDto{" + "id=" + id + ", username='" + username + '\'' + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\'' + ", isMembershipActive=" + isMembershipActive + ", booksIssued="
                + booksIssued + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return isMembershipActive == userDto.isMembershipActive && Objects.equals(id, userDto.id) && Objects
                .equals(username, userDto.username) && Objects.equals(firstName, userDto.firstName) && Objects
                .equals(lastName, userDto.lastName) && Objects.equals(booksIssued, userDto.booksIssued) && Objects
                .equals(createdAt, userDto.createdAt) && Objects.equals(updatedAt, userDto.updatedAt);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, isMembershipActive, booksIssued, createdAt, updatedAt);
    }
}
