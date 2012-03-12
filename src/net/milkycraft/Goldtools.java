package net.milkycraft;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class Goldtools extends JavaPlugin {
	@Override
	public void onEnable() {
		final MyGoldListener goldListener = new MyGoldListener(this);
		final MyBlockListener blockListener = new MyBlockListener();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(goldListener, this);
		pm.registerEvents(blockListener, this);
		saveConfig();
		FileConfiguration cfg = this.getConfig();
		cfg = getConfig();
		this.getConfig();
		this.getServer().getPluginManager();
		this.getDescription();
		cfg.addDefault("Source.item", 266);
		cfg.addDefault("Source.amount", 10);
		cfg.addDefault("disable.mine.ironore", false);
		cfg.addDefault("disable.mine.goldore", false);
		cfg.addDefault("disable.mine.lapisore", false);
		cfg.addDefault("disable.mine.redstoneore", false);
		cfg.addDefault("disable.mine.ironblock", true);
		cfg.addDefault("disable.mine.goldblock", true);
		cfg.addDefault("disable.mine.lapisblock", true);
		cfg.addDefault("disable.mine.cobweb", true);
		cfg.options().copyDefaults(true);
		saveConfig();
	}
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) { 
		 final Enchantment SILK_TOUCH = new EnchantmentWrapper(33);
	      if( !(sender instanceof Player) ){
	          sender.sendMessage(ChatColor.RED + "[GoldTool] Sorry but the console cannot use these commands.");
	          return true;
	      } 
	      if(commandLabel.equalsIgnoreCase("gold")) {
	    	  int itm = this.getConfig().getInt("Charge.item");
	    	  int amt = this.getConfig().getInt("Charge.amount");	
			 if(((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_HOE
					&& sender.hasPermission("goldtools.hoe") && ((Player) sender).getInventory().contains(itm, amt)) {
						((Player) sender).getInventory().getItemInHand().setType(Material.GOLD_HOE);
						sender.sendMessage(ChatColor.GOLD + "[GoldTools]" + ChatColor.AQUA + 
							"Successfully converted wood hoe to gold hoe!");
						return true;
					}
			 if(((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_SPADE
					&& sender.hasPermission("goldtools.shovel") && ((Player) sender).getInventory().contains(itm, amt)) {
						((Player) sender).getInventory().getItemInHand().setType(Material.GOLD_SPADE);
						sender.sendMessage(ChatColor.GOLD + "[GoldTools]" + ChatColor.AQUA + 
							"Sucessfully converted wood shovel to gold shovel!");
							return true;
			}
			if(((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_SWORD
					&& sender.hasPermission("goldtools.sword") && ((Player) sender).getInventory().contains(itm, amt)) {
						((Player) sender).getInventory().getItemInHand().setType(Material.GOLD_SWORD);
;						sender.sendMessage(ChatColor.GOLD + "[GoldTools]" + ChatColor.AQUA + 
							"Sucessfully onverted wood sword to gold sword!");
							return true;	
							}
			 if(((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_AXE 
					&& sender.hasPermission("goldtools.axe") && ((Player) sender).getInventory().contains(itm, amt)) {
					((Player) sender).getInventory().getItemInHand().setType(Material.GOLD_AXE);
					sender.sendMessage(ChatColor.GOLD + "[GoldTools]" + ChatColor.AQUA + 
							"Sucessfully converted wood axe to gold!");
					return true;
			 }
			if(((Player) sender).getInventory().getItemInHand().getType() == Material.WOOD_PICKAXE
					&& sender.hasPermission("goldtools.pick") && ((Player) sender).getInventory().contains(itm, amt)) {
					((Player) sender).getInventory().getItemInHand().setType(Material.GOLD_PICKAXE);
					sender.sendMessage(ChatColor.GOLD + "[GoldTools]" + ChatColor.AQUA + 
							"Sucessfully converted wood pick to gold pick!");
					return true;
			}
					if(((Player) sender).getInventory().getItemInHand().getType() == Material.GOLD_PICKAXE 
							&& sender.hasPermission("goldtools.silk")) {
						((Player ) sender).getItemInHand().addEnchantment(SILK_TOUCH, 1);
						 sender.sendMessage(ChatColor.GOLD + "[GoldTools]" + ChatColor.AQUA + 
								 "Silk touch added to golden pickaxe!");	
						return true;
					}
					if(((Player) sender).getInventory().getItemInHand().getType() == Material.GOLD_AXE
							&& sender.hasPermission("goldtools.silk")) {
						((Player ) sender).getItemInHand().addEnchantment(SILK_TOUCH, 1);
						 sender.sendMessage(ChatColor.GOLD + "[GoldTools]" + ChatColor.AQUA + 
								 "Silk touch added to golden axe!");	
						return true;	
	      } else {
		 sender.sendMessage(ChatColor.GOLD + "[GoldTools]" + ChatColor.DARK_RED + "Could not complete specific action!");		
		 return false;
	  		}
		}   
		return false;
	}
@Override
public void onDisable() {
Logger log = Logger.getLogger("Minecraft");
{
	log.info("GoldTools disabled");
}}}