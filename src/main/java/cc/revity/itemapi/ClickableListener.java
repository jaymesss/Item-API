package cc.revity.itemapi;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;

public class ClickableListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getType() == Material.AIR) {
            return;
        }
        Optional<ClickableItem> itemOptional = ItemAPI.getItems().stream().filter(item -> item.getItemStack().isSimilar(event.getItem())).findFirst();
        ClickableItem item = itemOptional.orElse(null);
        if (item == null) {
            return;
        }
        boolean click = item.onClick(event.getPlayer(), event.getItem(), event.getAction());
        event.setCancelled(!click);
    }
}
