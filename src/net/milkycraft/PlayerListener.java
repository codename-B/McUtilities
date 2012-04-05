package net.milkycraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.block.Sign;

public class PlayerListener implements Listener {
	McLevelUp plugin;

	public PlayerListener(McLevelUp instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onSignCreate(SignChangeEvent e) {
		Sign s = (Sign) e.getBlock().getState();
		Player p = e.getPlayer();
		String[] skillnamess = { "ARCHERY", "ACROBATICS", "ALL", "AXES",
				"EXCAVATION", "FISHING", "HERBALISM", "MINING", "REPAIR",
				"SWORDS", "TAMING", "UNARMED", "WOODCUTTING" };
		if (e.getBlock().getState() instanceof Sign) {
			if (e.getLine(0).equalsIgnoreCase("[LevelUp]")) {
				if (p.hasPermission("mclevelup.create")
						|| p.hasPermission("mclevelup.create.*")) {
				} else {
					e.setLine(0, "NoPerms");
				}
				if (e.getLine(1).isEmpty()) {
					p.sendMessage(ChatColor.RED
							+ " Line 2 should be the mcMMO skill");
				}
				if (e.getLine(2).isEmpty()) {
					p.sendMessage(ChatColor.RED
							+ " Line 3 should be the amount of xp");
				}
				if (e.getLine(3).isEmpty()) {
					p.sendMessage(ChatColor.RED
							+ " Line 4 should be the level up cost");
				}
				boolean lino = true;
				if (!e.getLine(2).isEmpty()) {
					try {
						Integer.parseInt(e.getLine(2).replace("xp", ""));
					} catch (Exception ex) {
						p.sendMessage(ChatColor.RED
								+ "Line 3 must be an integer!");
						lino = false;
					}
				}
				boolean derp = false;
				for (String st : skillnamess) {
					if (!e.getLine(1).isEmpty()) {
						if (e.getLine(1).equalsIgnoreCase(st)) {
							derp = true;
						}
					}
				}
				if (!derp) {
					e.getPlayer()
							.sendMessage(
									ChatColor.RED
											+ "Line 2 contains invalid mcMMO Skillname");
					return;
				}
				if (e.getPlayer().hasPermission(
						"mclevelup.create." + s.getLine(1).toLowerCase())
						|| e.getPlayer().hasPermission("mclevelup.create.*")) {
					if (!e.getLine(1).isEmpty() && !e.getLine(2).isEmpty()
							&& !e.getLine(3).isEmpty() && lino) {
						p.sendMessage(ChatColor.GREEN
								+ " Sucessfully created a level up sign");
						e.setLine(0, "[LevelUp]");
						return;
					}
				} else {
					e.setLine(0, "Requires");
					e.setLine(1, "Permission!");
					e.getPlayer()
							.sendMessage(
									ChatColor.RED
											+ " You do not have permission to create Levelup signs");
					return;
				}
			}
		}
	}
}
