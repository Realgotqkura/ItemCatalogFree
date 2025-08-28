package org.realgotqkura.itemCatalog.utilities;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.guis.items.GeneralGuiItems;

public class GuiSorting {

    private ItemCatalog plugin;
    private GeneralGuiItems items;

    public GuiSorting(ItemCatalog plugin){
        this.plugin = plugin;
        items = new GeneralGuiItems();
    }

    public void fillLowestRow(Inventory inv){
        int size = inv.getSize();
        int startingIndex = size - 9;
        for(int i = startingIndex; i < size; i++){
            inv.setItem(i, items.guiGlassDark());
        }
    }
    public void getInner(Inventory inv) {
        int size = inv.getSize();
        if(size == 36) {
            for(int i = 0; i < size; i++) {
                if(i > 9 && i < 17 || i > 18 && i < 26) {
                    if(inv.getItem(i) == null) {
                        inv.setItem(i, items.guiGlass());
                    }
                }
            }
            return;
        }
        for(int i = 0; i < size; i++) {
            if(i > 9 && i < 17 || i > 18 && i < 26 || i > 27 && i < 35 || i > 36 && i < 44) {
                if(inv.getItem(i) == null) {
                    inv.setItem(i, items.guiGlass());
                }
            }
        }
    }

    private void removeInner(Inventory inv) {
        int size = inv.getSize();
        for(int i = 0; i < size; i++) {
            if(i > 9 && i < 17 || i > 18 && i < 26 || i > 27 && i < 35 || i > 36 && i < 44) {
                inv.setItem(i, new ItemStack(Material.AIR));
            }
        }
    }

    public void getOuter(Inventory inv, boolean removein) {
        int size = inv.getSize();
        getInner(inv);
        for(int i = 0; i < size; i++) {
            if(inv.getItem(i) == null) {
                inv.setItem(i, items.guiGlassDark());
            }
        }
        if(removein) {
            removeInner(inv);
        }
    }

    public void corners(Inventory inv, ItemStack itemStack){
        inv.setItem(0, itemStack);
        inv.setItem(8, itemStack);
        inv.setItem(inv.getSize() - 1, itemStack);
        inv.setItem(inv.getSize() - 9, itemStack);
    }

    public GeneralGuiItems getItems() {
        return items;
    }

}
