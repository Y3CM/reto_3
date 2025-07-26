package com.devsenior.yecm.services;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.yecm.exceptions.NotFoundException;
import com.devsenior.yecm.model.User;

public class UserServices {
    private List<User> users;

    public UserServices() {
        users = new ArrayList<>();
    }

    public void addUser(int id, String username, String email) {
        users.add(new User(id, username, email));
    }

    public User getUserById(int id) throws NotFoundException {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NotFoundException("usuario con el id " + id + "no encontrado");
    }

    public void updateEmailUserById(int id, String email) throws NotFoundException {
        for (User user : users) {
            if (user.getId() == id) {
                user.setEmail(email);
                System.out.println("Email cambiado con exito new email: " + user.getEmail());
                return;
            }
        }
        throw new NotFoundException("usuario con el id " + id + "no encontrado");
    }

    public void updateUsernameById(int id, String username) throws NotFoundException {
        for (User user : users) {
            if (user.getId() == id) {
                user.setUsername(username);
                System.out.println("Username cambiado con exito, new username:" + user.getUsername());
                return;
            }
        }

        throw new NotFoundException("usuario con el id " + id + "no encontrado");
    }

    public void deleteUserById(int id) throws NotFoundException {
        User userToDelete = null;

        for (User user : users) {
            if (user.getId() == id) {
                userToDelete = user;
                break; 
            }
        }

        if (userToDelete != null) {
            users.remove(userToDelete);
            System.out.println("Usuario con el id " + id + " eliminado");
        } else {
            throw new NotFoundException("Usuario con el id " + id + " no encontrado");
        }
    }

    public List<User> getUsers() {
        return users;
    }
    
}
