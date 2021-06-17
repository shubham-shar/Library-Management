package com.assignment.library.model.entities;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @Email
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "is_membership_active")
    private boolean isMembershipActive;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "books")
    private Set<Book> books;
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\'' + ", isMembershipActive=" + isMembershipActive + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + ", books=" + books + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return isMembershipActive == user.isMembershipActive && Objects.equals(id, user.id) && Objects
                .equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects
                .equals(lastName, user.lastName) && Objects.equals(createdAt, user.createdAt) && Objects
                .equals(updatedAt, user.updatedAt) && Objects.equals(books, user.books);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, isMembershipActive, createdAt, updatedAt, books);
    }
}
