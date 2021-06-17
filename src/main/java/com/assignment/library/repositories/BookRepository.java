package com.assignment.library.repositories;

import java.util.Optional;

import com.assignment.library.model.entities.Book;
import com.assignment.library.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    
    Optional<Book> findByNameAndAuthorName(String name, String authorName);
}
