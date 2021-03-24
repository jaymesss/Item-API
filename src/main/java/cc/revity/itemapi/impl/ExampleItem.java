package cc.revity.itemapi.impl;

import cc.revity.itemapi.ClickableItem;
import cc.revity.itemapi.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class ExampleItem extends ClickableItem {
    @Override
    public String getId() {
        return "Example";
    }

    @Override
    public ItemStack getItemStack() {
        return ItemBuilder.from(Material.REDSTONE).name("&bExample").addLore("&aLine one!", "&eLine two!", "&cLine three!").build();
    }

    @Override
    public boolean onClick(Player player, ItemStack clicked, Action action) {
        if (action != Action.RIGHT_CLICK_AIR) {
            player.sendMessage(ChatColor.RED + "You can only right click this item!");
            return false;
        }
        player.sendMessage(ChatColor.GREEN + "Diamonds were granted to you!");
        player.getInventory().addItem(ItemBuilder.from(Material.DIAMOND).amount(16).name("&bDiamonds!").build());
        this.takeOne(player);
        return false;
    }
}
