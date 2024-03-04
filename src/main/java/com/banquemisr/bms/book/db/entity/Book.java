package com.banquemisr.bms.book.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Column(name = "title", nullable = false, length = 100, unique = true)
    private String title;

    @NotBlank
    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @NotBlank
    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @PositiveOrZero
    @Column(name = "stock_level", nullable = false)
    private Integer stockLevel;

    @Column(name = "availability", nullable = false)
    private boolean availability;

    @Version
    @Column(name = "version")
    private Long version;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
}