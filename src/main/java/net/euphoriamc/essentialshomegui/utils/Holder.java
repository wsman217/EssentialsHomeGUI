package net.euphoriamc.essentialshomegui.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

@ListenerAnnotation
public abstract class Holder implements InventoryHolder, Listener {

    protected Inventory inv;

    public void openInventory(Player p) {
        p.openInventory(inv);
    }

    @Override
    public Inventory getInventory() {
        return this.inv;
    }

    protected abstract void initializeItems();
}
