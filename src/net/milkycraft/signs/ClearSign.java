package net.milkycraft.signs;

import net.milkycraft.McLevelUp;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ClearSign implements Listener {
	McLevelUp plugin;
	private static String line = "[McReset]";
	public ClearSign(McLevelUp instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onSignCreate(SignChangeEvent e) {
		Sign s = (Sign) e.getBlock().getState();
		Player p = e.getPlayer();
		if (s instanceof Sign) {
			if (s.getLine(0).equalsIgnoreCase(line)) {
				if (e.getPlayer().hasPermission(
						"mcutils.create.reset")
						|| e.getPlayer().hasPermission("mcutils.create.*")) {
						p.sendMessage(ChatColor.GREEN
								+ " Sucessfully created a reset data sign");
						e.setLine(0, line);
						e.setLine(1, "WARNING: THIS");
						e.setLine(2, "WILL RESET YOUR");
						e.setLine(3, "MCMMO SKILLS");
						return;
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
