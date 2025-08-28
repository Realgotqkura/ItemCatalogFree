package org.realgotqkura.itemCatalog.items.misc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
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

import java.util.*;

public class HarvesterHoe implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public HarvesterHoe(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.harvesterHoe");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.STONE_HOE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#7efa70", "Harvester Hoe"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8Farmers best friend"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lON CROP BREAK &6Replant"));
        lore.add(RandomUtils.color("&7Replants the broken crop"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "HarvesterHoe");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void breakBl(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (!RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "HarvesterHoe"))
            return;

        Block block = event.getBlock();
        BlockData data = block.getBlockData();

        if (!(data instanceof Ageable ageable))
            return;

        // Cancel vanilla break
        event.setCancelled(true);
        HashMap<Material, Material> matMap = new HashMap<>();
        matMap.put(Material.WHEAT_SEEDS, Material.WHEAT);
        matMap.put(Material.CARROT, Material.CARROTS);
        matMap.put(Material.POTATO, Material.POTATOES);
        matMap.put(Material.BEETROOT_SEEDS, Material.BEETROOTS);

        // Drop the normal items manually
        block.breakNaturally(player.getInventory().getItemInMainHand());


        // Also drop seeds/crops from ageable (simulate natural drops)
        block.getDrops(player.getInventory().getItemInMainHand())
                .forEach(item -> block.getWorld().dropItemNaturally(block.getLocation(), item));

        Material seedType = getInventoryCrop(player);
        if (seedType == null)
            return;


        Material plantedType = matMap.get(seedType);
        if (plantedType == null)
            return;

        block.setType(plantedType);

        if (block.getBlockData() instanceof Ageable replantedAgeable) {
            replantedAgeable.setAge(0);
            block.setBlockData(replantedAgeable);
        }

        // Consume 1 seed from inventory
        for (ItemStack stack : player.getInventory().getContents()) {
            if (stack != null && stack.getType() == seedType) {
                stack.setAmount(stack.getAmount() - 1);
                break;
            }
        }
    }
    private Material getInventoryCrop(Player player) {
        List<Material> validCrops = Arrays.asList(
                Material.WHEAT_SEEDS,
                Material.CARROT,
                Material.POTATO,
                Material.BEETROOT_SEEDS
        );

        for (ItemStack stack : player.getInventory().getContents()) {
            if (stack != null && validCrops.contains(stack.getType()) && stack.getAmount() > 0) {
                return stack.getType();
            }
        }
        return null;
    }
}
