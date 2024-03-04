package com.banquemisr.bms.book.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookParams {

    @NotBlank
    @Max(value = 100, message = "title can't be more than 100 characters")
    private String title;

    private String category;

    private Integer pageNumber = 0;

    private Integer pageSize = 10;
}
