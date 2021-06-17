package com.assignment.library.repositories;

import com.assignment.library.model.entities.Inventory;
import com.assignment.library.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
