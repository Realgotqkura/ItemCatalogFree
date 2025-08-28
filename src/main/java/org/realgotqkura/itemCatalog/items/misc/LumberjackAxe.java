package org.realgotqkura.itemCatalog.items.misc;

import jdk.jfr.Enabled;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;
import org.realgotqkura.itemCatalog.utilities.SoundAndEffectUtils;

import java.util.ArrayList;
import java.util.List;

public class LumberjackAxe implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public LumberjackAxe(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.lumberjackAxe");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.GOLDEN_AXE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#f5a056", "Lumberjack Axe"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8The lumberjacks best friend"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lON BLOCK BREAK &6Lumber"));
        lore.add(RandomUtils.color("&7Destroys all wood of the same wood blocks"));
        lore.add(RandomUtils.color("&7in &a3&7x&a3 area"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "LumberjackAxe");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void breakBl(BlockBreakEvent event){
        Player player = event.getPlayer();

        if(!RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "LumberjackAxe"))
            return;

        if(!event.getBlock().getType().toString().toLowerCase().contains("log"))
            return;

        int dropCount = event.getBlock().getDrops(player.getInventory().getItemInMainHand(), player).size();
        Material mat = event.getBlock().getType();
        BlockData data = event.getBlock().getBlockData();

        ItemStack dropItem = new ItemStack(mat);
        dropItem.setAmount(dropCount);

        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                for(int k = -1; k < 2; k++){
                    Block block = event.getBlock().getLocation().clone().add(i, j, k).getBlock();
                    if(block.getType() == mat){
                        block.setType(Material.AIR);
                        for(int l = 0; l < 5; l++){
                            player.getWorld().spawnParticle(Particle.BLOCK_CRUMBLE, block.getLocation(), 5, data);
                        }
                        player.getWorld().dropItemNaturally(block.getLocation(), dropItem);
                    }
                }
            }
        }
    }
}
