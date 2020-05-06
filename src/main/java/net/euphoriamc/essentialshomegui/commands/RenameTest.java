package net.euphoriamc.essentialshomegui.commands;

import net.euphoriamc.essentialshomegui.gui.holder.RenameBuilder;
import net.euphoriamc.essentialshomegui.gui.holder.RenameHolder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RenameTest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //new RenameHolder("test", new RenameHolder().getInventory()).openInventory((Player) sender);
        new RenameBuilder("test", null).open((Player) sender);
        return true;
    }
}