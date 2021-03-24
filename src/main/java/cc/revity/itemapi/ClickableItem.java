package cc.revity.itemapi;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public abstract class ClickableItem {

    /**
     * @return the id of the {@link ClickableItem}
     */
    public abstract String getId();

    /**
     * @return the {@link ItemStack} of the {@link ClickableItem}
     */
    public abstract ItemStack getItemStack();

    /**
     * @return the clicking logic of the {@link ClickableItem}
     */
    public abstract boolean onClick(Player player, ItemStack clicked, Action action);
}
