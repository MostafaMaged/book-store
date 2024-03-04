CREATE TABLE "user"
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(255),
    deactivated BOOLEAN      NOT NULL DEFAULT FALSE,

    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP
);

CREATE TABLE book
(
    id           UUID         NOT NULL,
    title        VARCHAR(255) NOT NULL,
    author       VARCHAR(255) NOT NULL,
    description  VARCHAR(500),
    stock_level  INT          NOT NULL DEFAULT 1,
    version      BIGINT       NOT NULL DEFAULT 0,
    availability BOOLEAN      NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP,

    PRIMARY KEY (id),
    CONSTRAINT chk_stock_level CHECK (stock_level >= 0)
);

CREATE TABLE category
(
    id         UUID         NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    PRIMARY KEY (id)
);

CREATE TABLE book_category
(
    book_id     UUID NOT NULL,
    category_id UUID NOT NULL,

    PRIMARY KEY (book_id, category_id),
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE borrowing
(
    id          UUID,
    book_id     UUID      NOT NULL,
    customer_id UUID      NOT NULL,
    return_date TIMESTAMP NOT NULL,

    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP,

    PRIMARY KEY (id)
);