package org.realgotqkura.itemCatalog.items.combat;

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

public class GolemUnderwear implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public GolemUnderwear(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.golemUnderwear");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#c29fa5", "Golem Underwear"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8Pretty intimate"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lWHEN WORN &6Strength"));
        lore.add(RandomUtils.color("&7Gives permanent Strength &aI"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "GolemUnderwear");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void move(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(!RandomUtils.passedItemChecks(player.getEquipment().getLeggings(), "GolemUnderwear"))
            return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 10*20, 0, false, false, false));
    }
}
