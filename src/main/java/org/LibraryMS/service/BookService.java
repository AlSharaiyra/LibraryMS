package org.LibraryMS.service;

import org.LibraryMS.model.Book;
import org.LibraryMS.repository.BookRepository;
import org.LibraryMS.util.IsbnValidator;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void addBook(String title, String author, String isbn, int year, int availableCopies){
        if (!IsbnValidator.isValidIsbn(isbn)) {
            throw new IllegalArgumentException("Invalid ISBN format.");
        }
        if (bookRepo.isbnExists(isbn)){
            throw new RuntimeException("ISBN already exists. Please provide a unique ISBN.");
        }

        Book book = new Book(title, author, isbn, year, availableCopies);
        bookRepo.save(book);
    }

    public Book getBookById(int bookId){
        Book book = bookRepo.findById(bookId);
        if (book == null){
            throw new IllegalArgumentException("Book with ID " + bookId + " not found.");
        }
        return book;
    }

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public void updateBook(Book updatedBook){
        if (!IsbnValidator.isValidIsbn(updatedBook.getIsbn())) {
            throw new IllegalArgumentException("Invalid ISBN format.");
        }
        // todo: fix this, will fail if used the same isbn as before
//        if (bookRepo.isbnExists(updatedBook.getIsbn())){
//            throw new RuntimeException("ISBN already exists. Please provide a unique ISBN.");
//        }

        bookRepo.save(updatedBook);
    }

    public void removeBookById(int bookId) {
        Book book = bookRepo.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book with ID " + bookId + " not found.");
        }
        bookRepo.deleteById(bookId);
    }

    public List<Book> searchBooksByTitle(String title){
        List<Book> books = bookRepo.findBooksByTitle(title);
        if (books.isEmpty()){
            throw new RuntimeException("No matches found.");
        }
        return books;
    }

    public List<Book> ListAvailableBooks(){
        return bookRepo.findAll().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .collect(Collectors.toList());
    }
}

// 4 features: add book + remove book + update book + list available books in file
// todo: implement search for books using title
