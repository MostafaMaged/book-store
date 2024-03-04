INSERT INTO "user" (id, name, email, password, role, deactivated)
VALUES
    ('84b1e540-9c3e-4f27-84bc-fb279a99663f', 'Admin', 'admin@example.com', '$2a$10$ltdo/4AYBxortmkuSau96uhyzi5p5CgBnBRSl31WXAFlkKI6sGrwm', 'ADMIN', false),
    ('6d0c7343-28de-4e46-a2c9-30c3c1c305c8', 'User', 'user@example.com', '$2a$10$LK5XcmsC3aml9rB4rlFB9.kKbfyO82RIrqx2nXLGTUyczx7iWBy6.', 'USER', false);

INSERT INTO category (id, name)
VALUES
    ('1f2b5fc6-0ddc-4e94-b074-8bb1b40d31e7', 'FICTION'),
    ('2f1e08a9-bc18-47d2-b574-9ad03c4e15ae', 'ACTION'),
    ('3e6c4935-076f-45e8-8658-5bb5ed5eb51f', 'SPACE'),
    ('4e56ac0d-4a21-4346-bd61-1fc432c7a40d', 'MYSTERY'),
    ('5db1fd9d-7eae-49b3-b92e-fae6acfb8e80', 'BIOGRAPHY');

INSERT INTO book (id, title, author, description, stock_level, availability, version)
VALUES
    ('8a918bf9-0d36-4d8b-8ee6-217d4c60d16d', 'To Kill a Mockingbird', 'Harper Lee', 'A novel set in the American South during the 1930s.', 5, true, 0),
    ('19fe3653-1e48-4a89-82c3-2d5c47bb690e', '1984', 'George Orwell', 'A dystopian novel set in a totalitarian regime.', 3, true, 0),
    ('2e83e6cb-4744-4136-bf4f-89fb30a8ff43', 'The Great Gatsby', 'F. Scott Fitzgerald', 'A novel depicting the American Dream during the Jazz Age.', 7, true, 0),
    ('32fe72c0-b932-4cf4-9f07-6f1791682ab7', 'Pride and Prejudice', 'Jane Austen', 'A classic romance novel set in Regency-era England.', 2, true, 0),
    ('4c89b1f4-02b5-4188-b0a1-7504d8c6813e', 'The Catcher in the Rye', 'J.D. Salinger', 'A coming-of-age novel following the protagonist Holden Caulfield.', 10, true, 0);

INSERT INTO book_category (book_id, category_id)
VALUES
    ('8a918bf9-0d36-4d8b-8ee6-217d4c60d16d', '1f2b5fc6-0ddc-4e94-b074-8bb1b40d31e7'),
    ('19fe3653-1e48-4a89-82c3-2d5c47bb690e', '1f2b5fc6-0ddc-4e94-b074-8bb1b40d31e7'),
    ('2e83e6cb-4744-4136-bf4f-89fb30a8ff43', '2f1e08a9-bc18-47d2-b574-9ad03c4e15ae'),
    ('32fe72c0-b932-4cf4-9f07-6f1791682ab7', '4e56ac0d-4a21-4346-bd61-1fc432c7a40d'),
    ('4c89b1f4-02b5-4188-b0a1-7504d8c6813e', '5db1fd9d-7eae-49b3-b92e-fae6acfb8e80');

INSERT INTO borrowing (id, book_id, customer_id, return_date)
VALUES
    ('8a918bf9-0d36-4d8b-8ee6-217d4c60d16d', '8a918bf9-0d36-4d8b-8ee6-217d4c60d16d', '84b1e540-9c3e-4f27-84bc-fb279a99663f', '2024-02-17 10:15:30.00'),
    ('19fe3653-1e48-4a89-82c3-2d5c47bb690e', '19fe3653-1e48-4a89-82c3-2d5c47bb690e', '6d0c7343-28de-4e46-a2c9-30c3c1c305c8', '2024-02-17 11:30:45.00'),
    ('2e83e6cb-4744-4136-bf4f-89fb30a8ff43', '2e83e6cb-4744-4136-bf4f-89fb30a8ff43', '84b1e540-9c3e-4f27-84bc-fb279a99663f', '2024-02-18 09:45:15.00'),
    ('32fe72c0-b932-4cf4-9f07-6f1791682ab7', '32fe72c0-b932-4cf4-9f07-6f1791682ab7', '6d0c7343-28de-4e46-a2c9-30c3c1c305c8', '2024-02-18 13:20:00.00'),
    ('4c89b1f4-02b5-4188-b0a1-7504d8c6813e', '4c89b1f4-02b5-4188-b0a1-7504d8c6813e', '84b1e540-9c3e-4f27-84bc-fb279a99663f', '2024-02-19 14:55:30.00');