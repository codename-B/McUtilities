package net.milkycraft.CommandHandlers;

import net.milkycraft.Goldtools;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MyGoldCommandExecutor implements CommandExecutor {
	Goldtools plugin;
	public MyGoldCommandExecutor(Goldtools instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) { 
	  	String ver = plugin.getDescription().getVersion();  	
		if(args.length == 0) {
			sender.sendMessage(ChatColor.WHITE + "*************************************");
			sender.sendMessage(ChatColor.GOLD + "GoldTools " + ChatColor.RED + ver + ChatColor.GOLD + " by milkywayz loaded!");
			sender.sendMessage(ChatColor.GOLD + "Always check bukkit dev page for latest version");
			sender.sendMessage(ChatColor.GOLD + "Sumbit requests on bukkit dev to have them added!");
			sender.sendMessage(ChatColor.GOLD + "If you find any issues or bugs, please report them!");
			sender.sendMessage(ChatColor.WHITE + "*************************************");
			return true;
		}
	      if(cmd.getName().equalsIgnoreCase("goldtools") && sender.hasPermission("goldtools.help"))
	      {
	      }
	      if(args[0].equalsIgnoreCase("help")) { 	  
			sender.sendMessage(ChatColor.RED +	" GoldTools help page");
			sender.sendMessage(ChatColor.GOLD + " /gold - will convert wood tool to gold");;
			sender.sendMessage(ChatColor.GOLD + " /gt - Info on the plugin");
			sender.sendMessage(ChatColor.GOLD + " /gt reload - Reloads config from disk");
			sender.sendMessage(ChatColor.GREEN + " If you have any more questions, ask on bukkitdev!");
			return true;
	      }
	      if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("goldtools.reload")) { 
	    	  plugin.reloadConfig();
			sender.sendMessage(ChatColor.AQUA + "[GoldTools] " +
					ChatColor.GREEN  + ver + " Configuration reloaded from disk!");
			return true;
	      }
	      if(args.length == 0) 
	      {
				sender.sendMessage(ChatColor.WHITE + "*************************************");
				sender.sendMessage(ChatColor.GOLD + "GoldTools " + ChatColor.RED + ver + ChatColor.GOLD + " by milkywayz loaded!");
				sender.sendMessage(ChatColor.GOLD + "Always check bukkit dev page for latest version");
				sender.sendMessage(ChatColor.GOLD + "Sumbit requests on bukkit dev to have them added!");
				sender.sendMessage(ChatColor.GOLD + "If you find any issues or bugs, please report them!");
				sender.sendMessage(ChatColor.WHITE + "*************************************");
				return true;
	      } else {
		 sender.sendMessage(ChatColor.DARK_RED + " Not enough permission to use that command!");	
		 return false;
	  		}
	}
}

