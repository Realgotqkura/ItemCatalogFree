package org.realgotqkura.itemCatalog.utilities;

import org.bukkit.inventory.ItemStack;

import java.util.Comparator;

public class ItemStackComparators {

    public static final Comparator<ItemStack> BY_DISPLAY_NAME =
            Comparator.comparing((ItemStack i) -> {
                if (i.hasItemMeta() && i.getItemMeta().hasDisplayName()) {
                    return i.getItemMeta().getDisplayName();
                }
                return i.getType().toString();
            });
}
