package org.realgotqkura.itemCatalog.items.combat;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

public class PoisonFang implements Listener {


    private ItemCatalog plugin;
    private static int customModelDataNum;

    public PoisonFang(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.poisonFang");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#32a852", "Poison Fang"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8A tribe classic"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lENTITY HIT &6Poison"));
        lore.add(RandomUtils.color("&7Applies Poison &aI &7to the hit entity"));
        lore.add(RandomUtils.color("&7for &a5&7s"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "PoisonFang");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void hit(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof LivingEntity) && !(event.getEntity() instanceof LivingEntity))
            return;

        try{
            LivingEntity lDamager = (LivingEntity) event.getDamager();

            if(!RandomUtils.passedItemChecks(lDamager.getEquipment().getHelmet(), "PoisonFang"))
                return;

            ((LivingEntity) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5*20, 0, false, false, true));
        }catch (Exception ignored){

        }
    }
}
