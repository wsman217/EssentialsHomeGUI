package net.euphoriamc.essentialshomegui.data;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataFile implements ConfigurationSerializable {

    private final ArrayList<String> homeItems;
    private ArrayList<HomeItem> parsedHomeItems;

    public DataFile() {
        homeItems = new ArrayList<>();
    }

    public DataFile(Map<String, Object> map) {
        homeItems = (ArrayList<String>) map.get("homeItems");
    }

    public DataFile addHomeItem(String name, Material mat) {
        homeItems.add(name + ":" + mat);
        return this;
    }

    public HomeItem contains(String name) {
        if (parsedHomeItems == null)
            getHomeItems();
        for (HomeItem item : parsedHomeItems) {
            if (item.name.equalsIgnoreCase(name))
                return item;
        }
        return null;
    }

    public ArrayList<HomeItem> getHomeItems() {
        if (parsedHomeItems != null)
            return parsedHomeItems;
        ArrayList<HomeItem> homeItems = new ArrayList<>();
        for (String working : this.homeItems) {
            String[] split = working.split(":");
            if (split.length != 2)
                continue;
            String name = split[0];
            Material mat = Material.getMaterial(split[1]);
            if (mat == null)
                continue;
            homeItems.add(new HomeItem(name, mat));
        }
        parsedHomeItems = homeItems;
        return homeItems;
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("homeItems", homeItems);
        return map;
    }
}
