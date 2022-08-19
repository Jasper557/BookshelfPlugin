package de.jasper557.bookshelf;

import de.jasper557.bookshelf.events.BookshelfInvEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bookshelf extends JavaPlugin {

     @Override
    public void onEnable() {
        getLogger().info("Plugin Loaded!");
        getServer().getPluginManager().registerEvents(new BookshelfInvEvents(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }
}