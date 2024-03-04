package com.banquemisr.bms.book.service;

import com.banquemisr.bms.book.db.entity.Book;
import com.banquemisr.bms.book.db.entity.Borrowing;
import com.banquemisr.bms.book.db.entity.Category;
import com.banquemisr.bms.book.db.repository.BookRepository;
import com.banquemisr.bms.book.db.repository.BorrowingRepository;
import com.banquemisr.bms.book.db.repository.CategoryRepository;
import com.banquemisr.bms.book.dto.request.BookParams;
import com.banquemisr.bms.book.dto.request.BorrowBookRequest;
import com.banquemisr.bms.book.dto.request.CreateBookRequest;
import com.banquemisr.bms.book.dto.request.UpdateBookRequest;
import com.banquemisr.bms.book.dto.response.BookDTO;
import com.banquemisr.bms.book.mapper.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.banquemisr.bms.book.utils.BookQueryUtils.buildEvetntSpecification;
import static com.banquemisr.bms.shared.utils.QueryUtils.buildPageableObject;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BorrowingRepository borrowingRepository;
    private final BookMapper bookMapper;

    public List<BookDTO> fetchBooks(BookParams params) {

        Specification<Book> bookSpecification = buildEvetntSpecification(params);

        Pageable pageable = buildPageableObject(params.getPageNumber(), params.getPageSize());

        Page<Book> bookPage = bookRepository.findAll(bookSpecification, pageable);

        return bookPage.getContent().stream()
                .map(bookMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO fetchBookById(UUID id) {

        Book book = bookRepository.getReferenceById(id);

        return bookMapper.fromEntityToDTO(book);
    }

    public BookDTO createBook(CreateBookRequest request) {

        List<Category> categories = categoryRepository.findAllById(request.getCategories());

        Book book = bookRepository.save(bookMapper.fromCreateRequestToEntity(request));
        book.setCategories(categories);

        Book persisitedBook = bookRepository.save(book);

        return bookMapper.fromEntityToDTO(persisitedBook);
    }

    public BookDTO updateBook(UUID id, UpdateBookRequest request) {

        Book fetchedBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        bookMapper.updateBook(fetchedBook, request);

        Book persisitedBook = bookRepository.save(fetchedBook);

        return bookMapper.fromEntityToDTO(persisitedBook);
    }

    @Transactional
    public Borrowing borrowBook(UUID id, BorrowBookRequest request) {

        Book fetchedBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        if (!fetchedBook.isAvailability()) {
            throw new IllegalArgumentException("book with id " + id + "cannot be borrowed due to availability");
        }

        fetchedBook.setStockLevel(fetchedBook.getStockLevel() - 1);
        bookRepository.save(fetchedBook);

        return borrowingRepository.save(bookMapper.fromBorrowingRequestToEntity(id, request));
    }

    public List<BookDTO> getRecommendations(UUID userId) {

        List<Book> recommendedBooks = bookRepository.findBooksByCategoryOfLastBorrowedBook(userId);

        return recommendedBooks.stream()
                .map(bookMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }
}
