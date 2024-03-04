package com.banquemisr.bms.book.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {

    @NotBlank(message = "title can't be blank")
    @Size(min = 10, max = 100, message = "title must be between 10 and 100 characters")
    private String title;

    @NotBlank(message = "author can't be blank")
    @Size(min = 3, max = 50, message = "author name must be between 3 and 50 characters")
    private String author;

    @Size(min = 10, max = 500, message = "description must be between 10 and 500 characters")
    private String description;

    @NotNull
    @PositiveOrZero
    private Integer stockLvl;
}
