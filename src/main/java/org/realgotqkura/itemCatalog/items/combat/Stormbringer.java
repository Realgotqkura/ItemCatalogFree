package org.realgotqkura.itemCatalog.items.combat;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Stormbringer implements Listener {

    private static HashMap<UUID, Double> cooldowns = new HashMap<>();

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public Stormbringer(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.stormBringer");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#71a69c", "Storm Bringer"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8A gift from zeus"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lRIGHT CLICK &6Fire from above"));
        lore.add(RandomUtils.color("&7Shoots a lightning at the block you are"));
        lore.add(RandomUtils.color("&7looking at"));
        lore.add(RandomUtils.color("&7Max Distance: &a30 &7blocks"));
        lore.add(RandomUtils.color("&7Cooldown: &a3&30s"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "Stormbringer");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void click(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if(!RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "Stormbringer"))
            return;


        int cooldown = 30;
        if(!RandomUtils.checkCooldown(player, cooldown, cooldowns))
            return;

        int maxDistance = 30;
        RayTraceResult rayTraceResult = player.rayTraceBlocks(maxDistance);

        if(rayTraceResult.getHitBlock() == null)
            return;

        player.getWorld().strikeLightning(rayTraceResult.getHitBlock().getLocation());
    }
}
