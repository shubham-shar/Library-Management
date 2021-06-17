package com.assignment.library.model.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @Column(name = "is_already_borrowed")
    private boolean isAlreadyBorrowed;
    
    @Column(name = "issued_till_date")
    private Date issuedTillDate;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @OneToOne(mappedBy = "inventory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference("inventory")
    private Book bookDetails;
    
    @Override
    public String toString() {
        return "Inventory{" + "id=" + id + ", isAlreadyBorrowed=" + isAlreadyBorrowed + ", issuedTillDate="
                + issuedTillDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", bookDetails="
                + bookDetails + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventory)) {
            return false;
        }
        Inventory inventory = (Inventory) o;
        return isAlreadyBorrowed == inventory.isAlreadyBorrowed && Objects.equals(id, inventory.id) && Objects
                .equals(issuedTillDate, inventory.issuedTillDate) && Objects.equals(createdAt, inventory.createdAt)
                && Objects.equals(updatedAt, inventory.updatedAt) && Objects.equals(bookDetails, inventory.bookDetails);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, isAlreadyBorrowed, issuedTillDate, createdAt, updatedAt, bookDetails);
    }
}
