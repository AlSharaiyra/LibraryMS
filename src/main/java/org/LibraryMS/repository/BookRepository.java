package org.LibraryMS.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.LibraryMS.model.Book;

import java.util.List;

public class BookRepository {
    private final EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean isbnExists(String isbn) {
        String query = "SELECT COUNT(b) FROM Book b WHERE b.isbn = :isbn";
        long count = entityManager.createQuery(query, Long.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
        return count > 0;
    }

    public void save(Book book) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    public void deleteById(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Book book = entityManager.find(Book.class, id);
            if (book != null) {
                entityManager.remove(book);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Book> findBooksByTitle(String title) {
        String query = "SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(:title)";
        return entityManager.createQuery(query, Book.class)
                .setParameter("title", "%" + title.toLowerCase() + "%")
                .getResultList();
    }
}
