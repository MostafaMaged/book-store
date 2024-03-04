package com.banquemisr.bms.book.controller;

import com.banquemisr.bms.book.db.entity.Borrowing;
import com.banquemisr.bms.book.dto.request.BorrowBookRequest;
import com.banquemisr.bms.book.dto.request.CreateBookRequest;
import com.banquemisr.bms.book.dto.request.UpdateBookRequest;
import com.banquemisr.bms.book.dto.response.BookDTO;
import com.banquemisr.bms.book.mapper.BookMapper;
import com.banquemisr.bms.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.banquemisr.bms.book.controller.BookController.BOOK_API_PATH;
import static com.banquemisr.bms.shared.Constants.API_V1_PATH_PREFIX;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = API_V1_PATH_PREFIX + BOOK_API_PATH)
public class BookController {

    protected static final String BOOK_API_PATH = "/books";
    protected static final String ID_API_PATH = "/{id}";
    protected static final String RECOMMENDATIONS_API_PATH = "/recommendations";


    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping(ID_API_PATH)
    public ResponseEntity<BookDTO> getBookById(@PathVariable(value = "id") UUID bookId) {

        BookDTO book = bookService.fetchBookById(bookId);

        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {
        List<BookDTO> responseList = bookService.fetchBooks(
                bookMapper.fromQueryParamsToBookParams(title, category, page, size));

        return ResponseEntity.ok(responseList);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody CreateBookRequest request) {

        BookDTO createdBook = bookService.createBook(request);

        return ResponseEntity.ok(createdBook);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable UUID id, @Valid @RequestBody UpdateBookRequest request) {
        BookDTO updatedBook = bookService.updateBook(id, request);
        return ResponseEntity.ok(updatedBook);
    }

    @PostMapping(ID_API_PATH)
    public ResponseEntity<Borrowing> borrowBook(@PathVariable("id") UUID bookId, @Valid @RequestBody BorrowBookRequest request) {

        Borrowing borrowing = bookService.borrowBook(bookId, request);

        return ResponseEntity.ok(borrowing);
    }

    @GetMapping(RECOMMENDATIONS_API_PATH + ID_API_PATH)
    public ResponseEntity<List<BookDTO>> getRecommendations(@PathVariable("id") UUID userId) {

        List<BookDTO> recommendations = bookService.getRecommendations(userId);

        return ResponseEntity.ok(recommendations);
    }
}
