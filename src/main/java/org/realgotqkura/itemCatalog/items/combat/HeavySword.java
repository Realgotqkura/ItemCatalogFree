package org.realgotqkura.itemCatalog.items.combat;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HeavySword implements Listener {

    private ItemCatalog plugin;
    private static int customModelDataNum;

    public HeavySword(ItemCatalog plugin){
        this.plugin = plugin;
        customModelDataNum = plugin.getConfig().getInt("customModelData.heavySword");
    }

    private HashMap<Player, ItemStack> previousOffHandMap = new HashMap<>();

    public static ItemStack item(){
        ItemStack stack = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.safeHexColor("#878787", "Heavy Sword"));
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8A regular heavy sword"));
        lore.add("");
        lore.add(RandomUtils.color("&7Makes you slower in return for more damage."));
        lore.add("");
        lore.add(RandomUtils.color("&7Speed Nerf: &c-50%"));
        lore.add(RandomUtils.color("&7Damage Buff: &a+150%"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setCustomModelData(customModelDataNum);
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemCatalogID"), PersistentDataType.STRING, "HeavySword");
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void holdEvent(PlayerItemHeldEvent event){
        Player player = event.getPlayer();

        int newSlot = event.getNewSlot();
        int previousSlot = event.getPreviousSlot();

        ItemStack newItem = event.getPlayer().getInventory().getItem(newSlot);
        ItemStack previousItem = event.getPlayer().getInventory().getItem(previousSlot);

        if(RandomUtils.passedItemChecks(newItem, "HeavySword")){
            player.setWalkSpeed(player.getWalkSpeed() / 2);
            player.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue() * 3);
        }

        if(RandomUtils.passedItemChecks(previousItem, "HeavySword")){
            player.setWalkSpeed(player.getWalkSpeed() * 2);
            player.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue() / 3);
        }
    }

    @EventHandler
    public void jump(PlayerMoveEvent event){
        Player player = event.getPlayer();


        if(RandomUtils.passedItemChecks(player.getInventory().getItemInMainHand(), "HeavySword") || RandomUtils.passedItemChecks(player.getInventory().getItemInOffHand(), "HeavySword")){
            if (event.getTo().getY() > event.getFrom().getY()) {
                Vector v = player.getVelocity();
                v.setY(-1); // cancel upward velocity
                player.setVelocity(v);
            }
        }
    }




}
