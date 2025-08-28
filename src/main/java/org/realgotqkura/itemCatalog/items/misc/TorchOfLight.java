package org.realgotqkura.itemCatalog.items.misc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
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

public class TorchOfLight implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public TorchOfLight(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.torchOfLight");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.TORCH);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#fff240", "Torch Of Light"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8Light up your way"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lWHEN HELD &6Let there be light"));
        lore.add(RandomUtils.color("&7Gives Night Vision when held"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "TorchOfLight");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void stopPlacement(BlockPlaceEvent event){
        Player player = event.getPlayer();

        if(!RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "TorchOfLight") && !RandomUtils.passedItemChecks(player.getInventory().getItemInOffHand(), "TorchOfLight"))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void holding(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(!RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "TorchOfLight") && !RandomUtils.passedItemChecks(player.getInventory().getItemInOffHand(), "TorchOfLight"))
            return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20*20, 1, false, false, false));
    }
}
