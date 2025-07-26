package com.devsenior.yecm.services;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.yecm.exceptions.NotFoundException;
import com.devsenior.yecm.model.Book;

public class BookServices {
    private List<Book> books;

    public BookServices() {
        this.books = new ArrayList<>();
    }

    public void addBook(String author, String title, String isbn) {
        this.books.add(new Book(author, title, isbn));
    }

    public Book getBookByIsbn(String isbn) throws NotFoundException {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        throw new NotFoundException("Libro con el isbn: " + isbn + " no encontrado");
    }

    public List<Book> getBooks() {
        return books;
    }

    public void deleteBookByIsbn(String isbn) throws NotFoundException {
        Book bookToDelete = null;

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                bookToDelete = book;
                break;
            }
        }
        if (bookToDelete != null) {
            books.remove(bookToDelete);
            System.out.println("Libro con el isbn: " + isbn + " eliminado");

        } else {
            throw new NotFoundException("Libro con el isbn: " + isbn + " no encontrado");
        }
    }
}