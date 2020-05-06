package net.euphoriamc.essentialshomegui.gui.holder;

import net.euphoriamc.essentialshomegui.utils.GUIUtils;
import net.euphoriamc.essentialshomegui.utils.Holder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SetupHolder extends Holder {

    public SetupHolder() {
        //TODO add message.yml for inv size and name.
        inv = Bukkit.createInventory(this, 54, ChatColor.LIGHT_PURPLE + "Homes");
        initializeItems();
    }

    @Override
    protected void initializeItems() {
        HashMap<Integer, ItemStack> items = GUIUtils.getContents();
        //TODO add message.yml
        List<String> lore = Collections.singletonList(ChatColor.LIGHT_PURPLE + "Shift + Left Click to set as a button.");
        for (Integer i : items.keySet()) {
            ItemStack working = items.get(i);
            ItemMeta im = working.getItemMeta();
            if (im != null) {
                im.setDisplayName(" ");
                im.setLore(lore);
                working.setItemMeta(im);
            }
            inv.setItem(i, working);
        }
    }

    @EventHandler
    public void setupClickHandler(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        System.out.println("yeet");
        if (!(inv.getHolder() instanceof SetupHolder))
            return;
        if (e.getCursor() != null)
            return;
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null)
            return;
        if (e.isShiftClick()) {
            //TODO open the button menu for clicking a button will have to pass the inventory along to it so that way you can save the progress.
        }
    }

    @EventHandler
    public void setupCloseHandler(InventoryCloseEvent e) {
        Inventory inv = e.getInventory();
        System.out.println(inv.getHolder());
        System.out.println(this);
        if (!(inv.getHolder() instanceof SetupHolder))
            return;
        //TODO do I want a confirmation menu?
        System.out.println("test");
        GUIUtils.saveContents(inv);
        //TODO add an output message
    }
}
