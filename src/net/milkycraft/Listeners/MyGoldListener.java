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
/*
 * This class does the following:
 * - Breaks the blocks
 * - Delivers damage to tool
 * - Checks for null on actions
 * - Handle cool downs
 */
public class MyGoldListener implements Listener {
	public Map<Player, Boolean> cooling = new HashMap<Player, Boolean>();
	private Goldtools plugin;
	
	public MyGoldListener(Goldtools instance) {
		plugin = instance;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onLeftClick(PlayerInteractEvent e) {
		 Player p = e.getPlayer();	
		int time = plugin.getConfig().getInt("mine.cooldown.seconds");
		/*
		 * Cooldown in config is a int, that converts to a long by getting multiplied by 20
		 */
		long xime = time * 20;	
		/*
		 * If the player left clicks the air with a gold pick, then it would send a error to console
		 * If the player right clicks a block or air, and mcmmo isnt installed it would send an error
		 * The null checks block those errors from occuring
		 * mcMMO handles the action of right clicks already but if mcmmo isnt installed it can't
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
			 Location loc = e.getClickedBlock().getLocation();
		     final Player player = e.getPlayer();
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
									chechs(e, player, player);
											Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
											toggleCoolingState(player);
											return;
										}
								}
								if (e.getClickedBlock().getType() == Material.GOLD_ORE) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.goldore") || player.hasPermission("goldtools.mine.goldore")) {
									chechs(e, player, player);
											Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
											toggleCoolingState(player);
											return;
										}	
								}
								if (e.getClickedBlock().getType() == Material.LAPIS_ORE) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.lapisore") || player.hasPermission("goldtools.mine.lapisore")) {
									chechs(e, player, player);
											Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
											toggleCoolingState(player);
											return;
										}	
								}
								if (e.getClickedBlock().getType() == Material.REDSTONE_ORE) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.redstoneore") || player.hasPermission("goldtools.mine.redstoneore")) {
									chechs(e, player, player);
											Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
											toggleCoolingState(player);
											return;
										}
								}
								if (e.getClickedBlock().getType() == Material.IRON_BLOCK) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.ironblock") || player.hasPermission("goldtools.mine.ironblock")) {
									checks(e, player, player);
											Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
											toggleCoolingState(player);
											return;
										}
								}
								if (e.getClickedBlock().getType() == Material.GOLD_BLOCK) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.goldblock") || player.hasPermission("goldtools.mine.goldblock")) {
									checks(e, player, player);
											Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
											toggleCoolingState(player);
											return;
										}
								}
								if (e.getClickedBlock().getType() == Material.LAPIS_BLOCK) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.lapisblock")) {
									checks(e, player, player);
											Goldtools.lbconsumer.queueBlockBreak(e.getPlayer().getName(), e.getClickedBlock().getState());
											toggleCoolingState(player);						
											return;
										}
								}
								if (cooling.containsKey(player)) {
									plugin.getServer()
											.getScheduler()
											.scheduleSyncDelayedTask(plugin,
													new Runnable() {
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
	/*
	 * Below are "shortcuts" for the class..
	 * These make the code alot more neat
	 */
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
	public void checks(PlayerInteractEvent e, Player p, Player player) {
		int tdamage = plugin.getConfig().getInt("mine.precious.tooldamage");
			toggleCoolingState(player);
			e.getClickedBlock().breakNaturally();
			p.getItemInHand()
					.setDurability(
							(short) (p
									.getItemInHand()
									.getDurability() + tdamage));
			if (e.getPlayer().getInventory()
					.getItemInHand()
					.getDurability() > 33) {
				e.getPlayer().getInventory()
						.setItemInHand(null);
			}
		}			

			public void chechs(PlayerInteractEvent e, Player p, Player player) {
				int damage = plugin.getConfig().getInt("mine.ore.tooldamage");
					e.getClickedBlock().breakNaturally();
					p.getItemInHand()
							.setDurability(
									(short) (p
											.getItemInHand()
											.getDurability() + damage));
					if (e.getPlayer().getInventory()
							.getItemInHand()
							.getDurability() > 33) {
						e.getPlayer().getInventory()
								.setItemInHand(null);					
		}
	}
}
