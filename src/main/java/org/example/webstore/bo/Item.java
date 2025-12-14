package org.example.webstore.bo;

public class Item {

    private int id;
    private String name;
    private String description;
    private double price;
    private int categoryId;

    public Item(int id, String name, String description, double price, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }
}
