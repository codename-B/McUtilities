package net.milkycraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MyGoldCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) { 
		if(args.length == 0) {
			sender.sendMessage(ChatColor.WHITE + "*************************************");
			sender.sendMessage(ChatColor.GOLD + "GoldTools v1.5 by milkywayz loaded!");
			sender.sendMessage(ChatColor.GOLD + "Always check bukkit dev page for latest version");
			sender.sendMessage(ChatColor.GOLD + "Sumbit requests on bukkit dev to have them added!");
			sender.sendMessage(ChatColor.GOLD + "View source on github! github.com/milkywayz/GoldTools-");
			sender.sendMessage(ChatColor.WHITE + "*************************************");
			return true;
		}
	      if(cmd.getName().equalsIgnoreCase("goldtools") && sender.hasPermission("goldtools.help"))
	      {
	      }
	      if(args[0].equalsIgnoreCase("help")) { 	  
			sender.sendMessage(ChatColor.RED +	" GoldTools help page");
			sender.sendMessage(ChatColor.GOLD + " /gold - will convert wood tool to gold");
			sender.sendMessage(ChatColor.GOLD + " /gold silk - will add silk touch to gold pick or axe");
			sender.sendMessage(ChatColor.GOLD + " /goldtools perms - A list of the nodes");
			sender.sendMessage(ChatColor.GREEN + " If you have any more questions, ask on bukkitdev!");
			return true;
	      }
	      if(args[0].equalsIgnoreCase("perms") && sender.hasPermission("goldtools.help")) { 	  
			sender.sendMessage(ChatColor.RED +	" GoldTools Permissions page");
			sender.sendMessage(ChatColor.GOLD + " goldtools.pick || axe || sword || hoe || shovel*");
			sender.sendMessage(ChatColor.GOLD + " goldtools.silk - Allows silk touch command to be used");
			sender.sendMessage(ChatColor.GOLD + " goldtools.drop - Allows blocks instantly mined to drop the block");
			sender.sendMessage(ChatColor.GOLD + " *Swap out pick with the tools you want players to convert");
			sender.sendMessage(ChatColor.GREEN + " Any questions? Ask on the bukkit dev project page");
			return true;
	      }
	      if(args.length == 0) 
	      {
				sender.sendMessage(ChatColor.WHITE + "*************************************");
				sender.sendMessage(ChatColor.GOLD + "GoldTools v1.5 by milkywayz loaded!");
				sender.sendMessage(ChatColor.GOLD + "Always check bukkit dev page for latest version");
				sender.sendMessage(ChatColor.GOLD + "Sumbit requests on bukkit dev to have them added!");
				sender.sendMessage(ChatColor.GOLD + "View source on github! github.com/milkywayz/GoldTools-");
				sender.sendMessage(ChatColor.WHITE + "*************************************");
				return true;
	      } else {
		 sender.sendMessage(ChatColor.DARK_RED + " Not enough permission to use that command!");	
		 return false;
	  		}
	  	}
	}
