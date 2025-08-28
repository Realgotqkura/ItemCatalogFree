package org.realgotqkura.itemCatalog.items.misc;

import jdk.jfr.Enabled;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
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

public class HastyPickaxe implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public HastyPickaxe(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.hastyPickaxe");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#3867ff", "Hasty Pickaxe"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8A sword made from the essence of The End"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lON BLOCK BREAK &6Haste"));
        lore.add(RandomUtils.color("&7Gives Haste &aII &7lasting &a5&7s"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "HastyPickaxe");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void breakBl(BlockBreakEvent event){
        Player player = event.getPlayer();

        if(!RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "HastyPickaxe"))
            return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 5*20, 1, false, false, true));

    }
}
