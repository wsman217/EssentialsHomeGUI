package net.euphoriamc.essentialshomegui.commands;

import net.euphoriamc.essentialshomegui.gui.holder.MainHolder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            //TODO no console message
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("EssentialsHomeGUI.opengui")) {
            //TODO no perms
            return true;
        }
        new MainHolder().openInventory(p);
        return true;
    }
}