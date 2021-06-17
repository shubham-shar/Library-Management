package com.assignment.library.api;

import javax.validation.Valid;

import com.assignment.library.model.dto.OrderDto;
import com.assignment.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
@Slf4j
@RestController
@RequestMapping("/library/order")
public class OrderController {
    
    @Autowired
    OrderService orderService;
    
    @PostMapping("/issue")
    private ResponseEntity<OrderDto> issueBook(@Valid @RequestBody OrderDto orderDto) {
        log.info("Received request to create user with details {}", orderDto.toString());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderService.issueBook(orderDto));
    }
    
    @PostMapping("/return")
    private ResponseEntity<OrderDto> returnBook(@Valid @RequestBody OrderDto orderDto) {
        log.info("Received request to create user with details {}", orderDto.toString());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderService.returnBook(orderDto));
    }
}
