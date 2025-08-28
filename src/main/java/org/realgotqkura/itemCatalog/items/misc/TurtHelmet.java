package org.realgotqkura.itemCatalog.items.misc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class WaterHelmet implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public WaterHelmet(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.infernoStaff");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.TURTLE_HELMET);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#ff6017", "Water Helmet"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8A Staff straight from Hell"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lRIGHT CLICK &6Fireball"));
        lore.add(RandomUtils.color("&7Shoots a Fireball"));
        lore.add(RandomUtils.color("&7Cooldown: &a3&7s"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "WaterHelmet");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void move(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(!RandomUtils.passedItemChecks())
    }
}
