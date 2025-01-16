package org.LibraryMS;

import org.LibraryMS.model.Book;
import org.LibraryMS.model.Member;
import org.LibraryMS.service.BookService;
import org.LibraryMS.service.LibraryService;
import org.LibraryMS.service.MemberService;
import org.LibraryMS.util.FileWriterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class InputHandler {
    Scanner scanner = new Scanner(System.in);

    private static final Logger logger = LoggerFactory.getLogger(InputHandler.class);
    private final MemberService memberService;
    private final BookService bookService;
    private final LibraryService libraryService;

    public InputHandler(MemberService memberService, BookService bookService, LibraryService libraryService) {
        this.memberService = memberService;
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    public void start() {
        int choice = 0;
        logger.info("System started...");
        do {
            System.out.println("\n----- Library Management System -----");
            System.out.println("1. Create a new member");
            System.out.println("2. Update member`s email or phone number");
            System.out.println("3. Delete member");
            System.out.println("4. Find member by ID");
            System.out.println("5. List all books borrowed by a member");
            System.out.println("6. Add a new book");
            System.out.println("7. Update book information");
            System.out.println("8. Remove a book");
            System.out.println("9. Search for books using title");
            System.out.println("10. List available books in a file");
            System.out.println("11. Borrow a book");
            System.out.println("12. Return a book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleAddMember();
                    break;
                case 2:
                    handleUpdateMember();
                    break;
                case 3:
                    handleDeleteMember();
                    break;
                case 4:
                    handleFindMemberById();
                    break;
                case 5:
                    handleListBorrowedBooks();
                    break;
                case 6:
                    handleAddBook();
                    break;
                case 7:
                    handleUpdateBook();
                    break;
                case 8:
                    handleRemoveBook();
                    break;
                case 9:
                    handleSearchBooksByTitle();
                    break;
                case 10:
                    handleListAvailableBooksInFile();
                    break;
                case 11:
                    handleBorrowBook();
                    break;
                case 12:
                    handleReturnBook();
                    break;
                case 0:
                    handleExit();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 13);
    }

    public void handleAddMember() {
        System.out.println("Enter member name: ");
        String name = scanner.nextLine();
        System.out.println("Enter member email: ");
        String email = scanner.nextLine();
        System.out.println("Enter member phone number: ");
        String phoneNumber = scanner.nextLine();

        try {
            memberService.registerMember(name, email, phoneNumber);
            logger.info("Member added successfully.");
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }

    public void handleUpdateMember() {
        System.out.println("Enter member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Member member;

        try {
            member = memberService.getMemberById(id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Enter new email: ");
        String email = scanner.nextLine();
        member.setEmail(email);

        System.out.println("Enter new phone number: ");
        String phoneNumber = scanner.nextLine();
        member.setPhoneNumber(phoneNumber);

        try {
            memberService.updateMember(member);
            logger.info("Member updated successfully.");
        } catch (RuntimeException e){
            System.err.println(e.getMessage());
        }

    }

    public void handleDeleteMember() {
        System.out.println("Enter member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            memberService.deleteMember(id);
            logger.info("Member deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void handleFindMemberById() {
        System.out.println("Enter member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Member member;

        try {
            member = memberService.getMemberById(id);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println(member.toString());
    }

    public void handleListBorrowedBooks() {
        System.out.println("Enter member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        List<Book> borrowedBooks;
        try {
            borrowedBooks = memberService.findBorrowedBooksByMemberId(id);
            logger.info("Listed borrowed books for member with Id: " + id);
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
            return;
        }
        // Use stream to print borrowed books
        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrowed books found for member ID " + id);
            logger.info("No borrowed books found for member with Id: " + id);
        } else {
            System.out.println("Borrowed Books for Member ID " + id + ":");
            borrowedBooks.forEach(book -> {
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("Published Date: " + book.getPublishedYear());
                System.out.println("Available Copies: " + book.getAvailableCopies());
                System.out.println("---------------------------");
            });
        }
    }

    public void handleAddBook() {
        System.out.println("Enter book`s title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book`s author: ");
        String author = scanner.nextLine();
        System.out.println("Enter book`s ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter published year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter available copies: ");
        int availableCopies = scanner.nextInt();
        scanner.nextLine();

        try {
            bookService.addBook(title, author, isbn, year, availableCopies);
            logger.info("Book added successfully.");
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
        }
    }

    public void handleUpdateBook() {
        System.out.println("Enter book`s ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Book book;
        try {
            book = bookService.getBookById(id);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Enter book`s title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book`s author: ");
        String author = scanner.nextLine();
        System.out.println("Enter book`s ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter available copies: ");
        int availableCopies = scanner.nextInt();
        scanner.nextLine();

        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setAvailableCopies(availableCopies);

        try {
            bookService.updateBook(book);
            logger.info("Book updated successfully.");
        } catch (RuntimeException e){
            System.err.println(e.getMessage());
        }

    }

    public void handleRemoveBook() {
        System.out.println("Enter book`s ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            bookService.removeBookById(id);
            logger.info("Book removed successfully.");

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void handleSearchBooksByTitle() {
        System.out.println("What to search about?");
        String title = scanner.nextLine();
        List<Book> result;
        try {
            result = bookService.searchBooksByTitle(title);
            logger.info("Searched for book title: " + title);
            logger.info("Found " + result.size() + " books.");
        } catch (RuntimeException e){
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Search results for \"" + title + "\" are: ");
        result.forEach(book -> {
            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Published Date: " + book.getPublishedYear());
            System.out.println("Available Copies: " + book.getAvailableCopies());
            System.out.println("---------------------------");
        });
    }

    public void handleListAvailableBooksInFile() {
        List<Book> availableBooks = bookService.ListAvailableBooks();

        if (availableBooks.isEmpty()) {
            System.out.println("No available books to list.");
            return;
        }

        FileWriterTask fileWriterTask = new FileWriterTask(availableBooks);
        Thread fileWriteThread = new Thread(fileWriterTask);
        logger.info("Started a new Thread to print available books in a file.");

        fileWriteThread.start();
        logger.info("Available books have been listed in available_books.txt.");
    }

    public void handleBorrowBook() {
        System.out.println("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        try {
            libraryService.borrowBook(bookId, memberId);
            logger.info("Member with Id: " + memberId + " has borrowed book with Id: " + bookId + " successfully.");
        } catch (RuntimeException e){
            System.err.println(e.getMessage());
        }
    }

    public void handleReturnBook() {
        System.out.println("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        try {
            libraryService.returnBook(bookId, memberId);
            logger.info("Member with Id: " + memberId + " has returned book with Id: " + bookId + " successfully.");
        } catch (RuntimeException e){
            System.err.println(e.getMessage());
        }
    }

    public void handleExit() {
        System.out.println("Are you sure you want to exit? (yes/NO):");
        String input = scanner.nextLine().trim();

        if ("yes".equals(input)){
            System.out.println("Exiting the application. Goodbye!");
            logger.info("Exiting the system...");
            System.exit(0);
        } else {
            System.out.println("Returning to the main menu.");
        }
    }
}
