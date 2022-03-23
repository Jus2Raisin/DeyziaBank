package space.debian.playerchest.listeners;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import space.debian.playerchest.Main;
import org.bukkit.event.Listener;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Events implements Listener
{
    private final Main main;
    
    public Events(final Main main) {
        this.main = main;
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        try {
            this.main.chestManager.load(player);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @EventHandler
    public void onLeave(final PlayerQuitEvent event)  {
        final Player player = event.getPlayer();

        try {
            this.main.chestManager.save(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (event.getInventory().getName().contains("§8Coffre")) {
            this.main.chestManager.onClose(event);
        }
    }
}
