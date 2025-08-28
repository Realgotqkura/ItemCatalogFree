package org.realgotqkura.itemCatalog.items.combat;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;
import org.realgotqkura.itemCatalog.utilities.SoundAndEffectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class VampireDagger implements Listener {

    private static HashMap<UUID, Double> cooldowns = new HashMap<>();

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public VampireDagger(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.vampireDagger");
    }


    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#a12e00", "Vampire Dagger"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8A sword gifted from the underworld"));
        lore.add("");
        lore.add(RandomUtils.color("&e&lON ENTITY HIT &6Life Steal"));
        lore.add(RandomUtils.color("&7Heals &a1 &7heart of health."));
        lore.add(RandomUtils.color("&7Cooldown: &b3s"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "VampireSword");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void click(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            Player player = (Player) event.getDamager();

            if(!RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "VampireSword"))
                return;

            int cooldown = 3;

            if(!RandomUtils.checkCooldown(player, cooldown, cooldowns))
                return;

            player.setHealth(player.getHealth() + 2);
            SoundAndEffectUtils.makeParticle(player.getLocation(), Particle.HEART, 2, 3, 0);
        }


    }
}
