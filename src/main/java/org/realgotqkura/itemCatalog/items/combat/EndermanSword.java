package org.realgotqkura.itemCatalog.items.combat;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;
import org.realgotqkura.itemCatalog.utilities.SoundAndEffectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EndermanSword implements Listener {

    private static HashMap<UUID, Double> cooldowns = new HashMap<>();

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public EndermanSword(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.endermanSword");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#c757ff", "Enderman Sword"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8An endermans... well, forget about it."));
        lore.add("");
        lore.add(RandomUtils.color("&e&lRIGHT CLICK &6Teleport"));
        lore.add(RandomUtils.color("&7Teleports you &a10 &7blocks in front of you"));
        lore.add(RandomUtils.color("&7Cooldown: &b5s"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "EndermanSword");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void click(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if(!RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "EndermanSword"))
            return;

        int cooldown = 5;

        if(!RandomUtils.checkCooldown(player, cooldown, cooldowns))
            return;

        Vector dir = player.getLocation().getDirection();
        dir = dir.normalize();
        dir = dir.multiply(10);
        Location tpLoc = player.getLocation().add(dir);

        if(tpLoc.getBlock().getType() == Material.AIR){
            player.teleport(tpLoc);
        }else{
            player.sendMessage(RandomUtils.color("&cBlock is not air!"));
        }

        SoundAndEffectUtils.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2);
        SoundAndEffectUtils.makeParticle(player.getLocation(), Particle.DRAGON_BREATH, 4, 4, -1);
    }
}
