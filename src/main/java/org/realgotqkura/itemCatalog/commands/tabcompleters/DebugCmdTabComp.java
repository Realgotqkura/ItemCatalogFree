package org.realgotqkura.itemCatalog.commands.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class DebugCmdTabComp implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("itcat_debug")){
            if(args.length == 1){
                List<String> lore = new ArrayList<>();
                lore.add("resetHeavySword");
                return lore;
            }
        }
        return List.of();
    }
}
