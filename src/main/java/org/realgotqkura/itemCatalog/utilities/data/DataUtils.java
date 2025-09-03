package org.realgotqkura.itemCatalog.utilities.data;

import org.realgotqkura.itemCatalog.ItemCatalog;

public class DataUtils {

    public void loadCustomData(ItemCatalog plugin){
        checkIntIfNull(plugin, "customModelData.chronoClock", 21);
        checkIntIfNull(plugin, "customModelData.echoBow", 24);
        checkIntIfNull(plugin, "customModelData.giantHammer", 26);
        checkIntIfNull(plugin, "customModelData.flameSword", 20);
        checkIntIfNull(plugin, "customModelData.sandstormDagger", 27);
        checkIntIfNull(plugin, "customModelData.echoBlade", 34);
    }

    private static void checkIntIfNull(ItemCatalog plugin, String configLine, int defaultValue) {
        if (plugin.getConfig().get(configLine) == null) {
            plugin.getConfig().set(configLine, defaultValue);
            plugin.saveConfig();
        }
    }
}
