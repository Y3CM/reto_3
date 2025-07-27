package com.devsenior.yecm.components;

import java.util.Scanner;

import com.devsenior.yecm.exceptions.NotFoundException;
import com.devsenior.yecm.services.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu {

    private static final Logger logger = LogManager.getLogger(Menu.class);
    private final Scanner scan = new Scanner(System.in);
    private final BookServices bookServices;
    private final UserServices userServices;
    private final LoanService loanService;

    public Menu() {
        this.bookServices = new BookServices();
        this.userServices = new UserServices();
        this.loanService = new LoanService(bookServices, userServices);
        logger.debug("Servicios inicializados correctamente.");
    }

    public void showMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Biblioteca ---");
            System.out.println("Bienvenido a la biblioteca virtual");
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Agregar libro");
            System.out.println("2. Agregar usuario");
            System.out.println("3. Listar libros");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Prestar libro");
            System.out.println("6. Devolver libro");
            System.out.println("7. Salir");

            int opcion = 0;
            try {
                String input = scan.nextLine();
                logger.debug("Opcion ingresada por el usuario: " + input);
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Intente con un numero del 1 al 7.");
                logger.warn("Entrada no numerica ingresada en menu", e);
                continue;
            }

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el autor del libro: ");
                        String autor = scan.nextLine();
                        logger.debug("Autor ingresado: " + autor);

                        System.out.print("Ingrese el titulo del libro: ");
                        String titulo = scan.nextLine();
                        logger.debug("Titulo ingresado: " + titulo);

                        System.out.print("Ingrese el ISBN del libro: ");
                        String isbn = scan.nextLine();
                        logger.debug("ISBN ingresado: " + isbn);

                        bookServices.addBook(titulo, autor, isbn);
                        logger.info("Libro agregado: '" + titulo + "' de " + autor + " [ISBN: " + isbn + "]");
                        break;

                    case 2:
                        System.out.print("Ingrese el ID del usuario: ");
                        int id = Integer.parseInt(scan.nextLine());
                        logger.debug("ID de usuario ingresado: " + id);

                        System.out.print("Ingrese el username: ");
                        String username = scan.nextLine();
                        logger.debug("Username ingresado: " + username);

                        System.out.print("Ingrese el email: ");
                        String email = scan.nextLine();
                        logger.debug("Email ingresado: " + email);

                        userServices.addUser(id, username, email);
                        logger.info("👤 Usuario agregado: " + username + " (ID: " + id + ", Email: " + email + ")");
                        break;

                    case 3:
                        var books = bookServices.getBooks();
                        logger.debug("Obteniendo lista de libros. Total: " + books.size());

                        if (!books.isEmpty()) {
                            System.out.println("Libros almacenados:");
                            books.forEach(System.out::println);
                        } else {
                            System.out.println("No hay libros almacenados.");
                            logger.warn("El usuario intento listar libros pero no hay ninguno en la coleccion.");
                        }
                        break;

                    case 4:
                        var users = userServices.getUsers();
                        logger.debug("Obteniendo lista de usuarios. Total: " + users.size());

                        if (!users.isEmpty()) {
                            System.out.println("Usuarios registrados:");
                            users.forEach(System.out::println);
                        } else {
                            System.out.println("No hay usuarios registrados.");
                            logger.warn("El usuario intento listar usuarios pero no hay ninguno registrado.");
                        }
                        break;

                    case 5:
                        System.out.print("Ingrese el ISBN del libro a prestar: ");
                        String isbnPrestamo = scan.nextLine();
                        logger.debug("ISBN a prestar: " + isbnPrestamo);

                        System.out.print("Ingrese el ID del usuario que toma el prestamo: ");
                        int userIdPrestamo = Integer.parseInt(scan.nextLine());
                        logger.debug("ID del usuario que solicita prestamo: " + userIdPrestamo);

                        loanService.addLoan(isbnPrestamo, userIdPrestamo);
                        logger.info("Prestamo realizado - Usuario ID: " + userIdPrestamo + ", Libro ISBN: "
                                + isbnPrestamo);
                        break;

                    case 6:
                        System.out.print("Ingrese el ISBN del libro a devolver: ");
                        String isbnDevolver = scan.nextLine();
                        logger.debug("ISBN a devolver: " + isbnDevolver);

                        System.out.print("Ingrese el ID del usuario que devuelve el libro: ");
                        int userIdDevolver = Integer.parseInt(scan.nextLine());
                        logger.debug("ID del usuario que realiza devolucion: " + userIdDevolver);

                        loanService.returnBook(userIdDevolver, isbnDevolver);
                        logger.info("Devolucion realizada - Usuario ID: " + userIdDevolver + ", Libro ISBN: "
                                + isbnDevolver);
                        break;

                    case 7:
                        salir = true;
                        System.out.println("¡Hasta luego!");
                        logger.info("Aplicacion cerrada correctamente por el usuario.");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        logger.warn("Opcion fuera de rango seleccionada: " + opcion);
                        break;
                }
            } catch (NotFoundException e) {
                System.err.println("Error: " + e.getMessage());
                logger.warn("Error de negocio controlado: " + e.getMessage(), e);
            } catch (Exception e) {
                System.out.println("Ocurrio un error inesperado.");
                logger.error("Excepcion no controlada en el menu: ", e);
            }
        }
    }
}
