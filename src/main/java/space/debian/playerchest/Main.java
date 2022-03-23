package space.debian.playerchest;

import space.debian.playerchest.listeners.Events;
import space.debian.playerchest.commands.CommandChest;
import org.bukkit.Bukkit;
import space.debian.playerchest.chest.ChestManager;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin
{
    public ChestManager chestManager;
    
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§d§lDeyziaBank: §fPlugin à présent §a§lAllumé §f!");
        Bukkit.getConsoleSender().sendMessage(" ");
        this.chestManager = new ChestManager(this);
        this.getCommand("bank").setExecutor(new CommandChest());
        this.getServer().getPluginManager().registerEvents(new Events(this), this);
    }
    
    public void onDisable() {

        Bukkit.getOnlinePlayers().forEach(player -> {
            try {
                this.chestManager.save(player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§d§lDeyziaBank: §fPlugin à présent §c§lEteint §f!");
        Bukkit.getConsoleSender().sendMessage(" ");
    }

}
