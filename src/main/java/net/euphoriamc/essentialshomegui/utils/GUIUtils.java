package net.euphoriamc.essentialshomegui.utils;

import net.euphoriamc.essentialshomegui.EssentialsHomeGUI;
import net.euphoriamc.essentialshomegui.FileManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;

public class GUIUtils {
    public static HashMap<Integer, ItemStack> getContents() {
        FileConfiguration guiConfig = EssentialsHomeGUI.getFileManager().getFile(FileManager.Files.GUI);
        ConfigurationSection configSection = guiConfig.getConfigurationSection("Items");
        if (configSection == null) {
            guiConfig.createSection("Items");
            configSection = guiConfig.getConfigurationSection("Items");
        }

        if (configSection == null)
            throw new NullPointerException("Config section: Items was equal to null.");

        //String[] keyList = (String[]) configSection.getKeys(false).toArray();
        String[] keyList = new String[configSection.getKeys(false).size()];
        configSection.getKeys(false).toArray(keyList);
        HashMap<Integer, ItemStack> items = new HashMap<>();

        for (String s : keyList) items.put(Integer.parseInt(s), configSection.getItemStack(s));

        return items;
    }

    public static void saveContents(Inventory inv) {
        FileConfiguration guiConfig = EssentialsHomeGUI.getFileManager().getFile(FileManager.Files.GUI);
        ItemStack[] contents = inv.getContents();
        System.out.println(Arrays.toString(contents));
        guiConfig.set("Items", null);
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

        EssentialsHomeGUI.getFileManager().saveFile(FileManager.Files.GUI);
        EssentialsHomeGUI.getFileManager().reloadFile(FileManager.Files.GUI);
    }
}
