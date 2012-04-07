package net.milkycraft.signs;

import net.milkycraft.McLevelUp;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class CoolDownSign implements Listener {
	McLevelUp plugin;
	private static String line = "[McCooldown]";
	public CoolDownSign(McLevelUp instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onSignCreate(SignChangeEvent e) {
		Sign s = (Sign) e.getBlock().getState();
		Player p = e.getPlayer();
		if (s instanceof Sign) {
			if (s.getLine(0).equalsIgnoreCase(line)) {
				String price = s.getLine(1);
				if (e.getPlayer().hasPermission(
						"mcutils.create.cooldown")
						|| e.getPlayer().hasPermission("mcutils.create.*")) {
						p.sendMessage(ChatColor.GREEN
								+ " Sucessfully created a reset data sign");
						e.setLine(0, line);
						if(!e.getLine(1).isEmpty()) {
						e.setLine(1, "$"+price);
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
