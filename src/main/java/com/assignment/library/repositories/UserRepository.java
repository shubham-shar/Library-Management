package com.assignment.library.repositories;

import java.util.Optional;

import com.assignment.library.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
