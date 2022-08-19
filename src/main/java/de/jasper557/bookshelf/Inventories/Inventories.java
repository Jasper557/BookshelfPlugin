package de.jasper557.bookshelf.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class Inventories implements Listener {



    public void newInventory(Player player){
        Inventory i = Bukkit.getServer().createInventory(null, 6, "§4Bookshelf §2Inventory");
        player.openInventory(i);
    }


}
