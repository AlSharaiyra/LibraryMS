package org.LibraryMS.util;

import org.LibraryMS.model.Book;

import java.io.*;
import java.util.List;

public class FileWriterTask implements Runnable {
    private final List<Book> availableBooks;

    public FileWriterTask(List<Book> availableBooks) {
        this.availableBooks = availableBooks;
    }

    @Override
    public void run() {
        File file = new File("available_books.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Available Books List\n");
//            writer.newLine();
            writer.write("---------------------\n");
//            writer.newLine();

            availableBooks.forEach(book -> {
                try {
                    writer.write("ID: " + book.getId() + "\n");
//                writer.newLine();
                    writer.write("Title: " + book.getTitle() + "\n");
//                writer.newLine();
                    writer.write("Author: " + book.getAuthor() + "\n");
//                writer.newLine();
                    writer.write("ISBN: " + book.getIsbn() + "\n");
//                writer.newLine();
                    writer.write("Published Year: " + book.getPublishedYear() + "\n");
//                writer.newLine();
                    writer.write("Available Copies: " + book.getAvailableCopies() + "\n");
//                writer.newLine();
                    writer.write("---------------------" + "\n");
//                writer.newLine();
                } catch (IOException e) {
                    System.err.println("Error writing book: " + book + " - " + e.getMessage());
                }
            });

            System.out.println("Available books have been listed in available_books.txt.");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
