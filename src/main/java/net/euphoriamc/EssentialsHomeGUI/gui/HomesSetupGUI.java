package net.euphoriamc.EssentialsHomeGUI.gui;

import net.euphoriamc.EssentialsHomeGUI.EssentialsHomeGUI;
import net.euphoriamc.EssentialsHomeGUI.FileManager;
import net.euphoriamc.EssentialsHomeGUI.gui.holder.SetupHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class HomesSetupGUI implements Listener {

    public static ItemStack[] getContents() {
        FileConfiguration guiConfig = EssentialsHomeGUI.getFileManager().getFile(FileManager.Files.GUI);
        ArrayList<ItemStack> contents = new ArrayList<>();
        ConfigurationSection configSection = guiConfig.getConfigurationSection("Items");
        if (configSection == null) {
            guiConfig.createSection("Items");
            configSection = guiConfig.getConfigurationSection("Items");
        }

        if (configSection == null)
            throw new NullPointerException("Config section: Items was equal to null.");

        String[] keyList = (String[]) configSection.getKeys(false).toArray();
        ItemStack[] items = new ItemStack[keyList.length];

        for (int i = 0; i < keyList.length; i++)
            items[i] = configSection.getItemStack(keyList[i]);
        return items;
    }

    public static void openInventory(Player p) {
        p.openInventory(createInventory());
    }

    @EventHandler
    public void setupClickHandler(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
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
        if (!(inv.getHolder() instanceof SetupHolder))
            return;
        //TODO do I want a confirmation menu?
        saveContents(inv);
        //TODO add an output message
    }

    private static Inventory createInventory() {
        ItemStack[] items = getContents();
        //TODO add message.yml
        Inventory inv = Bukkit.createInventory(new SetupHolder(), 54, ChatColor.LIGHT_PURPLE + "Setup HomesGUI");
        ArrayList<String> lore = (ArrayList<String>) Collections.singletonList(ChatColor.LIGHT_PURPLE + "Shift + Left Click to set as a button.");
        for (int i = 0; i < items.length; i++) {
            ItemStack working = items[i];
            ItemMeta im = working.getItemMeta();
            if (im != null) {
                im.setDisplayName("");
                im.setLore(lore);
                working.setItemMeta(im);
            }
            inv.setItem(i, working);
        }
        return inv;
    }

    private static void saveContents(Inventory inv) {
        FileConfiguration guiConfig = EssentialsHomeGUI.getFileManager().getFile(FileManager.Files.GUI);
        ItemStack[] contents = inv.getContents();
        ConfigurationSection configSection = guiConfig.getConfigurationSection("Items");
        if (configSection == null) {
            guiConfig.createSection("Items");
            configSection = guiConfig.getConfigurationSection("Items");
        }

        if (configSection == null)
            throw new NullPointerException("Config section: Items was equal to null.");

        for (int i = 0; i < contents.length; i++) {
            ItemStack working = contents[i];
            if (working == null || working.getType() == Material.AIR)
                continue;
            configSection.set("" + i, working);
        }

        EssentialsHomeGUI.getFileManager().reloadFile(FileManager.Files.GUI);
    }
}
