package net.euphoriamc.EssentialsHomeGUI;

import com.earth2me.essentials.User;
import net.ess3.api.IEssentials;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class EssentialsUtils {

    private EssentialsHomeGUI plugin = EssentialsHomeGUI.getInstance();
    private IEssentials essentials;

    public EssentialsUtils() {
        essentials = (IEssentials) plugin.getServer().getPluginManager().getPlugin("EssentialsX");
    }

    public List<String> getHomes(Player p) {
        User essentialsUser = essentials.getUser(p);
        return essentialsUser.getHomes();
    }

    public boolean hasHome(Player p, String search) {
        User essentialsUser = essentials.getUser(p);
        return essentialsUser.getHomes().contains(search);
    }

    public void setHome(Player p, String name) {
        User essentialsUser = essentials.getUser(p);
        essentialsUser.setHome(name, p.getLocation());
    }

    public boolean renameHome(Player p, String originalName, String newName) {
        User essentialsUser = essentials.getUser(p);
        try {
            Location originalLoc = essentialsUser.getHome(originalName);
            if (originalLoc == null)
                return false;
            essentialsUser.delHome(originalName);
            essentialsUser.setHome(newName, p.getLocation());
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }
}
