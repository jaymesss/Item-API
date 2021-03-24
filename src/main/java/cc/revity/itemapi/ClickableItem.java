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

    /**
     * @param player the {@link Player} to take one from
     * @return if the {@link ClickableItem} was taken
     */
    public boolean takeOne(Player player) {
        return take(player, 1);
    }

    /**
     * @param player the {@link Player} to take from
     * @return if the {@link ClickableItem} was taken
     * @return
     */
    public boolean take(Player player, int amount) {
        boolean found = false;
        ItemStack base = getItemStack();
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (!itemStack.isSimilar(base) || found) {
                continue;
            }
            int removed = Math.min(0, itemStack.getAmount() - amount);
            amount -= removed;
            itemStack.setAmount(itemStack.getAmount() - amount);
            found = amount == 0;
        }
        return found;
    }
}
