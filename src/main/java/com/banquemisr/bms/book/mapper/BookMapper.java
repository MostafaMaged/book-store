package com.banquemisr.bms.book.mapper;

import com.banquemisr.bms.book.db.entity.Book;
import com.banquemisr.bms.book.db.entity.Borrowing;
import com.banquemisr.bms.book.dto.request.BookParams;
import com.banquemisr.bms.book.dto.request.BorrowBookRequest;
import com.banquemisr.bms.book.dto.request.CreateBookRequest;
import com.banquemisr.bms.book.dto.request.UpdateBookRequest;
import com.banquemisr.bms.book.dto.response.BookDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@Component
public class BookMapper {

    public BookDTO fromEntityToDTO(Book book) {

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .categories(book.getCategories())
                .stockLvl(book.getStockLevel())
                .isAvailable(book.isAvailability())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    public Book fromDTOtoEntity(BookDTO dto) {

        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .description(dto.getDescription())
                .categories(dto.getCategories())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public void updateBook(Book book, UpdateBookRequest request) {

        if (request.getStockLvl() != null) {
            book.setStockLevel(request.getStockLvl());
        }

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }

        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }

        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }
    }

    public Borrowing fromBorrowingRequestToEntity(UUID bookId, BorrowBookRequest request) {

        return Borrowing.builder()
                .bookId(bookId)
                .customerId(request.getUserId())
                .returnDate(request.getReturnDate().atZone(ZoneId.systemDefault()).toInstant())
                .build();
    }

    public Book fromCreateRequestToEntity(CreateBookRequest request) {

        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .description(request.getDescription())
                .stockLevel(request.getStockLvl())
                .availability(request.getStockLvl() > 0)
                .build();
    }

    public BookParams fromQueryParamsToBookParams(String title,
                                                  String category,
                                                  Integer page,
                                                  Integer size) {

        return BookParams.builder()
                .title(title)
                .category(category)
                .pageNumber(page)
                .pageSize(size)
                .build();
    }
}
