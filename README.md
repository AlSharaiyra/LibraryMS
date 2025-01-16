# Library Management System

## Purpose

The **Library Management System** (LibraryMS) is designed to simplify and streamline the management of books in a library. It allows users to search, borrow, and return books, while also providing features for administrators to track borrowed books and manage their inventory. The system aims to be a simple, intuitive solution for managing library operations and enhancing user experience.

## Features

- **Search Books**: Users can search for books by title, author, or ISBN.
- **Borrow Books**: Users can borrow books, with automatic tracking of borrowed books.
- **Return Books**: Users can return borrowed books, updating the library’s inventory.
- **Admin Panel**: Admins can manage book inventory, track borrowed books, and generate reports.
- **Email and Phone Validation**: Ensure that users' email addresses and phone numbers are valid using regular expressions.
- **Logging**: All actions performed in the system are logged to a file for auditing and debugging purposes. This includes actions like borrowing and returning books.
- **Multi-threading**: Multi-threading is used to list available books and write them to a file. This helps in improving the performance and efficiency of the operation.
- **JPA and JDBC**: The application demonstrates the use of both JPA and JDBC. JPA is used for managing entities, while JDBC is used for performing lower-level database operations. This allows for comparison and understanding of the differences between these two approaches.

## Known Limitations
- **Basic Functionality**: While the application provides basic book management and borrowing functionality, it lacks advanced features like book reservations or fine calculations for late returns.
- **Database Handling**: Although JDBC is used for certain operations, all database access could be unified under JPA for consistency in production-grade applications.
## Conclusion
The Library Management System demonstrates how to handle both basic and advanced operations within a library system. By using JPA and JDBC together, it highlights the strengths and trade-offs of each approach. The addition of logging and multithreading enhances the usability and performance of the application.
