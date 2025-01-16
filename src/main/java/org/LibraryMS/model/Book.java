package org.LibraryMS.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String author;
    @Column(unique = true)
    private String isbn;
    @Column(name = "published_year")
    private int publishedYear;
    @Column(name = "available_copies")
    private int availableCopies;

    @ElementCollection
    @CollectionTable(name = "book_borrowers", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "borrower_id")
    private List<Integer> borrowersIds = new ArrayList<>();

    public Book() {
    }

    public Book(int id, String title, String author, String isbn, int publishedYear, int availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.availableCopies = availableCopies;
    }

    public Book(String title, String author, String isbn, int publishedYear, int availableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.availableCopies = availableCopies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public List<Integer> getBorrowersIds() {
        return borrowersIds;
    }

    public void setBorrowersIds(List<Integer> borrowersIds) {
        this.borrowersIds = borrowersIds;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", published_year=" + publishedYear +
                ", available_copies=" + availableCopies +
                '}';
    }
}
