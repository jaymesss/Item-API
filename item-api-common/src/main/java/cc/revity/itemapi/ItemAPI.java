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

    /**
     * @param item is the {@link ClickableItem} you're registering
     */
    public static void registerItem(ClickableItem item) {
        items.put(item.getId(), item);
    }

    /**
     * @param item the {@link ClickableItem} to unregister
     */
    public static void unregisterItem(ClickableItem item) {
        unregisterItem(item.getId());
    }

    /**
     * @param id the id of the {@link ClickableItem} to unregister
     */
    public static void unregisterItem(String id) {
        items.remove(id);
    }

    /**
     * @param registering the {@link Plugin} running MenuAPI
     */
    public static void setPlugin(Plugin registering) {
        plugin = registering;
    }

    /**
     * @param listener the {@link Listener} containing click logic
     * @return if the click should be allowed
     */
    public static boolean registerClickListener(Listener listener) {
        if (plugin == null) {
            Bukkit.getLogger().log(Level.SEVERE, "[ItemAPI] You cannot register a click listener without a registering plugin!");
            return false;
        }
        Bukkit.getPluginManager().registerEvents(listener, plugin);
        return true;
    }

}
