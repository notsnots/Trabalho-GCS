package edu.pucrs.br.ui;

import edu.pucrs.br.item.ItemEntity;
import edu.pucrs.br.item.ItemTypes;
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
        // TODO: Implementar sistema de registro
    }

    private void listPlayersItens(){
        players.listItensByPrice();
    }
    //iuri em andamento
    private void registerItem(){ //registrar itens
        System.out.println("Nome do item: ");
        String nome = this.scanner.next();

        System.out.println("Descrição do item: ");
        String description= this.scanner.next();

        System.out.println("Valor do item: ");
        double valor = this.scanner.nextDouble();

        System.out.println("[===================]");
        System.out.println("Selecione o tipo de item:");
        System.out.println("1- Armor");
        System.out.println("2- Weapon");
        System.out.println("3- Potion");
        System.out.println("4- Key");
        System.out.println("5- Quest");
        System.out.println("[===================]");
        System.out.print("Digite o número da opção desejada: ");

        int Option = scanner.nextInt(); // escolha de qual tipo de item
        ItemTypes tipo = null;

        switch (Option) {
            case 1:
                tipo = ItemTypes.ARMOR;
                break;
            case 2:
                tipo = ItemTypes.WEAPON;
                break;
            case 3:
                tipo = ItemTypes.POTION;
                break;
            case 4:
                tipo = ItemTypes.KEY;
                break;
            case 5:
                tipo = ItemTypes.QUEST;
                break;
            default:
                System.out.println("ERRO: tenta de novo ai vai.");
                return;
        }

        long id = (long) (Math.random() * 1000); // trocar para uuid gerador de id unico adaptativo

        ItemEntity item = new ItemEntity(nome, description, tipo, valor);
        PlayerEntity currentPlayer = this.players.currentPlayer;

        currentPlayer.addItem(item);

   



    }

}
