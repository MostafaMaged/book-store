package com.banquemisr.bms.book.utils;

import com.banquemisr.bms.book.db.entity.Book;
import com.banquemisr.bms.book.db.entity.Category;
import com.banquemisr.bms.book.dto.request.BookParams;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

final public class BookQueryUtils {

    public static Specification<Book> buildEvetntSpecification(BookParams params) {

        Specification<Book> specification = Specification.where(null);

        if (!StringUtils.isEmpty(params.getTitle())) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("title"), "%" + params.getTitle() + "%"));
        }

        if (!StringUtils.isEmpty(params.getCategory())) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                Join<Book, Category> categoryJoin = root.join("categories", JoinType.INNER);
                return criteriaBuilder.equal(categoryJoin.get("name"), params.getCategory());
            });
        }

        return specification;
    }
}
