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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class ChickenWings implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public ChickenWings(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.chickenWings");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#fceabd", "Chicken Wings"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8Not very vegan"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lWHEN WORN &6Slow Falling"));
        lore.add(RandomUtils.color("&7Gives you permanent Slow Falling &aI"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "ChickenWings");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void move(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(!RandomUtils.passedItemChecks(player.getEquipment().getChestplate(), "ChickenWings"))
            return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 10*20, 0, false, false, false));
    }
}
