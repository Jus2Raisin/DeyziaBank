package space.debian.playerchest.commands;

import org.bukkit.Sound;
import space.debian.playerchest.chest.Chest;
import org.bukkit.inventory.Inventory;
import space.debian.playerchest.chest.ChestManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class CommandChest implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String msg, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            final int size = player.hasPermission("bank.3") ? 3 : (player.hasPermission("bank.2") ? 2 : (player.hasPermission("bank.1") ? 1 : 0));
            if (size > 0) {
                final Inventory inv = Bukkit.createInventory(null, 9 * size, "§8Coffre de niveau §6§l" + size);
                final Chest chest = ChestManager.getChest(player.getName());
                try {
                    assert chest != null;
                    chest.getItems().forEach(inv::setItem);
                }
                catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
                    ignored.getStackTrace();
                }
                player.openInventory(inv);
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
            }
            else {
                player.sendMessage("§cVous n'avez pas le grade nécessaire !");
            }
        }
        return true;
    }
}
