package org.realgotqkura.itemCatalog.items;

import org.bukkit.entity.Item;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.items.combat.*;
import org.realgotqkura.itemCatalog.items.misc.*;
import org.realgotqkura.itemCatalog.utilities.ItemStackComparators;

import java.util.ArrayList;
import java.util.List;

public class ItemsManager {

    public static List<ItemStack> combatItems = new ArrayList<>();
    public static List<ItemStack> miscItems = new ArrayList<>();
    public static List<ItemStack> allItems = new ArrayList<>();

    public static void loadItems(){
        loadCombatItems();
        loadMiscItems();
        allItems.addAll(combatItems);
        allItems.addAll(miscItems);

        allItems.sort(ItemStackComparators.BY_DISPLAY_NAME);
    }

    private static void loadCombatItems(){
        combatItems.add(HeavySword.item());
        combatItems.add(EnderSword.item());
        combatItems.add(VampireDagger.item());
        combatItems.add(SnowWand.item());
        combatItems.add(EndermanSword.item());
        combatItems.add(GuardianShield.item());
        combatItems.add(ZephyrBow.item());
        combatItems.add(InfernoStaff.item());
        combatItems.add(PoisonFang.item());
        combatItems.add(Stormbringer.item());
        combatItems.add(GolemUnderwear.item());
        combatItems.add(EchoBow.item());
    }

    private static void loadMiscItems(){
        miscItems.add(RabbitBoots.item());
        miscItems.add(HastyPickaxe.item());
        miscItems.add(LumberjackAxe.item());
        miscItems.add(TorchOfLight.item());
        miscItems.add(SpeedyBoots.item());
        miscItems.add(HarvesterHoe.item());
        miscItems.add(TurtHelmet.item());
        miscItems.add(ChickenWings.item());
        miscItems.add(PrimitiveShadowWand.item());
        miscItems.add(ChronoClock.item());
    }


    public static void loadItemEvents(ItemCatalog plugin){
        plugin.getServer().getPluginManager().registerEvents(new HeavySword(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EnderSword(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new VampireDagger(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new RabbitBoots(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new HastyPickaxe(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SnowWand(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LumberjackAxe(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new TorchOfLight(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EndermanSword(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GuardianShield(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ZephyrBow(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SpeedyBoots(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new InfernoStaff(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new HarvesterHoe(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new TurtHelmet(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PoisonFang(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new Stormbringer(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ChickenWings(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PrimitiveShadowWand(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GolemUnderwear(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ChronoClock(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EchoBow(plugin), plugin);
    }

}
