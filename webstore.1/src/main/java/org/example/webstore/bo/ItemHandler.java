package org.example.webstore.bo;

import org.example.webstore.db.ItemDB;
import org.example.webstore.ui.dto.ItemInfoDTO;
import java.util.ArrayList;
import java.util.List;

public class ItemHandler {

    private final ItemDB itemDB = new ItemDB();

    public List<ItemInfoDTO> getItemsByCategory(int categoryId) {
        List<Item> items = itemDB.getItemsByCategory(categoryId);
        List<ItemInfoDTO> itemInfoList = new ArrayList<>();

        for (Item item : items) {
            ItemInfoDTO dto = new ItemInfoDTO(
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getPrice()
            );
            itemInfoList.add(dto);
        }

        return itemInfoList;
    }
    public ItemInfoDTO getItemDetails(int itemId) {
        Item item = itemDB.getItemById(itemId); //no acesss

        if (item == null) {
            return null;
        }

        return new ItemInfoDTO(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice()
        );
    }

    public Item getItemBusinessObject(int itemId) {
        return itemDB.getItemById(itemId);
    }
    public List<ItemInfoDTO> getItems() {
        List<Item> items = itemDB.getAllItems();
        List<ItemInfoDTO> dtoList = new ArrayList<>();

        for (Item item : items) {
            dtoList.add(new ItemInfoDTO(
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getPrice()
            ));
        }
        return dtoList;
    }
    public boolean addItem(String name, String description, double price, int categoryId) {
        return itemDB.addItem(name, description, price, categoryId);
    }
    public boolean updateItem(int id, String name, String description, double price, int categoryId) {
        return itemDB.updateItem(id, name, description, price, categoryId);
    }
    public boolean deleteItem(int id) {
        return itemDB.deleteItem(id);
    }



}



