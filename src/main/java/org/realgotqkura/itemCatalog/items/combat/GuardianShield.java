package org.realgotqkura.itemCatalog.items.combat;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GuardianShield implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public GuardianShield(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.guardianShield");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.SHIELD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#adfff4", "Guardian Shield"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8Just a regular defensive shield"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lOFF HAND SNEAK &6Fortify"));
        lore.add(RandomUtils.color("&7Gives Resistance &aII &7for &a3 &7seconds."));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "GuardianShield");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void sneak(PlayerToggleSneakEvent event){
        Player player = event.getPlayer();

        if(!event.isSneaking())
            return;

        if(!RandomUtils.passedItemChecks(player.getInventory().getItemInOffHand(), "GuardianShield"))
            return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 3*20, 1, false, false, true));
    }
}
