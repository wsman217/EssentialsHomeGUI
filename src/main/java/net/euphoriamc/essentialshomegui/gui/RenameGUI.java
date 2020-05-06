package net.euphoriamc.essentialshomegui.gui;

import net.euphoriamc.essentialshomegui.gui.holder.RenameHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RenameGUI {

    private final String oldHome;
    private final Inventory oldInventory;

    public RenameGUI(String oldHome, Inventory oldInventory) {
        this.oldHome = oldHome;
        this.oldInventory = oldInventory;
    }

    public RenameGUI openInv(Player p) {
        Inventory renameInv = Bukkit.createInventory(new RenameHolder(oldHome, oldInventory), InventoryType.ANVIL, ChatColor.AQUA + "Rename " + oldHome);
        renameInv.setItem(0, new ItemStack(Material.PAPER));
        p.openInventory(renameInv);
        return this;
    }
}
