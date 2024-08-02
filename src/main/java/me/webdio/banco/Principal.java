package me.webdio.banco;

import me.webdio.banco.controller.BancoMenu;
import me.webdio.banco.domain.Cliente;
import me.webdio.banco.exceptions.SaldoException;

public class Principal {
    public static void main(String[] args) throws SaldoException {

        BancoMenu menu = new BancoMenu();

        menu.exibeMenu();
    }
}
