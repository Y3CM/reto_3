package com.devsenior.yecm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.yecm.exceptions.NotFoundException;

public class UserServicesTest {

    private UserServices userServices;
    private String username = "Yecm";
    private String email = "Yecm@gmail";
    private int id = 350;

    @BeforeEach
    void setUp() {
        userServices = new UserServices();
    }

    @Test
    void testAddUser() {
        // WHEN
        userServices.addUser(id, username, email);
        // THEN
        assertEquals(1, userServices.getUsers().size());
    }

    @Test
    void testDeleteUserById() throws NotFoundException {
        // GIVEN
        userServices.addUser(id, username, email);
        // WHEN
        userServices.deleteUserById(id);
        // THEN
        assertEquals(0, userServices.getUsers().size());
    }

    @Test
    void testGetUserById() throws NotFoundException {
        userServices.addUser(id, username, email);
        userServices.getUserById(id);
        assertEquals(1, userServices.getUsers().size());
    }

    @Test
    void testGetUsers() {
        // GIVEN
        var id_2 = 2;
        var name = "Jones";
        var email_2 = "Jones@gmail";
        userServices.addUser(id, username, email);
        userServices.addUser(id_2, name, email_2);
        // WHEN
        var users = userServices.getUsers();
        // THEN
        assertEquals(2, users.size());
    }

    @Test
    void testUpdateEmailUserById() throws NotFoundException {
        userServices.addUser(id, username, email);
        userServices.updateEmailUserById(id, "Jones@gmail");
        assertEquals("Jones@gmail", userServices.getUserById(id).getEmail());
    }

    @Test
    void testUpdateUsernameById() throws NotFoundException {
        userServices.addUser(id, username, email);
        userServices.updateUsernameById(id, "Jones");
        assertEquals("Jones", userServices.getUserById(id).getUsername());
    }
}
