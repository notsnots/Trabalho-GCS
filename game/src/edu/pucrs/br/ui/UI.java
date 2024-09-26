package edu.pucrs.br.ui;

import edu.pucrs.br.item.ItemEntity;
import edu.pucrs.br.player.PlayerEntity;
import edu.pucrs.br.player.Players;
import edu.pucrs.br.trade.TradeEntity;
import edu.pucrs.br.trade.Trades;
import java.util.ArrayList;
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

    private void authMenuOptions() {
        System.out.println("[===================]");
        System.out.println("1 - Login");
        System.out.println("2 - Cadastro");
        System.out.println("[===================]");
        System.out.print("Digite a opcao desejada: ");
    }

    private void showAuthMenu() {
       this.authMenuOptions();

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
                        authMenuOptions();
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
                        this.createTradeProposal();
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
        System.out.print("[Login] Digite o seu email: ");
        String email = this.scanner.next();
        System.out.print("[Login] Digite o seu PIN: ");
        String pin = this.scanner.next();

        boolean result = this.players.login(email,pin);
        if(result){
            System.out.println("[Login] Bem-vindo(a) " + email + "!");
            this.onLogin();
        }else{
            System.out.println("[Login] Email ou PIN incorretos!");
            this.showAuthMenu();
        }
    }

    private void register() {
        System.out.print("[Cadastro] Digite o seu nome: ");
        String fullName = this.scanner.next();
        System.out.print("[Cadastro] Digite o seu email:");
        String email = this.scanner.next();
        System.out.print("[Cadastro] Digite o seu PIN (6 digitos): ");
        String pin = this.scanner.next();

        if (pin.length() != 6) {
            System.out.println("[Cadastro] O PIN deve conter 6 digitos. ");
            this.showAuthMenu();
            return;
        }

        if (this.players.getByEmail(email) != null) {
            System.out.println("[Cadastro] Email ja esta em uso. ");
            this.showAuthMenu();
            return;
        }

        PlayerEntity newPlayer = new PlayerEntity(email, fullName, pin);
        this.players.addPlayer(newPlayer);

        System.out.println("[Cadastro] O novo jogador foi cadastrado com sucesso!");
        this.showAuthMenu();
    }

    private void listPlayersItens(){
        players.listItensByPrice();
    }

    private void createTradeProposal() {
        PlayerEntity targetPlayer;

        while (true) {
            System.out.print("[Trade] Digite o email do jogador que deseja trocar (para cancelar digite NA): ");
            String targetEmail = this.scanner.next();

            if (targetEmail.equalsIgnoreCase("na")) {
                System.out.println("[Trade] Operação cancelada");
                return;
            }

            targetPlayer = this.players.getByEmail(targetEmail);

            if (targetPlayer != null) {
                break;
            } else {
                System.out.println("[Trade] Jogador não encontrado. Tente novamente.");
            }
        }

        ArrayList<ItemEntity> targetItems = targetPlayer.getItems();
        if (targetItems.size() <= 0) {
            System.out.println("[Trade] O jogador " + targetPlayer.getFullName() + " não possui nenhum item disponível para troca.");
            return;
        }

        StringBuilder availableItems = new StringBuilder();
        for (int i = 0; i < targetItems.size(); i++) {
            ItemEntity item = targetItems.get(i);
            availableItems
                    .append(i + 1)
                    .append(" - ")
                    .append(item.getName())
                    .append(" : ")
                    .append("$")
                    .append(item.getPrice())
                    .append(" : ")
                    .append(item.getType())
                    .append("\n");
        }

        System.out.println("[Trade] Jogador encontrado: " + targetPlayer.getFullName());
        System.out.println("[Trade] Itens disponíveis para troca: ");
        System.out.println(availableItems.toString());
        System.out.print("[Trade] Digite o número do item que deseja trocar (para cancelar digite 0): ");

        int targetItemIndex = this.scanner.nextInt();
        if (targetItemIndex <= 0) {
            System.out.println("[Trade] Operação cancelada");
            return;
        }

        if (targetItemIndex > targetItems.size()) {
            System.out.println("[Trade] Item não encontrado. Tente novamente.");
            return;
        }

        ItemEntity targetItem = targetItems.get(targetItemIndex - 1);
        System.out.print("[Trade] Você confirma que deseja trocar o item " + targetItem.getName() + " por um item seu? (S/N) ");

        String confirm = this.scanner.next();
        if (confirm.equalsIgnoreCase("n")) {
            System.out.println("[Trade] Operação cancelada");
            return;
        }

        System.out.println("[Trade] Certo! Agora escolha o seu item que deseja dar em troca:");
        PlayerEntity currentPlayer = this.players.getCurrentPlayer();
        ArrayList<ItemEntity> currentPlayerItems = currentPlayer.getItems();

        if (currentPlayerItems.size() <= 0) {
            System.out.println("[Trade] Você não possui nenhum item disponível para troca.");
            return;
        }

        availableItems = new StringBuilder();
        for (int i = 0; i < currentPlayerItems.size(); i++) {
            ItemEntity item = currentPlayerItems.get(i);
            availableItems
                    .append(i + 1)
                    .append(" - ")
                    .append(item.getName())
                    .append(" : ")
                    .append("$")
                    .append(item.getPrice())
                    .append(" : ")
                    .append(item.getType())
                    .append("\n");
        }

        System.out.println("[Trade] Seus itens disponíveis para troca: ");
        System.out.println(availableItems.toString());
        System.out.print("[Trade] Digite o número do item que deseja trocar (para cancelar digite 0):");

        int sourceItemIndex = this.scanner.nextInt();
        if (sourceItemIndex <= 0) {
            System.out.println("[Trade] Operação cancelada");
            return;
        }

        if (sourceItemIndex > currentPlayerItems.size()) {
            System.out.println("[Trade] Item não encontrado. Tente novamente.");
            return;
        }

        ItemEntity sourceItem = currentPlayerItems.get(sourceItemIndex - 1);
        TradeEntity trade = new TradeEntity(currentPlayer, targetPlayer, sourceItem.getId(), targetItem.getId());
        this.trades.createTrade(trade);

        System.out.println("[Trade] Proposta de troca enviada com sucesso!");
        this.showOptionsMenu();
    }
}