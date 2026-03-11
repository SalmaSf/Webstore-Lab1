package org.example.webstore.ui.dto;

public class ItemInfoDTO {

    private int id;
    private String name;
    private String description;
    private double price;

    public ItemInfoDTO(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
}

