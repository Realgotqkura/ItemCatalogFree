package org.realgotqkura.itemCatalog.commands;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.realgotqkura.itemCatalog.ItemCatalog;
import org.realgotqkura.itemCatalog.utilities.RandomUtils;

public class DebugCommand implements CommandExecutor {

    private ItemCatalog plugin;

    public DebugCommand(ItemCatalog plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("itcat_debug")){
            if(!player.hasPermission("ItemCatalog.op")){
                player.sendMessage(RandomUtils.color("&cYou don't have permission to use this command!"));
                return true;
            }

            if(args.length < 1)
                return true;

            String arg = args[0].toLowerCase();

            switch (arg){
                case "resetheavysword":
                    player.setWalkSpeed(0.2f);
                    player.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.ATTACK_DAMAGE).getDefaultValue());
                    player.sendMessage(RandomUtils.color("&aReset!"));
                    break;
            }
        }
        return false;
    }
}
