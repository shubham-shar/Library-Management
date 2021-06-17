package com.assignment.library.utils;

import java.util.Objects;
import java.util.Optional;

import com.assignment.library.model.dto.BookDto;
import com.assignment.library.model.entities.Book;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
public class BookDetailUtil {
    private BookDetailUtil(){}
    
    public static void updateDtoFields(Book savedBook, BookDto bookDto) {
        if(Objects.nonNull(savedBook) && Objects.nonNull(bookDto)) {
            Optional.ofNullable(savedBook.getAuthorName()).ifPresent(bookDto::setAuthorName);
            Optional.ofNullable(savedBook.getName()).ifPresent(bookDto::setName);
            Optional.ofNullable(savedBook.getId()).ifPresent(bookDto::setId);
            Optional.ofNullable(savedBook.getInventory().getIssuedTillDate()).ifPresent(bookDto::setIssuedTillDate);
            Optional.ofNullable(savedBook.getInventory().isAlreadyBorrowed()).ifPresent(bookDto::setIsAlreadyBorrowed);
            Optional.ofNullable(savedBook.getPublisher()).ifPresent(bookDto::setPublisher);
            Optional.ofNullable(savedBook.getCreatedAt()).ifPresent(bookDto::setCreatedAt);
            Optional.ofNullable(savedBook.getUpdatedAt()).ifPresent(bookDto::setUpdatedAt);
        }
    }
    
    public static void updateEntityFields(Book bookEntity, BookDto bookDto) {
        if(Objects.nonNull(bookEntity) && Objects.nonNull(bookDto)) {
            Optional.ofNullable(bookDto.getAuthorName()).ifPresent(bookEntity::setAuthorName);
            Optional.ofNullable(bookDto.getName()).ifPresent(bookEntity::setName);
            Optional.ofNullable(bookDto.getPublisher()).ifPresent(bookEntity::setPublisher);
        }
    }
}
