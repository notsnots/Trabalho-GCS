package edu.pucrs.br.ui;

import edu.pucrs.br.player.PlayerEntity;
import edu.pucrs.br.player.Players;
import edu.pucrs.br.trade.Trades;

import java.util.Scanner;

public class UI {
    private Scanner scanner = new Scanner(System.in);
    private Players players;
    private Trades trades;

    public UI(Players players, Trades trades) {
        this.players = players;
        this.trades = trades;

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

    private void onLogin() {
        // TODO: Mostrar todas as propostas de troca pendentes para o jogador logado (implementação: Eduardo Rosa)

        this.showOptionsMenu();
    }

    private void showOptionsMenu() {
        System.out.println("[===================]");
        System.out.println("1 - [Admin] Cadastrar novo item");
        System.out.println("2 - [Admin] Listar os itens de todos os jogadores, ordenado por valor");
        System.out.println("3 - [Player] Listar meus itens, ordenado alfabeticamente");
        System.out.println("4 - [Player] Buscar itens por termo (parte do nome, descrição ou categoria)");
        System.out.println("5 - [Player] Propor troca de itens para outro jogador");
        System.out.println("6 - [Player] Mostrar minhas propostas de troca pendentes");
        System.out.println("7 - [Player] Detalhes sobre proposta de troca");
        System.out.println("8 - [Admin] Estatísticas do jogo");
        System.out.println("[===================]");
        System.out.print("Digite a opcao desejada: ");

        try {
            do {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Pendente de implementação: Iuri Queiroz");
                        break;
                    case 2:
                        this.listPlayersItens();
                        break;
                    case 3:
                        System.out.println("Pendente de implementação: Felipe Rambor");
                        break;
                    case 4:
                        System.out.println("Pendente de implementação: Luiza Mitchell");
                        break;
                    case 5:
                        System.out.println("Pendente de implementação: Bernardo Kirsch");
                        break;
                    case 6:
                        System.out.println("Pendente de implementação: Eduardo Rosa");
                        break;
                    case 7:
                        System.out.println("Pendente de implementação: Pedro Petrini | Aceite ou recusa de troca: Erick Carpes");
                        break;
                    case 8:
                        System.out.println("Pendente de implementação: Lucas Arieta");
                        break;

                    default: System.out.println("Opcao invalida. Redigite.");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Erro ao ler a opção: " + e.getMessage());
        }
    }

    private void login() {
        System.out.println("Digite o seu email: ");
        String email = this.scanner.next();
        System.out.println("Digite o seu PIN: ");
        String pin = this.scanner.next();

        boolean result = this.players.login(email,pin);
        if(result){
            System.out.println("Login efetuado com sucesso!");
            this.onLogin();
        }else{
            System.out.println("Erro ao fazer login!");
            this.showAuthMenu();
        }
    }

    private void register() {
       //solicita aos usuarios os dados para cadastro
        System.out.println("Digite o seu nome completo: ");
        String fullName = this.scanner.next();
        System.out.println("Digite o seu email: \n");
        String email = this.scanner.next();
        System.out.println("Digite o seu PIN (6 digitos): ");
        String pin = this.scanner.next();

        //verifica se o pin tem 6 digitos
        if (pin.length() != 6) {
            System.out.println("O PIN deve conter 6 digitos. ");
            return;
        }
        //verifica se o email ja esta em uso
        if (this.players.getByEmail(email) != null) {
            System.out.println("Email ja esta em uso. ");
            return;
        }
       //cria um novo jogador
        PlayerEntity newPlayer = new PlayerEntity(email, fullName, pin);
        this.players.addPlayer(newPlayer);
        System.out.println("Cadastro realizado com sucesso! ");
    }

    private void listPlayersItens(){
        players.listItensByPrice();
    }
}
