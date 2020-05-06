package net.euphoriamc.essentialshomegui.data;

import net.euphoriamc.essentialshomegui.EssentialsHomeGUI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class DataManager {

    private static EssentialsHomeGUI plugin;

    public DataManager() {
        plugin = EssentialsHomeGUI.getInstance();
    }

    public static File getFile(Player p) {
        File dataFolder = new File(plugin.getDataFolder(), "player_data");
        File file = new File(plugin.getDataFolder() + "\\player_data", p.getUniqueId().toString() + ".yml");
        if (!file.exists())
            try {
                if (!dataFolder.mkdir())
                    return null;
                if (!file.createNewFile())
                    return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return file;
    }

    public static FileConfiguration getFileConfiguration(Player p) {
        File dataFolder = new File(plugin.getDataFolder(), "player_data");
        File file = new File(plugin.getDataFolder() + "\\player_data", p.getUniqueId().toString() + ".yml");
        if (!file.exists())
            try {
                if (!dataFolder.mkdir())
                    return null;
                if (!file.createNewFile())
                    return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration saveFile(Player p) {
        FileConfiguration file = getFileConfiguration(p);
        try {
            file.save(getFile(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
