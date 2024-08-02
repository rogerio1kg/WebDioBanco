package me.webdio.banco.controller;

import me.webdio.banco.domain.Cliente;
import me.webdio.banco.domain.Conta;
import me.webdio.banco.domain.ContaCorrente;
import me.webdio.banco.domain.ContaPoupanca;
import me.webdio.banco.exceptions.SaldoException;

import java.util.Scanner;

public class BancoMenu {

    Cliente cliente = new Cliente();
    Conta corrente = new ContaCorrente(cliente);
    Conta poupanca = new ContaPoupanca(cliente);


    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu(){
        System.out.println("Informe seu nome: ");
        String nome = leitura.next();
        leitura.nextLine();
        cliente.setNome(nome);
        System.out.println("Qual conta gostaria de verificar? ");
        System.out.println("1. Poupança");
        System.out.println("2. Conta Corrente");
        var tipoConta = leitura.nextInt();
        leitura.nextLine();
        var opcao = -1;
        while(opcao != 0){
            var menu = """
                    1. Consultar Saldo
                    2. Realizar Saque
                    3. Realizar Transferência
                    4. Realizar Deposito
                    
                    0. Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    if (tipoConta == 1) {
                        System.out.println("Saldo: \n " + poupanca.getSaldo());
                        break;
                    } else {
                        System.out.println("Saldo: \n " + corrente.getSaldo());
                        break;
                    }
                case 2:
                    double valorSaque = 0;
                    if (tipoConta == 1) {
                        System.out.println("Informe o valor de Saque: ");
                        valorSaque = leitura.nextDouble();
                        leitura.nextLine();
                        try {
                            poupanca.sacar(valorSaque);
                            System.out.println("Saque Realizado!");
                            break;
                        } catch (SaldoException e) {
                            System.out.println("Sem saldo suficiente para realizar o saque!");
                            break;
                        }
                    } else if (tipoConta == 2) {
                        try {
                            corrente.sacar(valorSaque);
                            System.out.println("Saque Realizado!");
                            break;
                        } catch (SaldoException e) {
                            System.out.println("Sem saldo suficiente para realizar o saque!");
                            break;
                        }
                    }
                case 3:
                    System.out.println("Informe o tipo de conta destino: ");
                    System.out.println("1. Poupança para Poupança");
                    System.out.println("2. Poupança para Corrente");
                    System.out.println("3. Corrente para Corrente");
                    System.out.println("4. Corrente para Poupança");
                    int tipoTransferencia = leitura.nextInt();
                    leitura.nextLine();
                    System.out.println("Informe o valor que gostaria de transferir: ");
                    double valorTransferencia = leitura.nextDouble();
                    leitura.nextLine();

                    if (tipoConta == 1 && tipoTransferencia == 1) {
                        try {
                            poupanca.transferir(valorTransferencia, poupanca);
                            break;
                        } catch (SaldoException e) {
                            System.out.println("Sem saldo suficiente para transferencia");
                            break;
                        }
                    } else if (tipoConta == 1 && tipoTransferencia == 2) {
                        try {
                            poupanca.transferir(valorTransferencia, corrente);
                            break;
                        } catch (SaldoException e) {
                            System.out.println("Sem saldo suficiente para transferencia");
                            break;
                        }
                    } else if (tipoConta == 2 && tipoTransferencia == 3) {
                        try {
                            corrente.transferir(valorTransferencia, corrente);
                            break;
                        } catch (SaldoException e) {
                            System.out.println("Sem saldo suficiente para transferencia");
                            break;
                        }
                    }  else if (tipoConta == 2 && tipoTransferencia == 4) {
                        try {
                            corrente.transferir(valorTransferencia, poupanca);
                            break;
                        } catch (SaldoException e) {
                            System.out.println("Sem saldo suficiente para transferencia");
                            break;
                        }
                    }
                case 4:
                    System.out.println("Informe o valor que gostaria de depositar: ");
                    double valorDeposito = leitura.nextDouble();
                    leitura.nextLine();
                    if (tipoConta == 1) {
                        poupanca.depositar(valorDeposito);
                        System.out.println("Operação Realizada!");
                        break;
                    } else if (tipoConta == 2) {
                        corrente.depositar(valorDeposito);
                        System.out.println("Operação Realizada!");
                        break;
                    }
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }


}
