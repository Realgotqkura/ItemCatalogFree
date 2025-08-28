package org.realgotqkura.itemCatalog.utilities;

import org.bukkit.Bukkit;

public class GLogger {

    public static void log(GLoggerSeverity severity, String message){
        Bukkit.getConsoleSender().sendMessage(severity.toString() + ": " + message);
    }

    public static void logBroadcast(GLoggerSeverity severity, String message){
        Bukkit.broadcastMessage(severity.toString() + ": " + message);
    }
}
