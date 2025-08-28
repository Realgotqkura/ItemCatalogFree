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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SpeedyBoots implements Listener {

    private static HashMap<UUID, Double> cooldowns = new HashMap<>();

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public SpeedyBoots(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.speedyBoots");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#949494", "Speedy Boots"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8One trick pony Rabbit Boots"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lWHEN WORN &6Speed"));
        lore.add(RandomUtils.color("&7Gives Speed &aIII"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "SpeedyBoots");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void move(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(!RandomUtils.passedItemChecks(player.getEquipment().getBoots(), "SpeedyBoots"))
            return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60*20, 2, false, false, false));
    }

}
