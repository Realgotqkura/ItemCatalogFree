package org.realgotqkura.itemCatalog.items.combat;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
import java.util.List;

public class SnowWand implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public SnowWand(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.snowWand");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.IRON_HOE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#ffffff", "Snow Wand"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8A very very cold weapon"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lRIGHT CLICK &6Snowball fight"));
        lore.add(RandomUtils.color("&7Shoots a snowball and slows the hit entity"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "SnowWand");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void click(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if(!RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "SnowWand"))
            return;

        Snowball sball = player.launchProjectile(Snowball.class);
        sball.setMetadata("snowWandKey", new FixedMetadataValue(plugin, 1));
    }

    @EventHandler
    public void hit(ProjectileHitEvent event){
        if(event.getHitEntity() == null)
            return;

        if(!event.getEntity().hasMetadata("snowWandKey"))
            return;

        if(!(event.getHitEntity() instanceof LivingEntity))
            return;

        LivingEntity lEntity = (LivingEntity) event.getHitEntity();
        lEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 2*20, 1, false, false, false));

    }
}
