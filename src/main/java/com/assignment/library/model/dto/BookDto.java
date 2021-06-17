package com.assignment.library.model.dto;

import java.util.Date;
import java.util.Objects;

import lombok.Data;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
@Data
public class BookDto {
    private Long id;
    private String name;
    private String authorName;
    private String publisher;
    private Boolean isAlreadyBorrowed;
    private Date issuedTillDate;
    private Date createdAt;
    private Date updatedAt;
    
    @Override
    public String toString() {
        return "BookDto{" + "id=" + id + ", name='" + name + '\'' + ", authorName='" + authorName + '\''
                + ", publisher='" + publisher + '\'' + ", isAlreadyBorrowed=" + isAlreadyBorrowed + ", issuedTillDate="
                + issuedTillDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookDto)) {
            return false;
        }
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(name, bookDto.name) && Objects
                .equals(authorName, bookDto.authorName) && Objects.equals(publisher, bookDto.publisher) && Objects
                .equals(isAlreadyBorrowed, bookDto.isAlreadyBorrowed) && Objects
                .equals(issuedTillDate, bookDto.issuedTillDate) && Objects.equals(createdAt, bookDto.createdAt)
                && Objects.equals(updatedAt, bookDto.updatedAt);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorName, publisher, isAlreadyBorrowed, issuedTillDate, createdAt, updatedAt);
    }
}
