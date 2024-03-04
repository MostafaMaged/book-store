package com.banquemisr.bms.book.dto.response;

import com.banquemisr.bms.book.db.entity.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private UUID id;

    @NotBlank(message = "title can't be blank")
    @Max(value = 100, message = "title can't be more than 100 characters")
    private String title;

    @NotBlank(message = "author can't be blank")
    @Max(value = 100, message = "author can't be more than 100 characters")
    private String author;

    @NotEmpty
    private List<Category> categories;

    @Max(value = 500, message = "description can't be more than 500 characters")
    private String description;

    @NotNull
    @PositiveOrZero
    private Integer stockLvl;

    @NotNull
    private Boolean isAvailable;

    private Instant createdAt;

    private Instant updatedAt;
}
