package cc.revity.itemapi;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class ClickableItem {

    public abstract String getId();

    public abstract String getItemName();

    public abstract List<String> getLore();

    public abstract boolean onClick(Player player, ItemStack clicked, Action action);

}
