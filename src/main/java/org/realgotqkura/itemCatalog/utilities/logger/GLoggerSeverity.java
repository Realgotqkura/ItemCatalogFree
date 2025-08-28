package org.realgotqkura.itemCatalog.utilities.logger;


import org.bukkit.ChatColor;

public enum GLoggerSeverity {

    INFO, WARNING, ERROR, SUGGESTION;

    @Override
    public String toString() {
        switch (this){
            case WARNING:
                return ChatColor.YELLOW + "SupremeFishing: [!WARNING!]";
            case ERROR:
                return ChatColor.RED + "SupremeFishing: [!!ERROR!!]";
            case SUGGESTION:
                return ChatColor.GREEN + "SupremeFishing: [SUGGESTION]";
            default:
                return ChatColor.AQUA + "SupremeFishing: [INFO]";

        }
    }
}
