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

import com.gmail.nossr50.mcMMO;

public class GodSignListener implements Listener {
	McLevelUp plugin;
	private static String line = "[McGod]";
	public GodSignListener(McLevelUp instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onSignClick(PlayerInteractEvent e) {
		if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			return;
		}
		final Block block = e.getClickedBlock();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			// Check if block is null, block is null when clicking an entity or
			// the air
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
					if (!s.getLine(1).isEmpty() && !s.getLine(2).isEmpty()) {
							if (player.hasPermission("mcutils.buy.godmode")
									|| player.hasPermission("mcutils.buy.*")) {
								int time = Integer.parseInt(s.getLine(1).replace("seconds", ""));
								long longtime = time*20;							
								double balance = McLevelUp.econ.getBalance(e
										.getPlayer().getName());
								int cost = Integer.parseInt(s.getLine(2)
											.replaceAll("\\$", "0"));							
								// Makes sure player has enough money
								if(mcMMO.getPlayerProfile(player).getGodMode() == true) {
								if (balance >= cost) {
									e.setCancelled(true);
									mcMMO.getPlayerProfile(player).toggleGodMode();
									player.sendMessage(ChatColor.YELLOW 
											+ "God mode enabled for " + time + " seconds");
									plugin.getServer()
									.getScheduler()
									.scheduleSyncDelayedTask(
											plugin, new Runnable() {
												@Override
												public void run() {
													mcMMO.getPlayerProfile(player).toggleGodMode();
													player.sendMessage(ChatColor.RED 
															+ "God mode has warn out!");
												}
											}, longtime);
									if (!player
											.hasPermission("mcutils.bypass.charge")) {
										McLevelUp.econ
												.withdrawPlayer(e.getPlayer()
														.getName(), Double
														.valueOf(s.getLine(2)
																.replaceAll(
																		"\\$",
																		"0")));
										return;
									} // Player doesnt have permission so they
										// get charged the amount on the sign
								} else {
									// Players balance is less than the cost of
									// the sign
									e.setCancelled(true);
									player.sendMessage(ChatColor.RED
											+ "Insufficient funds!");
								}
								}
							} else {
								e.setCancelled(true);
								int time = Integer.parseInt(s.getLine(1).replace("seconds", ""));
								player.sendMessage(ChatColor.RED 
										+ "Already in godmode, wait " + time + " seconds for it to wear");
						}
							} else {
								// Player does have permission to use the sign
								// they are trying to use
								e.setCancelled(true);
								player.sendMessage(ChatColor.RED  + "You dont have permission for " + line + " signs");
						} 						
				}
			}
		}
	}
}

