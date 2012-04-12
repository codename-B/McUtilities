package net.milkycraft.Listeners;

import net.milkycraft.McLevelUp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CCooldownListener implements Listener {
	private static String line = "[McCooldown]";
	McLevelUp plugin;

	public CCooldownListener(McLevelUp instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onSignClick(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			return;
		}
		final Block block = e.getClickedBlock();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (block == null) {
				return;
			}
			final int mat = block.getTypeId();
			if (mat == Material.SIGN_POST.getId()
					|| mat == Material.WALL_SIGN.getId()) {
				BlockState state = e.getClickedBlock().getState();
				final Sign s = (Sign) state;
				final Player player = e.getPlayer();
				if (s.getLine(0).equalsIgnoreCase(line)) {
					if(!s.getLine(1).isEmpty()) {				
					if (player.hasPermission("mcutils.buy.cooldown") ||
							player.hasPermission("mcutils.buy.*")) {
						e.setCancelled(true);
						plugin.getPlayerProfile(player).resetCooldowns();
						player.sendMessage(ChatColor.GREEN
								+ "Reset all of your ability cooldowns!");
						if (!player
								.hasPermission("mcutils.bypass.charge")) {
							McLevelUp.econ
									.withdrawPlayer(e.getPlayer()
											.getName(), Double
											.valueOf(s.getLine(1)
													.replaceAll(
															"\\$",
															"0")));
						}
					} else {
						e.setCancelled(true);
						player.sendMessage(ChatColor.RED 
								+ "You dont have permission for " + line
								+ " signs");
					}
					} else {
						e.setCancelled(true);
						player.sendMessage(ChatColor.RED + "No price has been set!");
					}
				}
			}
		}
	}
}
