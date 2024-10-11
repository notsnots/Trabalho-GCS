package edu.pucrs.br.item;

import java.util.ArrayList;

public class LootBox {
    ArrayList <ItemEntity> itensCaixa;

    public LootBox(){
        itensCaixa = new ArrayList<>();

        itensCaixa.add(new ItemEntity("Espada Lendaria de Fogo", "Espada encontrada na caverna do dragão lendário Nosferatus", ItemTypes.WEAPON, 10000));
        itensCaixa.add(new ItemEntity("Arco de Athenas", "Arco que sempre acerta o primeiro disparo. Pertencia a deusa Athenas", ItemTypes.WEAPON, 10000));
        itensCaixa.add(new ItemEntity("Armadura do Gigante", "Forjada na era dos gigantes, é considerada a armadura mais resistente de todas", ItemTypes.ARMOR, 10000));
        itensCaixa.add(new ItemEntity("Chave Perdida", "Chave utilizada para abrir a Caixa do Herói", ItemTypes.KEY, 10000));
        itensCaixa.add(new ItemEntity("Gelo Eterno", "Fragmento de gelo encontrado nas profundezas do oceano. Dizem que pode durar para sempre e curar qualquer tipo de doença", ItemTypes.POTION, 100000));
        itensCaixa.add(new ItemEntity("Sapatos de Hermes", "Sapatos com asas que te levam onde você quiser ir", ItemTypes.ARMOR, 100000));
    }

    public ItemEntity openLootBox(){

        int max = this.itensCaixa.size();
        int min = 0;
        int range = max - min + 1;

        int random = (int)(Math.random() * range) + min;

        System.out.println("Parabéns, você recebeu o item: " + itensCaixa.get(random)); 
        return this.itensCaixa.get(random);
    }

}
