package edu.pucrs.br.player;

import java.util.ArrayList;

public class Players {
    private final ArrayList<PlayerEntity> players = new ArrayList<>();

    public void addPlayer(PlayerEntity player) {
        try {
            players.add(player);
        } catch (Exception exception) {
            System.out.println("Erro ao adicionar jogador: " + exception.getMessage());
        }
    }
}
