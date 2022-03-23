package space.debian.playerchest.chest;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import space.debian.playerchest.utils.GsonFactory;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.HashMap;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

import space.debian.playerchest.Main;

public class ChestManager
{
    private final Main main;
    private static List<Chest> chests;
    
    public ChestManager(final Main main) {
        this.main = main;
        ChestManager.chests = new ArrayList<>();
    }
    
    public void load(Player player) throws FileNotFoundException {
        File file = new File(this.main.getDataFolder().getAbsolutePath(), player.getName() + ".json");

        if (!(file.exists())) {
            chests.add(new Chest(player.getName(), new HashMap<>()));
            return;
        }
        Chest chest = GsonFactory.getPrettyGson().fromJson(new FileReader(file), Chest.class);
        chests.add(chest);
        Bukkit.getConsoleSender().sendMessage("§6§l" + player.getName() + "§a a été restauré avec succès.");
    }

    public void save(Player player) throws IOException {
        Chest chest = getChest(player.getName());
        File file = new File(this.main.getDataFolder().getAbsolutePath(), player.getName() + ".json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        GsonFactory.getPrettyGson().toJson(chest, writer);
        writer.flush();
        writer.close();
        Bukkit.getConsoleSender().sendMessage("§6§l" + player.getName() + "§a a été sauvegardé avec succès.");
    }
    
    public static Chest getChest(final String player) {
        for (final Chest content : ChestManager.chests) {
            if (content != null) {
                if (!content.getPlayer().equals(player)) {
                    continue;
                }
                return content;
            }
        }
        return null;
    }
    
    public void onClose(final InventoryCloseEvent event) {
        final Player player = (Player)event.getPlayer();
        final Chest chest = getChest(player.getName());
        assert chest != null;
        chest.clear();
        for (int i = 0; i < event.getInventory().getSize(); ++i) {
            final ItemStack item = event.getInventory().getItem(i);
            chest.setItem(i, item);
        }
    }
}
