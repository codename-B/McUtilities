package net.milkycraft.signs;

import net.milkycraft.McLevelUp;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class GodSign implements Listener {
	McLevelUp plugin;
	
	private static String line = "[McGod]";
	public GodSign(McLevelUp instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onSignCreate(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (e.getBlock().getState() instanceof Sign) {
			if (e.getLine(0).equalsIgnoreCase(line)) {
				if (p.hasPermission("mcutils.create.godmode")
						|| p.hasPermission("mcutils.create.*")) {		
					if (e.getLine(1).isEmpty()) {
						p.sendMessage(ChatColor.RED
								+ " Line 2 should be the time godmode lasts");
					}
					if (e.getLine(2).isEmpty()) {
						p.sendMessage(ChatColor.RED
								+ " Line 3 should be the a price");
					}
					if (!e.getLine(1).isEmpty() && !e.getLine(2).isEmpty()) {
						p.sendMessage(ChatColor.GREEN
								+ " Sucessfully created a temp god sign");
						e.setLine(0, line);
						return;
					}
				} else {
					e.setLine(0, "&4Sorry but you");
					e.setLine(1, "&4need permission");
					e.setLine(2, "&4to do that");
					e.getPlayer()
					.sendMessage(
							ChatColor.RED 
									+ " You do not have permission to create" + line + " signs");
					return;
				}
			}
		}
	}
}
