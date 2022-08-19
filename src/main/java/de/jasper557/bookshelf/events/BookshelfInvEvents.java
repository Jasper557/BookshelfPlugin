package de.jasper557.bookshelf.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public final class BookshelfInvEvents implements Listener {


    private final File file = new File("plugins/bookshelfinv", "items.yml");
    private final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

    private final static HashMap<Location, Inventory> bookMap = new HashMap<>();


    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if(clickedBlock != null){
        Location clickedLoc = clickedBlock.getLocation();
        if (event.getClickedBlock().getType() == Material.BOOKSHELF) {
            if (bookMap.containsKey(clickedLoc)) { // Hier wird geschaut ob das Inventar bereits Initialisiert ist
                openInv(player, clickedLoc); // Hier wird das Inventar geöffnet
            } else {
                Inventory inv = Bukkit.createInventory(null, 9, "§4Bookshelf Inv"); // Hier wird das Inventar erstellt, wenn noch nicht zuvor
                inv.setItem(0, new ItemStack(Material.BOOK));
                inv.setItem(3, new ItemStack(Material.BOOK));
                inv.setItem(5, new ItemStack(Material.BOOK));
                inv.setItem(7, new ItemStack(Material.BOOK));
                inv.setItem(8, new ItemStack(Material.BOOK));
                bookMap.put(clickedLoc, inv);
                openInv(player, clickedLoc);// Hier wird das Inventar geöffnet
            }
            }
        }
    }

    public void openInv(Player player, Location location) {
        player.openInventory(bookMap.get(location));
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("§4Bookshelf Inv")) {
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() == Material.BOOK) {
                    e.setCancelled(true);
                }

            }

        }

    }

    @EventHandler
    public void onCloseInv(InventoryCloseEvent e) {
        try {
            yamlConfiguration.save(this.file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }


    }
}
