package org.realgotqkura.itemCatalog.items.combat;

import jdk.jfr.Enabled;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ZephyrBow implements Listener {

    private static HashMap<UUID, Double> cooldowns = new HashMap<>();

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public ZephyrBow(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.zephyrBow");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.BOW);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#e06710", "Zephyr Bow"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8Is that a Warframe reference"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lON SHOOT &6Fly away"));
        lore.add(RandomUtils.color("&7Gives Levitation &aII &7for &a3 &7seconds"));
        lore.add(RandomUtils.color("&7to the hit enemy."));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "ZephyrBow");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void shoot(EntityShootBowEvent event){
        if(!RandomUtils.passedItemChecks(event.getBow(), "ZephyrBow"))
            return;

        event.getProjectile().setMetadata("zephyrBowArrow", new FixedMetadataValue(plugin, 1));
    }

    @EventHandler
    public void hit(ProjectileHitEvent event){
        if(event.getHitEntity() == null)
            return;

        if(!event.getEntity().hasMetadata("zephyrBowArrow"))
            return;

        if(!(event.getHitEntity() instanceof LivingEntity))
            return;

        LivingEntity lEntity = (LivingEntity) event.getHitEntity();
        lEntity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 3*20, 1, false, false, false));




    }
}
