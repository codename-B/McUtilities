package net.milkycraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MyGoldCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) { 
	      if(commandLabel.equalsIgnoreCase("goldtools") && sender.hasPermission("goldtools.info")){
			sender.sendMessage(ChatColor.WHITE + "*************************************");
			sender.sendMessage(ChatColor.GOLD + "GoldTools v1.3 by milkywayz loaded!");
			sender.sendMessage(ChatColor.GOLD + "For bugs, go to bukkit dev project page");
			sender.sendMessage(ChatColor.GOLD + "View source on github! github.com/milkywayz/GoldTools-");
			sender.sendMessage(ChatColor.WHITE + "*************************************");
      	return true;	     	      
	      } else {
		 sender.sendMessage(ChatColor.DARK_RED + " Not enough permission to use that command!");	
		 return false;
	  		}
	  	}
	}
