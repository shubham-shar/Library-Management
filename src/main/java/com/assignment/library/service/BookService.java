package com.assignment.library.service;

import java.util.Objects;
import java.util.Optional;

import com.assignment.library.model.dto.BookDto;
import com.assignment.library.model.entities.Book;
import com.assignment.library.model.entities.Inventory;
import com.assignment.library.model.exceptions.EntityNotFoundException;
import com.assignment.library.model.exceptions.EntityAlreadyExistsException;
import com.assignment.library.repositories.BookRepository;
import com.assignment.library.repositories.InventoryRepository;
import com.assignment.library.utils.BookDetailUtil;

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
public class BookService {
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    InventoryRepository inventoryRepository;
    
    public BookDto addBook(BookDto bookDto) {
        Optional<Book> book = bookRepository.findByNameAndAuthorName(bookDto.getName(), bookDto.getAuthorName());
        if (book.isPresent()) {
            throw new EntityAlreadyExistsException(
                    "Book with name " + bookDto.getName() + " and author " + bookDto.getAuthorName() + " already "
                            + "exists");
        }
        
        Book bookEntity = new Book();
        BookDetailUtil.updateEntityFields(bookEntity, bookDto);
        Inventory inventory = new Inventory();
        inventory.setBookDetails(bookEntity);
        inventory.setAlreadyBorrowed(false);
        Inventory savedInventory = inventoryRepository.save(inventory);
        bookEntity.setInventory(savedInventory);
    
        Book savedBook = bookRepository.save(bookEntity);
        BookDetailUtil.updateDtoFields(savedBook, bookDto);
        if(Objects.nonNull(savedBook.getInventory())) {
            bookDto.setIsAlreadyBorrowed(savedBook.getInventory().isAlreadyBorrowed());
            bookDto.setIssuedTillDate(savedBook.getInventory().getIssuedTillDate());
        }
        return bookDto;
    }
    
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
        BookDto bookDto = new BookDto();
        BookDetailUtil.updateDtoFields(book, bookDto);
        if(Objects.nonNull(book.getInventory())) {
            bookDto.setIsAlreadyBorrowed(book.getInventory().isAlreadyBorrowed());
            bookDto.setIssuedTillDate(book.getInventory().getIssuedTillDate());
        }
        return bookDto;
    }
    
    public void removeBook(Long id) {
        Book book = bookRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
        bookRepository.delete(book);
    }
    
    public void removeBook(String name, String authorName) {
        Book book = bookRepository.findByNameAndAuthorName(name, authorName).orElseThrow(
                () -> new EntityNotFoundException("Book with name " + name + " and author " + authorName
                                                          + " not found"));
        bookRepository.delete(book);
    }
}
