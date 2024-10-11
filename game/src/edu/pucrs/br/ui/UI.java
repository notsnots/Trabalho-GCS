package edu.pucrs.br.ui;

import edu.pucrs.br.item.ItemEntity;
import edu.pucrs.br.item.ItemTypes;
import edu.pucrs.br.item.LootBox;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import edu.pucrs.br.player.PlayerEntity;
import edu.pucrs.br.player.Players;
import edu.pucrs.br.trade.TradeEntity;
import edu.pucrs.br.trade.TradeStatus;
import edu.pucrs.br.trade.Trades;

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
        System.out.println("0 - Sair do Programa");
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
                    case 0:
                        System.exit(0);
                    default:
                        authMenuOptions();
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Erro ao ler a opção: " + e.getMessage());
        }
    }

    private void onLogin() {
        // TODO: Mostrar todas as propostas de troca pendentes para o jogador logado
        // (implementação: Eduardo Rosa)

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
        System.out.println("8 - [Player] Listar jogadores pelo valor do inventário");
        System.out.println("9 - [Player] Abrir Caixa do Herói");
        System.out.println("10 - [Admin] Estatísticas do jogo");
        System.out.println("0 - [Any] - Sair do programa");
        System.out.println("[===================]");
        System.out.print("Digite a opcao desejada: ");

        try {
            do {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        this.registerItem();
                        this.showOptionsMenu();
                        break;
                    case 2:
                        this.listPlayersItens();
                        this.showOptionsMenu();
                        break;
                    case 3:
                        this.listPlayersItemByAlphabeticalOrder();
                        this.showOptionsMenu();
                        break;
                    case 4:
                        this.searchItems();
                        this.showOptionsMenu();
                        break;
                    case 5:
                        this.createTradeProposal();
                        this.showOptionsMenu();
                        break;
                    case 6:
                        this.showPendingTrades();
                        this.showOptionsMenu();
                        break;
                    case 7:
                        this.showDetailsAboutTrade();
                        this.showOptionsMenu();
                        break;
                    case 8:
                        this.listPlayersByInventoryPrice();
                        this.showOptionsMenu();   
                        break;
                    case 9:
                         this.openLootBox();
                        this.showOptionsMenu();
                        break;
                    case 10:
                        this.showGameStatistics();
                        this.showOptionsMenu();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Opcao invalida. Redigite.");
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

        boolean result = this.players.login(email, pin);
        if (result) {
            this.onLogin();
            System.out.println("[Login] Bem-vindo(a) " + email + "!");
            this.onLogin();
        } else {
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

    private void listPlayersItens() {
        players.listItensByPrice();
    }

    private void registerItem(){
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

        ItemEntity item = new ItemEntity(nome, description, tipo, valor);
        PlayerEntity currentPlayer = this.players.getCurrentPlayer();
        currentPlayer.addItem(item);
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
            System.out.println("[Trade] O jogador " + targetPlayer.getFullName()
                    + " não possui nenhum item disponível para troca.");
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
        System.out.print(
                "[Trade] Você confirma que deseja trocar o item " + targetItem.getName() + " por um item seu? (S/N) ");

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

    private void searchItems() {
        System.out.println("Digite o nome do termo que gostaria de buscar");
        String nome = this.scanner.next();
        players.searchItem(nome);
    }

    private void listPlayersItemByAlphabeticalOrder() {

        players.listaItensOrdemAlfabetica();

    }

    private void showPendingTrades() {
        PlayerEntity p = this.players.getCurrentPlayer();

        ArrayList<TradeEntity> pendingTrades = this.trades.getPendingTrades(p);
        if (pendingTrades.isEmpty()) {
            System.out.println("[Trade] Nenhuma proposta de troca encontrada.");
            return;
        }

        int count = 1;
        System.out.println("[Trade] Propostas de troca pendentes: ");

        for (TradeEntity trade : pendingTrades) {
            ItemEntity sourceItem = p.getItem(trade.getSourceItem());
            ItemEntity targetItem = trade.getTargetPlayer().getItem(trade.getTargetItem());

            System.out.println(count + " - " +
                    "Jogador solicitante: " + trade.getSourcePlayer().getFullName() + " | " +
                    "Jogador solicitado: " + trade.getTargetPlayer().getFullName() + " | " +
                    "Item oferecido: " + sourceItem.getName() + " | " +
                    "Item solicitado: " + targetItem.getName());

            count++;
        }
    }

    private void showDetailsAboutTrade() {
        PlayerEntity p = this.players.getCurrentPlayer();

        ArrayList<TradeEntity> pendingTrades = this.trades.getPendingTrades(p);
        if (pendingTrades.isEmpty()) {
            System.out.println("[Trade] Nenhuma proposta de troca encontrada.");
            return;
        }

        System.out.print("[Trade] Digite o número da proposta de troca que deseja visualizar: ");
        int tradeIndex = this.scanner.nextInt();

        if (tradeIndex <= 0 || tradeIndex > pendingTrades.size()) {
            System.out.println("[Trade] Proposta de troca não encontrada.");
            return;
        }

        TradeEntity trade = pendingTrades.get(tradeIndex - 1);
        PlayerEntity targetPlayer = trade.getTargetPlayer();

        ItemEntity sourceItem = p.getItem(trade.getSourceItem());
        ItemEntity targetItem = targetPlayer.getItem(trade.getTargetItem());

        UUID sourceItemID = sourceItem.getId();
        UUID targetItemID = targetItem.getId();


        String confirm;
        do{
            System.out.println("[Trade] Detalhes da proposta de troca: ");
            System.out.println("Jogador solicitante: " + trade.getSourcePlayer().getFullName());
            System.out.println("Jogador solicitado: " + trade.getTargetPlayer().getFullName());
            System.out.println("Item oferecido: " + sourceItem.getName());
            System.out.println("Item solicitado: " + targetItem.getName());
            System.out.print("[Trade] Deseja aceitar a proposta de troca?");
            System.out.println("Digite 'S' para sim | 'N' para não | 'P' para deixar ela pendente: ");

            confirm = this.scanner.next();
            if(!confirm.equalsIgnoreCase("s") && !confirm.equalsIgnoreCase("n") && !confirm.equalsIgnoreCase("p")){
                System.out.println("Opção inválida, tente novamente!");
                System.out.println("--------------------------------");
            }
        }while(!confirm.equalsIgnoreCase("s") && !confirm.equalsIgnoreCase("n") && !confirm.equalsIgnoreCase("p"));

        confirm = confirm.toLowerCase();
        switch (confirm){
            case "s":
                System.out.println("Proposta de troca aceita com sucesso!");
                trade.setStatus(TradeStatus.ACCEPTED);

                p.addItem(targetItem);
                p.removeItem(sourceItemID);

                targetPlayer.addItem(sourceItem);
                targetPlayer.removeItem(targetItemID);
                break;
            case "n":
                System.out.println("Proposta de troca rejeitada com sucesso!");
                trade.setStatus(TradeStatus.DENIED);
                break;
            case "p":
                System.out.println("Proposta de troca continuará pendente!");
                break;
        }

    }

    public void showGameStatistics() {
        int players = this.players.getPlayersSize();
        int itens = this.players.getTotalItens();
        int totalItensPrices = this.players.getTotalItensPrices();
        int totalTrades = this.trades.getTotalTrades();
        int acceptedTrades = this.trades.getTotalAcceptedTrades();
        int deniedTrades = this.trades.getTotalDeniedTrades();
        int pendingTrades = this.trades.getTotalPendingTrades();

        System.out.println("Quantidade de jogadores: " + players);
        System.out.println("Quantidade de itens: " + itens);
        System.out.println("Soma total dos preços dos itens: " + totalItensPrices);
        System.out.println("Quantidade total de propostas de troca: " + totalTrades);
        System.out.println("Quantidade de propostas de troca aceitas: " + acceptedTrades);
        System.out.println("Quantidade de propostas de troca recusadas: " + deniedTrades);
        System.out.println("Quantidade de propostas de troca pendentes: " + pendingTrades);
    }

    public void listPlayersByInventoryPrice(){
        players.listPlayersByInventoryPrice();
    }

    public void openLootBox(){
        LootBox box = new LootBox();
        this.players.getCurrentPlayer().addItem(box.openLootBox());
    
        System.out.println();
        
    }
}
