// 
// Decompiled by Procyon v0.5.36
// 

package space.debian.playerchest.chest;

import java.util.HashMap;
import org.bukkit.inventory.ItemStack;
import java.util.Map;

public class Chest
{
    private final String player;
    private Map<Integer, ItemStack> items;
    
    public Chest(final String player, final Map<Integer, ItemStack> items) {
        this.player = player;
        this.items = items;
    }
    
    public String getPlayer() {
        return this.player;
    }
    
    public Map<Integer, ItemStack> getItems() {
        return this.items;
    }
    
    public void clear() {
        this.items = new HashMap<>();
    }
    
    public void setItem(final int slot, final ItemStack item) {
        this.items.put(slot, item);
    }
}
