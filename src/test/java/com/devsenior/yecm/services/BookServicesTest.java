package com.devsenior.yecm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.yecm.exceptions.NotFoundException;

public class BookServicesTest {
    private BookServices bookServices;
    private String author = "Yecm";
    private String title = "Pruebas unitarias 1";
    private String isbn = "1A2A4A35";
    
    @BeforeEach
    void setUp() {
        bookServices = new BookServices();
    }
    @Test
    void testAddBook() {
        bookServices.addBook(author, title, isbn);
        assertEquals(1, bookServices.getBooks().size());
    }

    @Test
    void testDeleteBookByIsbn() throws NotFoundException {
        bookServices.addBook(author, title, isbn);
        bookServices.deleteBookByIsbn(isbn);
        assertEquals(0, bookServices.getBooks().size());
    }

    @Test
    void testGetBookByIsbn() throws NotFoundException {
        bookServices.addBook(author, title, isbn);
        bookServices.getBookByIsbn(isbn);
        assertEquals(1, bookServices.getBooks().size());

    }

    @Test
    void testGetBooks() {
        // GIVEN
        var author_2 = "Jones";
        var title_2 = "Pruebas unitarias 2";
        var isbn_2 = "1A2A4A36";
        bookServices.addBook(author_2, title_2, isbn_2);
        bookServices.addBook(author, title, isbn);
        // WHEN
        var books = bookServices.getBooks();
        // THEN
        assertEquals(2, books.size());
    }
}
