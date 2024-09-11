package edu.pucrs.br.mockup;

import edu.pucrs.br.item.ItemEntity;
import edu.pucrs.br.item.ItemTypes;

public class ItemMockup {
    public static ItemEntity createWeapon() {
        return new ItemEntity(
            "Espada Longa",
            "Uma espada longa feita de aço",
            ItemTypes.WEAPON,
            100.0
        );
    }

    public static ItemEntity createArmor() {
        return new ItemEntity(
            "Armadura de Couro",
            "Uma armadura de couro leve",
            ItemTypes.ARMOR,
            50.0
        );
    }
}
