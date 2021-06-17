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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "book", uniqueConstraints = @UniqueConstraint(columnNames={"name", "author_name"}))
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    private String name;
    
    @Column(name = "author_name")
    private String authorName;
    
    private String publisher;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id")
    @JsonBackReference("inventory")
    private Inventory inventory;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "books")
    private User user;
    
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", authorName='" + authorName + '\'' + ", publisher='"
                + publisher + '\'' + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(name, book.name) && Objects
                .equals(authorName, book.authorName) && Objects.equals(publisher, book.publisher) && Objects
                .equals(createdAt, book.createdAt) && Objects.equals(updatedAt, book.updatedAt);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorName, publisher, createdAt, updatedAt);
    }
}
