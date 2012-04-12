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

public class ClearSignListener implements Listener {
	private static String line = "[McReset]";
	McLevelUp plugin;

	public ClearSignListener(McLevelUp instance) {
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
					if (player.hasPermission("mcutils.buy.reset")) {
						e.setCancelled(true);
						plugin.getPlayerProfile(player).resetAllData();
						plugin.getPlayerProfile(player).save();
						player.sendMessage(ChatColor.GREEN
								+ "Reset all of your mcMMO skills!");
						return;
					} else {
						e.setCancelled(true);
						player.sendMessage(ChatColor.RED 
								+ "You dont have permission for " + line
								+ " signs");
					}
				}
			}
		}
	}
}
