package com.devsenior.yecm.model;

import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String email;
    private LocalDate registerDate;

    public User(int id, String username, String email, LocalDate registerDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.registerDate = registerDate;
    }

    public User(int id, String username, String email) {
        this(id, username, email, LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

}
