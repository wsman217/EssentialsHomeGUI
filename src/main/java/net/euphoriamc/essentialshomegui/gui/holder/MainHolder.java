package net.euphoriamc.essentialshomegui.gui.holder;

import net.euphoriamc.essentialshomegui.utils.GUIUtils;
import net.euphoriamc.essentialshomegui.utils.Holder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MainHolder extends Holder {

    private final Inventory inv;

    public MainHolder() {
        //TODO add message.yml for inv size and name.
        inv = Bukkit.createInventory(this, 54, ChatColor.LIGHT_PURPLE + "Homes");
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    protected void initializeItems() {
        HashMap<Integer, ItemStack> items = GUIUtils.getContents();
        //TODO add message.yml
        for (Integer i : items.keySet())
            inv.setItem(i, items.get(i));
    }
}
