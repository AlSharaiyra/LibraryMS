package org.LibraryMS;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.LibraryMS.repository.BookRepository;
import org.LibraryMS.repository.MemberRepository;
import org.LibraryMS.service.BookService;
import org.LibraryMS.service.LibraryService;
import org.LibraryMS.service.MemberService;
import org.LibraryMS.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryMS");

        EntityManager entityManager = emf.createEntityManager();

        try {
            MemberRepository memberRepository = new MemberRepository();
            ValidationUtil validationUtil = new ValidationUtil();
            BookRepository bookRepository = new BookRepository(entityManager);
            // BookRepository needs EntityManager

            MemberService memberService = new MemberService(memberRepository, validationUtil);
            BookService bookService = new BookService(bookRepository);
            LibraryService libraryService = new LibraryService(bookService, memberService);

            InputHandler inputHandler = new InputHandler(memberService, bookService, libraryService);

            // Start the CLI
            inputHandler.start();
        } finally {
            entityManager.close();
            emf.close();
        }
    }
}