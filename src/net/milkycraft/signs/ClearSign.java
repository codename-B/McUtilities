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
	
	public ClearSign(McLevelUp instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onSignCreate(SignChangeEvent e) {
		Player p = e.getPlayer();
		if ((Sign) e.getBlock().getState() instanceof Sign) {
			if (e.getLine(0).equalsIgnoreCase("[McReset]")) {
				if (e.getPlayer().hasPermission(
						"mcutils.create.reset")
						|| e.getPlayer().hasPermission("mcutils.create.*")) {
						p.sendMessage(ChatColor.GREEN
								+ " Sucessfully created a reset data sign");
						e.setLine(0, "[McReset]");
						e.setLine(1, "&4THIS SIGN ");
						e.setLine(2, "&4WILL RESET");
						e.setLine(3, "&4YOUR SKILLS");
						return;
				} else {
					e.setLine(0, "&4Sorry but you");
					e.setLine(1, "&4need permission");
					e.setLine(2, "&4to do that");
					e.getPlayer()
					.sendMessage(
							ChatColor.RED 
									+ " You do not have permission to create [McReset] signs");
					return;
				}
			}
		}
	}
}
