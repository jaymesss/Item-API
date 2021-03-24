package cc.revity.itemapi;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.*;
import java.util.stream.Collectors;

@Accessors(fluent = true) @Setter
public class ItemBuilder {

    private Material material;
    private String name;
    private List<String> lore = new ArrayList<>();
    private int amount;
    private short data;
    private Map<Enchantment, Integer> enchants = new HashMap<>();
    private Color color;
    private ItemStack base;
    private boolean glow;

    public static ItemBuilder from(ItemStack itemStack) {
        return new ItemBuilder().base(itemStack);
    }

    public static ItemBuilder from(Material material) {
        return new ItemBuilder().material(material);
    }

    public static ItemBuilder from(Material material, int amount) {
        return from(material).amount(amount);
    }

    public static ItemBuilder from(Material material, int amount, short data) {
        return from(material, amount).data(data);
    }

    public ItemBuilder enchant(Enchantment enchant, int level) {
        enchants.put(enchant, level);
        return this;
    }

    public ItemBuilder addLore(String... lines) {
        lore.addAll(Arrays.stream(lines).map(this::translate).collect(Collectors.toList()));
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = base == null ? new ItemStack(material, amount, data) : base;
        ItemMeta itemMeta = Objects.requireNonNull(itemStack.getItemMeta());
        itemMeta.setLore(lore);
        if (name != null) { itemMeta.setDisplayName(translate(name)); }
        if (itemMeta instanceof LeatherArmorMeta && color != null) ((LeatherArmorMeta) itemMeta).setColor(color);
        for (Map.Entry<Enchantment, Integer> enchant : enchants.entrySet()) itemMeta.addEnchant(enchant.getKey(), enchant.getValue(), true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
