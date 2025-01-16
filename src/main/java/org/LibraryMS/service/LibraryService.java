package org.LibraryMS.service;

import org.LibraryMS.model.Book;
import org.LibraryMS.model.Member;


public class LibraryService {
    private final BookService bookService;
    private final MemberService memberService;

    public LibraryService(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public void borrowBook(int bookId, int memberId) {
        Book book = bookService.getBookById(bookId);
        Member member = memberService.getMemberById(memberId);
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies of the book.");
        }

        if (book.getBorrowersIds().contains(memberId)) {
            throw new RuntimeException("Book with ID " + bookId + " is already borrowed by member with ID " + memberId + ".");
        }
        member.getBorrowedBooksIds().add(book.getId());
        book.getBorrowersIds().add(member.getId());
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        memberService.updateMember(member);
        bookService.updateBook(book);
    }

    public void returnBook(int bookId, int memberId) {
        Book book = bookService.getBookById(bookId);
        Member member = memberService.getMemberById(memberId);

        if (!book.getBorrowersIds().contains(memberId)) {
            throw new RuntimeException("Book with ID " + bookId + " was not borrowed by member with ID " + memberId + ".");
        }
        member.getBorrowedBooksIds().remove(Integer.valueOf(bookId));
        book.getBorrowersIds().remove(Integer.valueOf(memberId));
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        memberService.updateMember(member);
        bookService.updateBook(book);
    }
}

// 2 features: borrow book + return book