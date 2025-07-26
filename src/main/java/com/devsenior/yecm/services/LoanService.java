package com.devsenior.yecm.services;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.yecm.exceptions.NotFoundException;
import com.devsenior.yecm.model.*;

public class LoanService {
    private List<Loan> loans;
    private List<Loan> finishedLoans;
    private BookServices bookServices;
    private UserServices userServices;

    public LoanService(BookServices bookServices, UserServices userServices) {
        this.loans = new ArrayList<>();
        this.finishedLoans = new ArrayList<>();
        this.bookServices = bookServices;
        this.userServices = userServices;
    }

    public void addLoan(String isbn, int userId) throws NotFoundException {
        var book = bookServices.getBookByIsbn(isbn);
        var user = userServices.getUserById(userId);
        var loan = new Loan(user, book);
        loans.add(loan);

    }

    public void returnBook(int userId, String isbn) throws NotFoundException {
        var book = bookServices.getBookByIsbn(isbn);
        var user = userServices.getUserById(userId);

        Loan loanToReturn = null;

        for (Loan loan : loans) {
            if (loan.getUser().equals(user) && loan.getBook().equals(book)
                    && loan.getState().equals(LoanState.STARTED)) {
                loan.setState(LoanState.FINISHED);
                loanToReturn = loan;
                break;
            }
        }
        if (loanToReturn != null) {
            loans.remove(loanToReturn);
            finishedLoans.add(loanToReturn);
            return;
        }
        throw new NotFoundException(
                "El libro con el isbn: " + isbn + " no esta prestado al usuario con el id: " + userId);

    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<Loan> getFinishedLoans() {
        return finishedLoans;
    }

    public BookServices getBookServices() {
        return bookServices;
    }

    public UserServices getUserServices() {
        return userServices;
    }
}
