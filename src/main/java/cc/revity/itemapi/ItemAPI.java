package cc.revity.itemapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.*;

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
     * @param item is the {@link ClickableItem...} you're registering
     */
    public static void registerItems(ClickableItem... item) {
        Arrays.stream(item).forEach(i -> items.put(i.getId(), i));
    }

    /**
     * @param item the {@link ClickableItem} to unregister
     */
    public static void unregisterItem(ClickableItem item) {
        unregisterItem(item.getId());
    }

    /**
     * @param item the {@link ClickableItem...} to unregister
     */
    public static void unregisterItems(ClickableItem... item) {
        Arrays.stream(item).map(ClickableItem::getId).forEach(items::remove);
    }

    /**
     * @param id the id of the {@link ClickableItem} to unregister
     */
    public static void unregisterItem(String id) {
        items.remove(id);
    }

    /**
     * @param id the id of the {@link ClickableItem...} to unregister
     */
    public static void unregisterItems(String... id) {
        Arrays.stream(id).forEach(items::remove);
    }

    /**
     * @return a {@link List} of all of the registered {@link ClickableItem}'s
     */
    public static List<ClickableItem> getItems() {
        return new ArrayList<>(items.values());
    }

    /**
     * @param registering the {@link Plugin} running MenuAPI
     */
    public static void register(Plugin registering) {
        plugin = registering;
        plugin.getServer().getPluginManager().registerEvents(new ClickableListener(), plugin);
    }

}
