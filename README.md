Online Bookstore Management System
===============================================
The system should offer customers the ability to browse through available books, request books for borrowing, and
manage the borrowing and return dates. Additionally, it should enable administrators to maintain the inventory by adding or
deleting books as needed.

## Running the Application

### Docker Compose
You can run the application using Docker by following these steps:

1. Make sure Docker is installed on your machine.
2. Open a terminal/command prompt and navigate to the project directory.
3. Run the following command to start the application using Docker Compose.
4. Docker Compose will build the necessary containers and start the application.
5. Once the application is running, you can access it using the specified endpoints.

### Running from IDE
Alternatively, you can run the application from an IDE like IntelliJ:

1. Make sure you have Java and Maven installed on your machine.
2. Clone the project repository to your local machine.
3. Open the project in your IDE (e.g., IntelliJ).
4. Build the project to resolve dependencies and compile the code.
5. Locate the main class (e.g., `Application.java`) and run it as a Java application.
6. The application will start, and you can access it using the specified endpoints.

## Authentication

### `POST /auth/login`
- This endpoint handles user login.
- It expects a JSON request body of type `AuthenticationRequest`.
- The `AuthenticationRequest` contains login credentials.
- The method `login` in the `AuthService` is called to process the login request.
- It returns a `ResponseEntity` containing an `AuthenticationResponse`.


- **Note:** For testing, you can use Postman to login using the following user:
    - Email: user@example.com
    - Password: password1
- The response will contain a JWT token. Make sure to save this token for later authorization when testing other endpoints.

### `POST /auth/register`
- This endpoint handles user registration.
- It expects a JSON request body of type `RegistrationRequest`.
- The `RegistrationRequest` contains user registration details.
- The method `register` in the `AuthService` is called to process the registration request.
- It returns a `ResponseEntity` containing an `AuthenticationResponse`.

## Book operations

### `GET /books/{id}`
- This endpoint retrieves a specific book by its `id`.
- The `id` is extracted from the path variable.
- The method `fetchBookById` in the `BookService` is called with the `bookId` to retrieve the book details.
- It returns a `ResponseEntity` containing a `BookDTO` (Data Transfer Object).

### `GET /books`
- This endpoint retrieves a list of books.
- It can accept optional query parameters: `title`, `category`, `page`, and `size`.
- The method `fetchBooks` in the `BookService` is called with the appropriate parameters.
- It returns a `ResponseEntity` containing a list of `BookDTO` objects.

### `POST /books`
- This endpoint creates a new book.
- It expects a JSON request body of type `CreateBookRequest`.
- The `CreateBookRequest` contains the details of the book to be created.
- The method `createBook` in the `BookService` is called to create the book.
- It returns a `ResponseEntity` containing the created `BookDTO`.

### `PATCH /books/{id}`
- This endpoint updates an existing book with the specified `id`.
- The `id` is extracted from the path variable.
- It expects a JSON request body of type `UpdateBookRequest` containing the updated book details.
- The method `updateBook` in the `BookService` is called with the `id` and `request` to update the book.
- It returns a `ResponseEntity` containing the updated `BookDTO`.

### `POST /books/{id}`
- This endpoint allows a user to borrow a book.
- The `id` is extracted from the path variable and represents the book's `id`.
- It expects a JSON request body of type `BorrowBookRequest`.
- The method `borrowBook` in the `BookService` is called with the `bookId` and `request` to handle the borrowing process.
- It returns a `ResponseEntity` containing a `Borrowing` object.

### `GET /books/recommendations/{id}`
- This endpoint retrieves book recommendations for a specific user.
- The `id` is extracted from the path variable and represents the user's `id`.
- The method `getRecommendations` in the `BookService` is called with the `userId` to fetch book recommendations for the user.
- It returns a `ResponseEntity` containing a list of `BookDTO` objects representing the recommended books.