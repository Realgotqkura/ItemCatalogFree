package org.realgotqkura.itemCatalog.items.misc;

import jdk.jfr.Enabled;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RabbitBoots implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public RabbitBoots(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.rabbitBoots");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#82faee", "Rabbit Boots"));
        meta.setColor(Color.SILVER);
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8Made from rabbit feet"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lWHEN EQUIPPED &6Jump Boots"));
        lore.add(RandomUtils.color("&7Gives Jump Boost &aII&7 and Speed &aI"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "RabbitBoots");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void walk(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(!RandomUtils.passedItemChecks(player.getEquipment().getBoots(), "RabbitBoots"))
            return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 10*20*60, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*20*60, 0, false, false, false));
    }
}
