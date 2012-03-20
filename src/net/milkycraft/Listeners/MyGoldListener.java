package net.milkycraft.Listeners;

import java.util.HashMap;
import java.util.Map;

import net.milkycraft.Goldtools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MyGoldListener implements Listener {
	public Map<Player, Boolean> cooling = new HashMap<Player, Boolean>();
	private Goldtools plugin;

	public MyGoldListener(Goldtools instance) {
		plugin = instance;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onLeftClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		/*
		 * If the player left clicks the air with a gold pick, then it would
		 * send a error to console If the player right clicks a block or air,
		 * and mcmmo isnt installed it would send an error The null checks block
		 * those errors from occuring mcMMO handles the action of right clicks
		 * already but if mcmmo isnt installed it can't
		 */
		if (e.getPlayer().getItemInHand().getType() == Material.GOLD_PICKAXE) {
			if (e.getAction() == Action.LEFT_CLICK_AIR) {
				return;
			}
			if (Bukkit.getServer().getPluginManager().getPlugin("mcMMO") == null) {
				if (e.getAction() == Action.RIGHT_CLICK_AIR) {
					return;
				}
				if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					return;
				}
			}
			// End null checks
	int time = plugin.getConfig().getInt("mine.cooldown.seconds");
	long xime = time * 20;
	Location loc = e.getClickedBlock().getLocation();
	final Player player = e.getPlayer();
	int tdamage = plugin.getConfig().getInt("mine.precious.tooldamage");
	int damage = plugin.getConfig().getInt("mine.ore.tooldamage");
	if (e.getPlayer().getItemInHand().getType() == Material.GOLD_PICKAXE) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (!cooling.containsValue(true) || p.hasPermission("goldtools.cooldown.bypass")) {
				if (Bukkit.getServer().getPluginManager()
						.getPlugin("WorldGuard") != null) {
					if (p.hasPermission("goodtools.worldguard.bypass")
							|| Goldtools.worldguardPlugin.canBuild(p,
									loc)) {
						if (e.getClickedBlock().getType() == Material.IRON_ORE) {
							if (!plugin.getConfig().getBoolean(
									"disable.mine.ironore") || player.hasPermission("goldtools.mine.ironore")) {
								e.getClickedBlock().breakNaturally();
								toggleCoolingState(player);
								p.getItemInHand()
										.setDurability(
												(short) (p
														.getItemInHand()
														.getDurability() + damage));
								Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
								if (e.getPlayer().getInventory()
										.getItemInHand()
										.getDurability() > 33) {
									e.getPlayer().getInventory()
											.setItemInHand(null);				
									return;
								}
							}
						}
						if (e.getClickedBlock().getType() == Material.GOLD_ORE) {
							if (!plugin.getConfig().getBoolean(
									"disable.mine.goldore") || player.hasPermission("goldtools.mine.goldore")) {
								e.getClickedBlock().breakNaturally();
								p.getItemInHand()
										.setDurability(
												(short) (p
														.getItemInHand()
														.getDurability() + damage));
								Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
						toggleCoolingState(player);
								if (e.getPlayer().getInventory()
										.getItemInHand()
										.getDurability() > 33) {
									e.getPlayer().getInventory()
											.setItemInHand(null);									
									return;
								}
							}
						}
						if (e.getClickedBlock().getType() == Material.LAPIS_ORE) {
							if (!plugin.getConfig().getBoolean(
									"disable.mine.lapisore") || player.hasPermission("goldtools.mine.lapisore")) 
								e.getClickedBlock().breakNaturally();
								p.getItemInHand()
										.setDurability(
												(short) (p
														.getItemInHand()
														.getDurability() + damage));
								Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
						toggleCoolingState(player);
								if (e.getPlayer().getInventory()
										.getItemInHand()
										.getDurability() > 33) {
									e.getPlayer().getInventory()
											.setItemInHand(null);									
									return;
								}
							}
						}
						if (e.getClickedBlock().getType() == Material.REDSTONE_ORE) {
							if (!plugin.getConfig().getBoolean(
									"disable.mine.redstoneore") || player.hasPermission("goldtools.mine.redstoneore")) {
								e.getClickedBlock().breakNaturally();
								p.getItemInHand()
										.setDurability(
												(short) (p
														.getItemInHand()
														.getDurability() + damage));
								Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
						toggleCoolingState(player);
								if (e.getPlayer().getInventory()
										.getItemInHand()
										.getDurability() > 33) {
									e.getPlayer().getInventory()
											.setItemInHand(null);
									
									return;
								}
							}
						}
						if (e.getClickedBlock().getType() == Material.IRON_BLOCK) {
							if (!plugin.getConfig().getBoolean(
									"disable.mine.ironblock") || player.hasPermission("goldtools.mine.ironblock")) {
								e.getClickedBlock().breakNaturally();
								p.getItemInHand()
										.setDurability(
												(short) (p
														.getItemInHand()
														.getDurability() + tdamage));
								Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
						toggleCoolingState(player);
								if (e.getPlayer().getInventory()
										.getItemInHand()
										.getDurability() > 33) {
									e.getPlayer().getInventory()
											.setItemInHand(null);
								
									return;
								}
							}
						}
						if (e.getClickedBlock().getType() == Material.GOLD_BLOCK) {
							if (!plugin.getConfig().getBoolean(
									"disable.mine.goldblock") || player.hasPermission("goldtools.mine.goldblock")) {
								e.getClickedBlock().breakNaturally();
								p.getItemInHand()
										.setDurability(
												(short) (p
														.getItemInHand()
														.getDurability() + tdamage));
								Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
						toggleCoolingState(player);
								if (e.getPlayer().getInventory()
										.getItemInHand()
										.getDurability() > 33) {
									e.getPlayer().getInventory()
											.setItemInHand(null);								
									return;
								}
							}
						}
						if (e.getClickedBlock().getType() == Material.LAPIS_BLOCK) {
							if (!plugin.getConfig().getBoolean(
									"disable.mine.lapisblock")) {
									e.getClickedBlock().breakNaturally();
									p.getItemInHand()
											.setDurability(
													(short) (p
															.getItemInHand()
															.getDurability() + tdamage));
									Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
							toggleCoolingState(player);
									if (e.getPlayer().getInventory()
											.getItemInHand()
											.getDurability() > 33) {
										e.getPlayer().getInventory()
									.setItemInHand(null);									
										return;
									}
								}
							}
							if (cooling.containsKey(player)) {
								if (cooling.containsValue(true)) {
									plugin.getServer()
											.getScheduler()
											.scheduleSyncDelayedTask(
													plugin, new Runnable() {
														@Override
														public void run() {
															toggleCoolingState(player);
														}
													}, xime);
									return;
								}
							}
						}
					}
				}
			}
		}
	}

public void toggleCoolingState(Player player) {
	if (cooling.containsKey(player)) {
		if (cooling.get(player)) {
			cooling.put(player, false);
		} else {
			cooling.put(player, true);
		}
	} else {
		cooling.put(player, true);
	}
}
}
