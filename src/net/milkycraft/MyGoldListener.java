package net.milkycraft;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
				boolean iron = plugin.getConfig().getBoolean(
						"disable.mine.ironore");
				boolean gold = plugin.getConfig().getBoolean(
						"disable.mine.goldore");
				boolean lapis = plugin.getConfig().getBoolean(
						"disable.mine.lapisore");
				boolean reds = plugin.getConfig().getBoolean(
						"disable.mine.redstoneore");
				boolean ironb = plugin.getConfig().getBoolean(
						"disable.mine.ironblock");
				boolean goldb = plugin.getConfig().getBoolean(
						"disable.mine.goldblock");
				boolean lapisb = plugin.getConfig().getBoolean(
						"disable.mine.lapisblock");
				if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (e.getClickedBlock().getType() == Material.IRON_ORE) {
						if (e.getPlayer().isDead() == iron) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + 2));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.GOLD_ORE) {
						if (e.getPlayer().isDead() == gold) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + 2));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.LAPIS_ORE) {
						if (e.getPlayer().isDead() == lapis) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + 2));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.REDSTONE_ORE) {
						if (e.getPlayer().isDead() == reds) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + 2));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.IRON_BLOCK) {
						if (e.getPlayer().isDead() == ironb) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + 1));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.GOLD_BLOCK) {
						if (e.getPlayer().isDead() == goldb) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + 1));
							if (e.getPlayer().getInventory().getItemInHand()
									.getDurability() > 33) {
								e.getPlayer().getInventory()
										.setItemInHand(null);
								return;
							}
						}
					}
					if (e.getClickedBlock().getType() == Material.LAPIS_BLOCK) {
						if (e.getPlayer().isDead() == lapisb) {
							e.getClickedBlock().breakNaturally();
							p.getItemInHand()
									.setDurability(
											(short) (p.getItemInHand()
													.getDurability() + 1));
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
