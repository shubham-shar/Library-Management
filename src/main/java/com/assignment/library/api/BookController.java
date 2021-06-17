package com.assignment.library.api;

import javax.validation.Valid;

import com.assignment.library.model.dto.BookDto;
import com.assignment.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/library/book")
public class BookController {
    
    @Autowired
    BookService bookService;
    
    @GetMapping
    public ResponseEntity<BookDto> getBookById(@RequestParam Long id) {
        log.info("Received request to fetch book details with id {}", id);
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    
    @PostMapping
    private ResponseEntity<BookDto> addBookDetails(@Valid @RequestBody BookDto bookDto) {
        log.info("Received request to create book with details {}", bookDto.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookDto));
    }
    
    @DeleteMapping("/id")
    public ResponseEntity<String> deleteBookById(@RequestParam Long id) {
        log.info("Received request to delete book details with id {}", id);
        bookService.removeBook(id);
        return ResponseEntity.ok("Book with id" + id + " deleted");
    }
    
    @DeleteMapping("/name")
    public ResponseEntity<String> deleteBookByNameAndAuthor(@RequestParam String name,
            @RequestParam String authorName) {
        log.info("Received request to delete book details with name {} and author {}", name, authorName);
        bookService.removeBook(name, authorName);
        return ResponseEntity.ok("Book with name" + name + " and author " + authorName + " deleted");
    }
}
