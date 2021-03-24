package cc.revity.itemapi;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ItemAPI {

    private static final Map<String, ClickableItem> items = new HashMap<>();
    private static Plugin plugin;

    public static void registerItem(ClickableItem item) {
        items.put(item.getId(), item);
    }

    public static void unregisterItem(ClickableItem item) {
        unregisterItem(item.getId());
    }

    public static void unregisterItem(String id) {
        items.remove(id);
    }

    public static void setPlugin(Plugin registering) {
        plugin = registering;
    }

    public static boolean registerClickListener(Listener listener) {
        if (plugin == null) {
            Bukkit.getLogger().log(Level.SEVERE, "[ItemAPI] You cannot register a click listener without a registering plugin!");
            return false;
        }
        Bukkit.getPluginManager().registerEvents(listener, plugin);
        return true;
    }

}
