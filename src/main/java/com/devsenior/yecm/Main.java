package com.devsenior.yecm;

import org.apache.logging.log4j.LogManager;

import com.devsenior.yecm.components.Menu;

public class Main {
    public static void main(String[] args) {
        var log = LogManager.getLogger(Main.class);
        Menu menu = new Menu();
        log.info("Mostrando menu");
        menu.showMenu();
    }
}