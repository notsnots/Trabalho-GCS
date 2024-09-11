package edu.pucrs.br.player;

import java.util.ArrayList;

public class Players {
    private final ArrayList<PlayerEntity> players = new ArrayList<>();

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
}
