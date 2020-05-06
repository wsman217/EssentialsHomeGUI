package net.euphoriamc.essentialshomegui.commands;

import net.euphoriamc.essentialshomegui.gui.holder.SetupHolder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class EditGUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by a player.");
            return true;
        }

        Player p = (Player) sender;
        if (!p.hasPermission("EssentialsHomeGUI.editgui")) {
            p.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        new SetupHolder().openInventory(p);
        return true;
    }
}