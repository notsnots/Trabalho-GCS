package edu.pucrs.br.player;

import edu.pucrs.br.item.ItemEntity;
import edu.pucrs.br.trade.TradeEntity;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerEntity {
    private final String email;
    private final String fullName;
    private final String passwordPin;

    private ArrayList<ItemEntity> items = new ArrayList<>();

    public PlayerEntity(
        String email,
        String fullName,
        String passwordPin
    ) {
        this.email = email;
        this.fullName = fullName;

        if (passwordPin.length() != 6) throw new IllegalArgumentException("O PIN deve conter 6 dígitos.");

        this.passwordPin = passwordPin;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPasswordPin() {
        return passwordPin;
    }

    public void addItem(ItemEntity item) {
        items.add(item);
    }

    public void removeItem(UUID itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
    }

    public void hasItem(UUID itemId) {
        items.stream()
            .filter(item -> item.getId().equals(itemId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("O item não pertence ao jogador."));
    }

    public ArrayList<ItemEntity> getItems() {
        return items;
    }

    public ItemEntity getItem(UUID itemId) {
        return items.stream()
            .filter(item -> item.getId().equals(itemId))
            .findFirst()
            .orElse(null);
    }

    public ItemEntity getFirst(){
        if (items.isEmpty()) {
            System.out.println("O jogador não tem itens.");
        }
        return items.get(0);
    }
}
