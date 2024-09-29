package edu.pucrs.br.player;

import edu.pucrs.br.item.ItemEntity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

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

        if(players.isEmpty()) {
            System.out.println("Nenhum jogador encontrado!");
            return;
        }

        for (PlayerEntity player : this.players)
        {
            ArrayList<ItemEntity> items = player.getItems();

            if(items.isEmpty()){
                System.out.println("Nenhum item encontrado para o player: " + player.getFullName());
                continue;
            }
                System.out.println("Itens do Player: " + player.getFullName()+": ");

            items.sort(Comparator.comparing(ItemEntity::getPrice));

            for(ItemEntity item : items){
                System.out.println(item.toString());
            }
            System.out.println();
        }
    }
    public void SearchItem(String nome){
        String term = nome.toLowerCase();
        boolean found = false;
        if (players.isEmpty()) {
            System.out.println("Nenhum jogador encontrado!");
        }
            for (int i = 0; i < players.size(); i++) {
                PlayerEntity player = players.get(i);
                ArrayList<ItemEntity> items = player.getItems();

                for (int j = 0; j < items.size(); j++) {
                    ItemEntity item = items.get(j);

                    if(item.getName().toLowerCase().contains(term) || item.getDescription().toLowerCase().contains(term) ||
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


    }
