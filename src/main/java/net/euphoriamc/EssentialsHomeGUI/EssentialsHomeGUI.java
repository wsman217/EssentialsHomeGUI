package net.euphoriamc.EssentialsHomeGUI;

import lombok.Getter;
import net.euphoriamc.EssentialsHomeGUI.commands.EditGUICommand;
import net.euphoriamc.EssentialsHomeGUI.gui.HomesSetupGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class EssentialsHomeGUI extends JavaPlugin {

    @Getter
    private static EssentialsHomeGUI instance;
    @Getter
    private static FileManager fileManager;
    @Getter
    private static EssentialsUtils essentialsUtils;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getServer().getConsoleSender().sendMessage(Constants.PREFIX + ChatColor.DARK_PURPLE + "Started");

        fileManager = FileManager.getInstance().logInfo(true).setup(this);
        essentialsUtils = new EssentialsUtils();

        initCommands();
        initListeners();
    }

    private void initListeners() {
        registerListener(new HomesSetupGUI());
    }

    private void initCommands() {
        registerCommand("editgui", new EditGUICommand());
    }

    private void registerListener(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    private void registerCommand(String commandAlias, CommandExecutor executor) {
        this.getCommand(commandAlias).setExecutor(executor);
    }
}
