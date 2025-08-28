package org.realgotqkura.itemCatalog.guis.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

public class GeneralGuiItems {

    public ItemStack guiGlassDark() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLACK + ".");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack guiGlass() {
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(ChatColor.BLACK + ".");
        glass.setItemMeta(meta);
        return glass;
    }

    public ItemStack guiClose() {
        ItemStack close = new ItemStack(Material.IRON_DOOR);
        ItemMeta meta = close.getItemMeta();
        meta.setDisplayName(RandomUtils.color("&cClose GUI"));
        close.setItemMeta(meta);
        return close;
    }

    public ItemStack pages(boolean previous){
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        if(!previous){
            meta.setDisplayName(RandomUtils.safeHexColor("#5eab55", "Next Page"));
        }else{
            meta.setDisplayName(RandomUtils.safeHexColor("#5eab55", "Previous Page"));
        }
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack openPreviousInventoryItem(){
        ItemStack stack = new ItemStack(Material.IRON_DOOR);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#fc6060", "Go to the previous Inventory"));
        stack.setItemMeta(meta);
        return stack;
    }

    public ItemStack cornerItems(boolean enchanted){
        ItemStack stack = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.color("&0."));

        if(enchanted){
            meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        stack.setItemMeta(meta);
        return stack;
    }
}
