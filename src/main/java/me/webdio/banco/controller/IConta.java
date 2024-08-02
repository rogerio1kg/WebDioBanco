package me.webdio.banco.controller;

import me.webdio.banco.exceptions.SaldoException;

public interface IConta {

    void sacar(Double valor) throws SaldoException;

    void depositar(Double valor);

    void transferir(Double valor, IConta contaDestino) throws SaldoException;

}