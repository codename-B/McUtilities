package net.milkycraft.CommandHandlers;

import net.milkycraft.Goldtools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;

public class MyGoldCommandExecutor implements CommandExecutor {
	Goldtools plugin;
	public MyGoldCommandExecutor(Goldtools instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) { 
	  	String ver = plugin.getDescription().getVersion();  	
	  	String prayer = ((Player) sender).getName();
		final Enchantment SILK_TOUCH = new EnchantmentWrapper(33);
		int amt = plugin.getConfig().getInt("Economy.gold.charge");
		int cash = plugin.getConfig().getInt("Economy.silk.charge");
		if(args.length == 0) {
			sender.sendMessage(ChatColor.WHITE + "*************************************");
			sender.sendMessage(ChatColor.GOLD + "GoldTools " + ChatColor.RED + ver + ChatColor.GOLD + " by milkywayz loaded!");
			sender.sendMessage(ChatColor.GOLD + "Always check bukkit dev page for latest version");
			sender.sendMessage(ChatColor.GOLD + "Sumbit requests on bukkit dev to have them added!");
			sender.sendMessage(ChatColor.GOLD + "If you find any issues or bugs, please report them!");
			sender.sendMessage(ChatColor.WHITE + "*************************************");
			return true;
		}
	      if(cmd.getName().equalsIgnoreCase("goldtools"))
	      {
	      }
	      if(args[0].equalsIgnoreCase("help") && sender.hasPermission("goldtools.help")) { 	  
			sender.sendMessage(ChatColor.RED +	" GoldTools help page");
			sender.sendMessage(ChatColor.GOLD + " /gt gold - will convert wood tool to gold");;
			sender.sendMessage(ChatColor.GOLD + " /gt silk - add silk touch to your tool");
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
			if(args[0].equalsIgnoreCase("gold")) { 
				if (((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_HOE
						&& sender.hasPermission("goldtools.hoe")) {
					((Player) sender).getInventory().getItemInHand()
							.setType(Material.GOLD_HOE);
					sender.sendMessage(ChatColor.GOLD + "[GoldTools]"
							+ ChatColor.AQUA
							+ "Successfully converted wood hoe to gold hoe!");
					if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
						Goldtools.econ.withdrawPlayer(prayer, amt);
					}					
					return true;
				}
				if (((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_SPADE
						&& sender.hasPermission("goldtools.shovel")) {
					((Player) sender).getInventory().getItemInHand()
							.setType(Material.GOLD_SPADE);
					sender.sendMessage(ChatColor.GOLD + "[GoldTools]"
							+ ChatColor.AQUA
							+ "Sucessfully converted wood shovel to gold shovel!");
					if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
						Goldtools.econ.withdrawPlayer(prayer, amt);				
					}
					return true;
				}
				if (((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_SWORD
						&& sender.hasPermission("goldtools.sword")) {
					((Player) sender).getInventory().getItemInHand()
							.setType(Material.GOLD_SWORD);
					sender.sendMessage(ChatColor.GOLD + "[GoldTools]"
							+ ChatColor.AQUA
							+ "Sucessfully converted wood sword to gold sword!");
					if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
						Goldtools.econ.withdrawPlayer(prayer, amt);
					}			
					return true;
				}
				if (((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_AXE
						&& sender.hasPermission("goldtools.axe")) {
					((Player) sender).getInventory().getItemInHand()
							.setType(Material.GOLD_AXE);
					sender.sendMessage(ChatColor.GOLD + "[G.ldTools]"
							+ ChatColor.AQUA
							+ "Sucessfully converted wood axe to gold!");
					if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
						Goldtools.econ.withdrawPlayer(prayer, amt);				
					}
					return true;
				}
				if (((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_PICKAXE
						&& sender.hasPermission("goldtools.pick")) {
					((Player) sender).getInventory().getItemInHand()
							.setType(Material.GOLD_PICKAXE);
					sender.sendMessage(ChatColor.GOLD + "[GoldTools]"
							+ ChatColor.AQUA
							+ "Sucessfully converted wood pick to gold pick!");
					if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
						Goldtools.econ.withdrawPlayer(prayer, amt);				
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("silk") && sender.hasPermission("goldtools.silk")) { 
				if (((Player) sender).getInventory().getItemInHand().getType() == Material.GOLD_PICKAXE) {
					((Player) sender).getItemInHand().addEnchantment(SILK_TOUCH, 1);
					sender.sendMessage(ChatColor.GOLD + "[GoldTools]"
							+ ChatColor.AQUA
							+ "Silk touch successfully added to golden pickaxe!");
					if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
						Goldtools.econ.withdrawPlayer(prayer, cash);				
					}
					return true;
				}
				if (((Player) sender).getInventory().getItemInHand().getType() == Material.GOLD_AXE) {
					((Player) sender).getItemInHand().addEnchantment(SILK_TOUCH, 1);
					sender.sendMessage(ChatColor.GOLD + "[GoldTools]"
							+ ChatColor.AQUA
							+ "Silk touch successfully added to golden axe!");
					if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
						Goldtools.econ.withdrawPlayer(prayer, cash);			
					}
					return true;
				}	
				}
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
		 sender.sendMessage(ChatColor.GOLD + "[GoldTools]" + ChatColor.UNDERLINE +
				 " Could not complete that Action!");	
		 return false;
	  		}
	}
}

