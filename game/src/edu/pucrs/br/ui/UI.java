package edu.pucrs.br.ui;

import edu.pucrs.br.player.Players;

import java.util.Scanner;

public class UI {
    private Scanner scanner = new Scanner(System.in);
    private Players players;

    public UI(Players players) {
        this.players = players;

        this.initUI();
    }

    private void initUI() {
        System.out.println("Seja bem-vindo ao NOME_DO_JOGO!");

        this.showAuthMenu();
    }

    private void showAuthMenu() {
        System.out.println("[===================]");
        System.out.println("1 - Login");
        System.out.println("2 - Cadastro");
        System.out.println("[===================]");
        System.out.print("Digite a opcao desejada: ");

        try {
            do {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    default:
                        System.out.println("Opcao invalida. Redigite.");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Erro ao ler a opção: " + e.getMessage());
        }

    }

    private void login() {
        // TODO: Implementar sistema de login
    }

    private void register() {
        // TODO: Implementar sistema de registro
    }
}
