package net.euphoriamc.essentialshomegui.gui.holder;

import lombok.Getter;
import net.euphoriamc.essentialshomegui.EssentialsHomeGUI;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class RenameBuilder {

    private final AnvilGUI.Builder anvilGUI;
    @Getter
    private final String name;
    @Getter
    private final InventoryHolder oldInventory;
    private Listener listener;

    public RenameBuilder(String name, InventoryHolder oldInventory) {
        this.name = name;
        this.oldInventory = oldInventory;
        anvilGUI = new AnvilGUI.Builder()
                .onClose(player -> close())
                .item(new ItemStack(Material.PAPER))
                .plugin(EssentialsHomeGUI.getInstance())
                //TODO add message.yml
                .title(ChatColor.LIGHT_PURPLE + "Rename " + name);
    }

    public void open(Player p) {
        AnvilGUI gui = anvilGUI.open(p);
        listener = new ListenerMethodsBecauseTheAPIIsAnnoying(gui.getInventory());
    }

    public void close() {
        HandlerList.unregisterAll(listener);
    }

    public static class ListenerMethodsBecauseTheAPIIsAnnoying implements Listener {

        private final Inventory inventory;

        public ListenerMethodsBecauseTheAPIIsAnnoying(Inventory inventory) {
            EssentialsHomeGUI instance = EssentialsHomeGUI.getInstance();
            instance.getServer().getPluginManager().registerEvents(this, instance);
            this.inventory = inventory;
        }

        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
            if (!e.getInventory().equals(inventory))
                return;
            AnvilInventory inv = (AnvilInventory) e.getInventory();
        }
    }
}
