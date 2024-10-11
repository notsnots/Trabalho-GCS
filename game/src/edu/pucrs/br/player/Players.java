package edu.pucrs.br.player;

import edu.pucrs.br.item.ItemEntity;
import java.util.ArrayList;
import java.util.Comparator;

public class Players {
    private final ArrayList<PlayerEntity> players = new ArrayList<>();
    private PlayerEntity currentPlayer;

    public PlayerEntity getByEmail(String email) {
        return players.stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public PlayerEntity getCurrentPlayer() {
        return this.currentPlayer;
    }

    /*
        Este método é responsável por adicionar um jogador à lista de jogadores.
        Ele está com um try/catch pois a função .add pode lançar uma exceção caso o jogador
        não seja cadastrado da forma correta.
    */
    public void addPlayer(PlayerEntity player) {
        try {
            players.add(player);
        } catch (Exception exception) {
            System.out.println("Erro ao adicionar jogador: " + exception.getMessage());
        }
    }

    public boolean login(String email, String pin) {
        PlayerEntity player = players.stream()
                .filter(p -> p.getEmail().equals(email) && p.getPasswordPin().equals(pin))
                .findFirst()
                .orElse(null);

        if (player != null) {
            this.currentPlayer = player;
            return true;
        }

        return false;
    }

    public void listItensByPrice() {

        if (players.isEmpty()) {
            System.out.println("Nenhum jogador encontrado!");
            return;
        }

        for (PlayerEntity player : this.players) {
            ArrayList<ItemEntity> items = player.getItems();

            if (items.isEmpty()) {
                System.out.println("Nenhum item encontrado para o player: " + player.getFullName());
                continue;
            }
            System.out.println("Itens do Player: " + player.getFullName() + ": ");

            items.sort(Comparator.comparing(ItemEntity::getPrice));

            for (ItemEntity item : items) {
                System.out.println(item.toString());
            }
            System.out.println();
        }
    }

    public void listPlayersByInventoryPrice(){
        if (players.isEmpty()) {
            System.out.println("Nenhum jogador encontrado!");
            return;

        }

        for(int i=0;i<players.size();i++){
            boolean trocou = false;

            for(int j=players.size()-1;j>i;j--){
                if(players.get(j-1).getValorTotalInventário() > players.get(j).getValorTotalInventário()){
                    PlayerEntity temp = players.get(j);
                    players.set(j, players.get(j-1));
                    players.set(j-1, temp);
                    trocou = true;
                }
            }

            if(!trocou){
                break;
            }
        }

        int i=1;
        for(PlayerEntity player: players){
            System.out.println(i+"° - Nome: "+player.getFullName()+"\nValor do Inventário: "+player.getValorTotalInventário()+" {");
            for(ItemEntity item : player.getItems()){
                System.out.println("    "+item.getName()+", R$"+item.getPrice());
            }
            System.out.println("}");
            System.out.println();
            i++;
        }
    }

    public void searchItem(String nome) {
        String term = nome.toLowerCase();
        boolean found = false;
        if (players.isEmpty()) {
            System.out.println("Nenhum jogador encontrado!");
            return;
        }
        for (PlayerEntity player : players) {
            ArrayList<ItemEntity> items = player.getItems();

            for (ItemEntity item : items) {
                if (item.getName().toLowerCase().contains(term) || item.getDescription().toLowerCase().contains(term) ||
                        item.getType().name().toLowerCase().contains(term)) {

                    System.out.println("Jogador: " + player.getFullName() + " - " + item.getName() + " " + item.getType()
                            + " " + item.getDescription());
                    found = true;

                }
            }
        }
        if (!found) {
            System.out.println("Nao existe item com essa palavra");
        }
    }


    public void listaItensOrdemAlfabetica() {

        if (players.isEmpty()) {

            System.out.println("Nenhum jogador encontrado!");
            return;

        }

        for (PlayerEntity player : this.players) {
            ArrayList<ItemEntity> items = player.getItems();

            if (items.isEmpty()) {

                System.out.println("Nenhum item encontrado para o player: " + player.getFullName());
                continue;

            }

            items.sort(Comparator.comparing(ItemEntity::getName));

            System.out.println("Itens do jogador " + currentPlayer.getFullName() + ":");
            for (ItemEntity item : items) {

                System.out.println(item.toString());

            }
        }
    }

    public int getPlayersSize() {
        return players.size();
    }

    public int getTotalItens() {
        int total = 0;
        for (PlayerEntity player : players) {
            total += player.getItems().size();
        }
        return total;
    }

    public int getTotalItensPrices() {
        int total = 0;
        for (PlayerEntity player : players) {
            for (ItemEntity item : player.getItems()) {
                total += item.getPrice();
            }
        }
        return total;
    }
}