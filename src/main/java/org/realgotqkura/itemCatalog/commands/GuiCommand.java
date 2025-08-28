package org.realgotqkura.itemCatalog.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.guis.InitialGUI;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

public class GuiCommand implements CommandExecutor {

    private ItemCatalog plugin;

    public GuiCommand(ItemCatalog plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("itemcatalog")){
            if(!player.hasPermission("ItemCatalog.op")){
                player.sendMessage(RandomUtils.color("&cYou don't have permission to use this command!"));
                return true;
            }

            InitialGUI gui = new InitialGUI(plugin);
            gui.createInv(player);
            return true;
        }


        return false;
    }
}
