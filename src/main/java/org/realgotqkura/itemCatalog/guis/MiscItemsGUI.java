package org.realgotqkura.itemCatalog.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.items.ItemsManager;
import org.realgotqkura.itemCatalog.utilities.GuiSorting;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;
import org.realgotqkura.itemCatalog.utilities.data.PlayerDataConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MiscItemsGUI implements Listener {

    private ItemCatalog plugin;
    private PlayerDataConfig playerData;

    public static HashMap<Player, List<Inventory>> invLists = new HashMap<>();

    public MiscItemsGUI(ItemCatalog plugin){
        this.plugin = plugin;
        playerData = plugin.getPlayerData();
    }


    public void clearList(Player player){
        invLists.put(player, new ArrayList<>());
    }

    public void createStartInv(Player player) {
        GuiSorting sort = new GuiSorting(plugin);
        String guiName = RandomUtils.color("&eMiscellaneous Items page ");
        clearList(player);
        List<Inventory> inventories = invLists.get(player);
        int itemCount = ItemsManager.miscItems.size();
        int count = 0;
        int currentPage = 0;
        int maxPageCount = 100;
        for(int i = 0; i < maxPageCount; i++){
            Inventory inv = Bukkit.createInventory(null, 54, guiName + currentPage);
            currentPage++;
            sort.fillLowestRow(inv);
            inv.setItem(49, sort.getItems().openPreviousInventoryItem());
            inv.setItem(45, sort.getItems().pages(true));
            inv.setItem(53, sort.getItems().pages(false));
            for(int j = 0; j < itemCount; j++){
                if(j > 44){
                    i++;
                    break;
                }
                if(count < itemCount){
                    inv.setItem(j, ItemsManager.miscItems.get(count));
                    count++;
                }
            }
            /*
            if(bossCount <= 44){
                count = 45;
            }

             */
            inventories.add(inv);
        }
        invLists.replace(player, inventories);
        playerData.getConfig().set("players." + player.getUniqueId().toString() + ".pickerPageMisc", 0);
        playerData.saveConfig();
        player.openInventory(invLists.get(player).get(0));
    }


    @EventHandler
    public void click(InventoryClickEvent event){
        if(!event.getView().getTitle().contains("Miscellaneous Items page "))
            return;

        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(event.getSlot() == 49){
            InitialGUI gui = new InitialGUI(plugin);
            gui.createInv(player);
            return;
        }

        int currentPage = Integer.parseInt(event.getView().getTitle().replaceAll("[^0-9]", ""));
        if(event.getSlot() == 53){
            if(playerData.getConfig().getInt("players." + player.getUniqueId().toString() + ".pickerPageMisc") < 99){
                playerData.getConfig().set("players." + player.getUniqueId().toString() + ".pickerPageMisc", playerData.getConfig().getInt("players." + player.getUniqueId().toString() + ".pickerPageMisc") + 1);
                playerData.saveConfig();
                player.openInventory(invLists.get(player).get(playerData.getConfig().getInt("players." + player.getUniqueId().toString() + ".pickerPageMisc")));
            }
            return;
        }

        if(event.getSlot() == 45){
            if(playerData.getConfig().getInt("players." + player.getUniqueId().toString() + ".pickerPageMisc") > 0){
                playerData.getConfig().set("players." + player.getUniqueId().toString() + ".pickerPageMisc", playerData.getConfig().getInt("players." + player.getUniqueId().toString() + ".pickerPageMisc") - 1);
                playerData.saveConfig();
                player.openInventory(invLists.get(player).get(playerData.getConfig().getInt("players." + player.getUniqueId().toString() + ".pickerPageMisc")));
            }
            return;
        }

        if(event.getSlot() < 45){
            if(event.getCurrentItem() == null)
                return;

            int fishIndex = event.getSlot() + (currentPage * 45);
            player.getInventory().addItem(ItemsManager.miscItems.get(fishIndex));
        }
    }
}
