package com.devsenior.yecm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.devsenior.yecm.exceptions.NotFoundException;
import com.devsenior.yecm.model.*;

public class LoanServiceTest {
    private LoanService loanService;
    private UserServices userServices;
    private BookServices bookServices;

    @BeforeEach
    void setUp() {
        this.bookServices = Mockito.mock(BookServices.class);
        this.userServices = Mockito.mock(UserServices.class);
        this.loanService = new LoanService(bookServices, userServices);
    }

    @Test
    void testAddloanWithExistingBookAndUser() throws NotFoundException {
        // GIVEN
        var id = 350;
        var isbn = "1A2A4A35";
        var mockUser = new User(id, "Yecm", "yecm@gmail.com");
        var mockBook = new Book("Pruebas Unitarias 1", "Yecm", isbn);
        Mockito.when(userServices.getUserById(id)).thenReturn(mockUser);
        Mockito.when(bookServices.getBookByIsbn(isbn)).thenReturn(mockBook);
        // WHEN
        loanService.addLoan(isbn, id);
        // THEN
        var loans = loanService.getLoans();
        assertEquals(1, loans.size());
        var loan = loans.get(0);
        assertNotNull(loan.getUser());
        assertEquals(LoanState.STARTED, loan.getState());
        assertNotNull(loan.getBook());
    }

    @Test
    void testGetBookServices() throws NotFoundException {
        // GIVEN
        var id = 350;
        var isbn = "1A2A4A35";
        var mockUser = new User(id, "Yecm", "yecm@gmail.com");
        var mockBook = new Book("Pruebas Unitarias 1", "Yecm", isbn);
        Mockito.when(userServices.getUserById(id)).thenReturn(mockUser);
        Mockito.when(bookServices.getBookByIsbn(isbn)).thenReturn(mockBook);
        // WHEN
        loanService.getBookServices();
        // THEN
        assertEquals(bookServices, loanService.getBookServices());
    }

    @Test
    void testGetFinishedLoans() throws NotFoundException {
        // GIVEN
        var id = 350;
        var isbn = "1A2A4A35";
        var mockUser = new User(id, "Yecm", "yecm@gmail.com");
        var mockBook = new Book("Pruebas Unitarias 1", "Yecm", isbn);
        Mockito.when(userServices.getUserById(id)).thenReturn(mockUser);
        Mockito.when(bookServices.getBookByIsbn(isbn)).thenReturn(mockBook);
        // WHEN
        loanService.addLoan(isbn, id);
        loanService.returnBook(id, isbn);
        // THEN
        var finishedLoans = loanService.getFinishedLoans();
        assertEquals(1, finishedLoans.size());
    }

    @Test
    void testGetLoans() throws NotFoundException {
        // GIVEN
        var id = 350;
        var isbn = "1A2A4A35";
        var mockUser = new User(id, "Yecm", "yecm@gmail.com");
        var mockBook = new Book("Pruebas Unitarias 1", "Yecm", isbn);
        Mockito.when(userServices.getUserById(id)).thenReturn(mockUser);
        Mockito.when(bookServices.getBookByIsbn(isbn)).thenReturn(mockBook);
        // WHEN
        loanService.addLoan(isbn, id);
        // THEN
        var loans = loanService.getLoans();
        assertEquals(1, loans.size());

    }

    @Test
    void testGetUserServices() throws NotFoundException {
        // GIVEN
        var id = 350;
        var isbn = "1A2A4A35";
        var mockUser = new User(id, "Yecm", "yecm@gmail.com");
        var mockBook = new Book("Pruebas Unitarias 1", "Yecm", isbn);
        Mockito.when(userServices.getUserById(id)).thenReturn(mockUser);
        Mockito.when(bookServices.getBookByIsbn(isbn)).thenReturn(mockBook);
        // WHEN
        loanService.getUserServices();
        // THEN
        assertEquals(userServices, loanService.getUserServices());

    }

    @Test
    void testReturnBook() throws NotFoundException {
        // GIVEN
        var id = 350;
        var isbn = "1A2A4A35";
        var mockUser = new User(id, "Yecm", "yecm@gmail.com");
        var mockBook = new Book("Pruebas Unitarias 1", "Yecm", isbn);
        Mockito.when(userServices.getUserById(id)).thenReturn(mockUser);
        Mockito.when(bookServices.getBookByIsbn(isbn)).thenReturn(mockBook);
        loanService.addLoan(isbn, id);
        assertEquals(1, loanService.getLoans().size());
        // WHEN
        loanService.returnBook(id, isbn);
        // THEN
        assertEquals(0, loanService.getLoans().size());
    }
}
