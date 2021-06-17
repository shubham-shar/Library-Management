package com.assignment.library.service;

import java.util.Calendar;
import java.util.Date;

import com.assignment.library.model.dto.BookDto;
import com.assignment.library.model.dto.OrderDto;
import com.assignment.library.model.entities.Book;
import com.assignment.library.model.entities.Inventory;
import com.assignment.library.model.entities.User;
import com.assignment.library.model.exceptions.EntityNotFoundException;
import com.assignment.library.model.exceptions.UnauthorizedException;
import com.assignment.library.repositories.BookRepository;
import com.assignment.library.repositories.InventoryRepository;
import com.assignment.library.repositories.UserRepository;
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
public class OrderService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    BookRepository bookRepository;
    
    public OrderDto issueBook(OrderDto orderDto) {
        User user = userRepository.findByUsername(orderDto.getUsername()).orElseThrow(
                () -> new EntityNotFoundException("User with email " + orderDto.getUsername() + " not " + "found"));
        
        BookDto bookDto = orderDto.getBookDto();
        Book book = bookRepository.findByNameAndAuthorName(bookDto.getName(), bookDto.getAuthorName()).orElseThrow(
                () -> new EntityNotFoundException("Book with name " + bookDto.getName() + " and author "
                                                          + bookDto.getAuthorName() + " not found"));
        
        if(book.getInventory().isAlreadyBorrowed() && book.getInventory().getIssuedTillDate().after(new Date())) {
            throw new UnauthorizedException("Cannot borrow book, as already borrowed, Please borrow after " +
                                                    book.getInventory().getIssuedTillDate().toString());
        }
    
        if(user.getBooks().size() >= 2) {
            throw new UnauthorizedException("Cannot borrow book as limit of 2 reached");
        }
    
        Inventory inventory = book.getInventory();
        inventory.setAlreadyBorrowed(true);
    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        inventory.setIssuedTillDate(calendar.getTime());
        inventory.setBookDetails(book);
        
        book.setInventory(inventory);
        book.setUser(user);
        Book savedBook = bookRepository.save(book);
        user.getBooks().add(savedBook);
        User savedUser = userRepository.save(user);
        orderDto.setUsername(savedUser.getUsername());
        BookDetailUtil.updateDtoFields(savedBook, bookDto);
        orderDto.setBookDto(bookDto);
        return orderDto;
    }
    
    public OrderDto returnBook(OrderDto orderDto) {
        User user = userRepository.findByUsername(orderDto.getUsername()).orElseThrow(
                () -> new EntityNotFoundException("User with email " + orderDto.getUsername() + " not " + "found"));
        
        BookDto bookDto = orderDto.getBookDto();
        Book book = bookRepository.findByNameAndAuthorName(bookDto.getName(), bookDto.getAuthorName()).orElseThrow(
                () -> new EntityNotFoundException("Book with name " + bookDto.getName() + " and author "
                                                          + bookDto.getAuthorName() + " not found"));
        
        if(!user.getBooks().contains(book)) {
            throw new UnauthorizedException("Book cannot be returned as it is issued to someone else");
        }
    
        Inventory inventory = book.getInventory();
        inventory.setAlreadyBorrowed(false);
        inventory.setIssuedTillDate(null);
        inventory.setBookDetails(book);
    
        book.setInventory(inventory);
        book.setUser(null);
        Book savedBook = bookRepository.save(book);
//        user.getBooks().remove(savedBook);
//        User savedUser = userRepository.save(user);
        orderDto.setUsername(user.getUsername());
        BookDetailUtil.updateDtoFields(savedBook, bookDto);
        orderDto.setBookDto(bookDto);
        return orderDto;
    }
}
