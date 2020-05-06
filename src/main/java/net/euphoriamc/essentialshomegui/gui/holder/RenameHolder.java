package net.euphoriamc.essentialshomegui.gui.holder;

import lombok.Getter;
import net.euphoriamc.essentialshomegui.utils.Holder;
import net.euphoriamc.essentialshomegui.utils.ListenerAnnotation;
import net.euphoriamc.essentialshomegui.utils.ListenerIgnore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@ListenerIgnore
public class RenameHolder extends Holder {

    @Getter
    private final String oldName;
    @Getter
    private final Inventory oldInventory;

    public RenameHolder() {
        this.oldName = null;
        this.oldInventory = null;
    }

    public RenameHolder(String oldName, Inventory oldInventory) {
        this.oldName = oldName;
        this.oldInventory = oldInventory;
        //TODO msg and size thing
        inv = Bukkit.createInventory(this, InventoryType.ANVIL, ChatColor.LIGHT_PURPLE + "Rename Home");
        initializeItems();
    }

    @Override
    protected void initializeItems() {
        this.inv.setItem(0, new ItemStack(Material.PAPER));
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e) {
        if (!(e.getInventory().getHolder() instanceof RenameHolder))
            return;
        if (e.getInventory().getType() != InventoryType.ANVIL)
            return;
        AnvilInventory anvil = (AnvilInventory) e.getInventory();
        String newName = anvil.getRenameText();
        System.out.println(newName);
    }

    /*@EventHandler
    public void onTalk(AsyncPlayerChatEvent e) {
        DataManager.getFileConfiguration(e.getPlayer()).set("Data", new DataFile().addHomeItem("Test", Material.BUCKET));
        System.out.println(DataManager.getFileConfiguration(e.getPlayer()));
    }*/
}
