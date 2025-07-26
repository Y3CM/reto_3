package com.devsenior.yecm.model;

import java.time.LocalDate;

public class Loan {
private User user;
private Book book;
private LoanState state;
private LocalDate loanDate;
public Loan(User user, Book book, LoanState state, LocalDate loanDate) {
    this.user = user;
    this.book = book;
    this.state = state;
    this.loanDate = loanDate;
}
public Loan(User user, Book book) {
   this(user,book,LoanState.STARTED,LocalDate.now());
}
public Loan(User user, Book book, LocalDate loanDate) {
    this(user,book,LoanState.STARTED,loanDate);
}
public User getUser() {
    return user;
}
public void setUser(User user) {
    this.user = user;
}
public Book getBook() {
    return book;
}
public void setBook(Book book) {
    this.book = book;
}
public LoanState getState() {
    return state;
}
public void setState(LoanState state) {
    this.state = state;
}
public LocalDate getLoanDate() {
    return loanDate;
}
public void setLoanDate(LocalDate loanDate) {
    this.loanDate = loanDate;
}


}
