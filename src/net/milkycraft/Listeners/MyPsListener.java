package net.milkycraft.Listeners;

import java.util.HashMap;
import java.util.Map;

import net.milkycraft.Goldtools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MyPsListener implements Listener {

	public Map<Player, Boolean> cooling = new HashMap<Player, Boolean>();
	private Goldtools plugin;

	public MyPsListener(Goldtools instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onLeftClick(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		int time = plugin.getConfig().getInt("Instantmine.cooldown.seconds");
		long xime = time*20;
		int damage = plugin.getConfig().getInt("mine.ore.tooldamage");
		int tdamage = plugin.getConfig().getInt("mine.precious.tooldamage");
		if (e.getPlayer().getItemInHand().getType() == Material.GOLD_PICKAXE) {
			if (e.getAction() == Action.LEFT_CLICK_AIR) {
				return;
			}
			if(Bukkit.getServer().getPluginManager().getPlugin("mcMMO") == null) {
				if (e.getAction() == Action.RIGHT_CLICK_AIR) {
					e.setCancelled(true);
					return;
				}
				if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					e.setCancelled(true);
					return;
				}
			}
			if (e.getPlayer().getItemInHand().getType() == Material.GOLD_PICKAXE) {
				if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
					final Player player = e.getPlayer();
					if (!cooling.containsValue(true)) {
						if (Bukkit.getServer().getPluginManager()
								.getPlugin("WorldGuard") == null) {
								if (e.getClickedBlock().getType() == Material.IRON_ORE) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.ironore")) {
										toggleCoolingState(player);
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
											return;
										}
									}
								}
								if (e.getClickedBlock().getType() == Material.GOLD_ORE) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.goldore")) {
										toggleCoolingState(player);
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
											return;
										}
									}
								}
								if (e.getClickedBlock().getType() == Material.LAPIS_ORE) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.lapisore")) {
										toggleCoolingState(player);
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
											return;
										}
									}
								}
								if (e.getClickedBlock().getType() == Material.REDSTONE_ORE) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.redstoneore")) {
										toggleCoolingState(player);
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
											return;
										}
									}
								}
								if (e.getClickedBlock().getType() == Material.IRON_BLOCK) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.ironblock")) {
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
											return;
										}
									}
								}
								if (e.getClickedBlock().getType() == Material.GOLD_BLOCK) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.goldblock")) {
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
											return;
										}
									}
								}
								if (e.getClickedBlock().getType() == Material.LAPIS_BLOCK) {
									if (!plugin.getConfig().getBoolean(
											"disable.mine.goldblock")) {
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
											return;
										}
									}
								}
								if (cooling.containsKey(player)) {
									plugin.getServer()
											.getScheduler()
											.scheduleSyncDelayedTask(plugin,
													new Runnable() {
														@Override
														public void run() {
															if(!player.hasPermission("goldtools.cooldown.bypass")) {
															toggleCoolingState(player);
															}
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
