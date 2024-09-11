package edu.pucrs.br.mockup;

import edu.pucrs.br.player.PlayerEntity;
import edu.pucrs.br.player.Players;

public class PlayerMockup {
    public static void insert(Players players) {
        for (int i = 0; i < 10; i++) {
            PlayerEntity player = new PlayerEntity("player" + i + "@gmail.com", "Player " + i, "123456");

            player.addItem(ItemMockup.createWeapon());
            player.addItem(ItemMockup.createArmor());

            players.addPlayer(player);
        }

        System.out.println("[PlayerMockup::insert] Mockup inserido com sucesso!");
    }
}
