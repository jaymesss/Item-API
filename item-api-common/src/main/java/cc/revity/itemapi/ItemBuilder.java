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
    private int data;
    private Map<Enchantment, Integer> enchants = new HashMap<>();
    private Color color;
    private ItemStack base;

    /**
     * @param itemStack the {@link ItemStack} to clone
     * @return the {@link ItemBuilder}
     */
    public static ItemBuilder clone(ItemStack itemStack) {
        return new ItemBuilder().base(itemStack);
    }

    /**
     * @param material the {@link Material} to clone
     * @return the {@link ItemBuilder}
     */
    public static ItemBuilder from(Material material) {
        return new ItemBuilder().material(material);
    }

    /**
     * @param material the {@link Material} to clone
     * @param amount the amount that the {@link ItemStack} stack size should be
     * @return the {@link ItemBuilder}
     */
    public static ItemBuilder from(Material material, int amount) {
        return from(material).amount(amount);
    }

    /**
     * @param material the {@link Material} to clone
     * @param amount the amount that the {@link ItemStack} stack size should be
     * @param data the data to set on the {@link ItemStack}
     * @return the {@link ItemBuilder}
     */
    public static ItemBuilder from(Material material, int amount, int data) {
        return from(material, amount).data(data);
    }

    /**
     * @param enchant the {@link Enchantment} to add
     * @param level the level of the enchantmentt
     * @return the {@link ItemBuilder}
     */
    public ItemBuilder enchant(Enchantment enchant, int level) {
        enchants.put(enchant, level);
        return this;
    }

    /**
     * @param lines the {@link String...} to add to the lore
     * @return the {@link ItemBuilder}
     */
    public ItemBuilder addLore(String... lines) {
        lore.addAll(Arrays.stream(lines).map(this::translate).collect(Collectors.toList()));
        return this;
    }

    /**
     * @return the finished {@link ItemStack}
     */
    public ItemStack build() {
        ItemStack itemStack = base == null ? new ItemStack(material, amount, (short) data) : base;
        ItemMeta itemMeta = Objects.requireNonNull(itemStack.getItemMeta());
        itemMeta.setLore(lore);
        if (name != null) { itemMeta.setDisplayName(translate(name)); }
        if (itemMeta instanceof LeatherArmorMeta && color != null) ((LeatherArmorMeta) itemMeta).setColor(color);
        for (Map.Entry<Enchantment, Integer> enchant : enchants.entrySet()) itemMeta.addEnchant(enchant.getKey(), enchant.getValue(), true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * @param string the {@link String} to translate
     * @return the translated {@link String}
     */
    private String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
