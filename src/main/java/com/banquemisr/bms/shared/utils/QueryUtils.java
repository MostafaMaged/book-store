package com.banquemisr.bms.shared.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class QueryUtils {

    public static Pageable buildPageableObject(int pageNumber, int pageSize) {

        return PageRequest.of(pageNumber, pageSize);
    }
}
