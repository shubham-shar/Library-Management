package com.assignment.library.model.dto;

import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
@Data
public class OrderDto {
    @Email
    @NotNull
    private String username;
    
    private BookDto bookDto;
    
    @Override
    public String toString() {
        return "OrderDto{" + "username='" + username + '\'' + ", bookDto=" + bookDto + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDto)) {
            return false;
        }
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(username, orderDto.username) && Objects.equals(bookDto, orderDto.bookDto);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(username, bookDto);
    }
}
