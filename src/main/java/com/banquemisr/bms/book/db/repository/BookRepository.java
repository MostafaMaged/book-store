package com.banquemisr.bms.book.db.repository;

import com.banquemisr.bms.book.db.entity.Book;
import com.banquemisr.bms.book.db.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    @Query("SELECT DISTINCT b FROM Book b " +
            "JOIN b.categories c " +
            "JOIN Borrowing br ON br.bookId = b.id " +
            "WHERE br.id = (SELECT MAX(br2.id) FROM Borrowing br2 WHERE br2.customerId = :userId) ")
    List<Book> findBooksByCategoryOfLastBorrowedBook(@Param("userId") UUID userId);
}