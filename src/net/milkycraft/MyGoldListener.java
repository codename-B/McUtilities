package net.milkycraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class MyGoldListener implements Listener {
	Goldtools plugin;
	public MyGoldListener(Goldtools instance) {
		plugin = instance;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onLeftClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getPlayer().getItemInHand().getType() == Material.GOLD_PICKAXE) {
			if (e.getAction() == Action.LEFT_CLICK_AIR) {
				return;
			}
			if (e.getPlayer().getItemInHand().getType() == Material.GOLD_PICKAXE) {
				int damage = plugin.getConfig().getInt("mine.ore.tooldamage");
				int tdamage = plugin.getConfig().getInt("mine.precious.tooldamage");
				if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
					Location loc = e.getClickedBlock().getLocation();
					WorldGuardPlugin worldGuard = getWorldGuard();
					if(worldGuard.canBuild(p, loc) || p.hasPermission("goodtools.worldguard.bypass")) {
					if (e.getClickedBlock().getType() == Material.IRON_ORE) {
						if (!plugin.getConfig().getBoolean("disable.mine.ironore")) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + damage));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.GOLD_ORE) {
						if (!plugin.getConfig().getBoolean("disable.mine.goldore")) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + damage));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.LAPIS_ORE) {
						if (!plugin.getConfig().getBoolean("disable.mine.lapisore")) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + damage));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.REDSTONE_ORE) {
						if (!plugin.getConfig().getBoolean("disable.mine.redstoneore")) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + damage));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.IRON_BLOCK) {
						if (!plugin.getConfig().getBoolean("disable.mine.ironblock")) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + tdamage));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.GOLD_BLOCK) {
						if (!plugin.getConfig().getBoolean("disable.mine.goldblock")) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + tdamage));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.LAPIS_BLOCK) {
						if (!plugin.getConfig().getBoolean("disable.mine.goldblock")) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + tdamage));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
			}
		}
		}
	}
	}	
		private WorldGuardPlugin getWorldGuard() {
		    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");	 
		    // WorldGuard may not be loaded
		    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
		        return null; // Maybe you want throw an exception instead
		    }	 
		    return (WorldGuardPlugin) plugin;
}}
