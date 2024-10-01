package edu.pucrs.br.item;

import java.util.UUID;

public class ItemEntity {
    private final UUID id;
    private final String name;
    private final String description;
    private final ItemTypes type;
    private final double price;

    public ItemEntity(
        String name,
        String description,
        ItemTypes type,
        double price
    ) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemTypes getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return type + " " + name + ", Preço: " + price;
    }
<<<<<<< HEAD

=======
    
>>>>>>> 058352aeb9648acc6a886febb9209c7d12d9af33
}
