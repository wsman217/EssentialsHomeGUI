package net.euphoriamc.EssentialsHomeGUI.gui.holder;

import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class RenameHolder implements InventoryHolder {

    @Getter
    private final String oldName;
    @Getter
    private final Inventory oldInventory;

    public RenameHolder(String oldName, Inventory oldInventory) {
        this.oldName = oldName;
        this.oldInventory = oldInventory;
    }

    public Inventory getInventory() {
        return null;
    }
}
