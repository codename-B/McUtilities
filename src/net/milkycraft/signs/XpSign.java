package net.milkycraft.signs;

import net.milkycraft.McLevelUp;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class XpSign implements Listener {
	McLevelUp plugin;
	public XpSign(McLevelUp instance) {
		plugin = instance;
	}
	/*
	 * Could anyone tell me the point of event priority? haha 
	 */
	@EventHandler(priority = EventPriority.LOW)
	public void onSignCreate(SignChangeEvent e) {
		Player p = e.getPlayer();
		String[] skillnamess = { "ARCHERY", "ACROBATICS", "ALL", "AXES",
				"EXCAVATION", "FISHING", "HERBALISM", "MINING", "REPAIR",
				"SWORDS", "TAMING", "UNARMED", "WOODCUTTING" };
		if (e.getBlock().getState() instanceof Sign) {
			if (e.getLine(0).equalsIgnoreCase("[LevelUp]")) {
				// checks if 1st line on sign is [levelup] in any case
				if (p.hasPermission("mcutils.create.xp")
						|| p.hasPermission("mcutils.create.*")) { 	
					
				// Test if line 3 has a number (excludes "xp" from int parse)
				boolean lino = true;
				if (!e.getLine(2).isEmpty()) {
					try {
						Integer.parseInt(e.getLine(2).replace("xp", ""));
					} catch (Exception ex) {
						p.sendMessage(ChatColor.RED
								+ "Line 3 should be a number!");
						lino = false;
					}
				}
								
				// Test if line two contains valid skilltype
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
				
					/*
					 * Checks if any line is empty, because the sign wont work with out all lines filled			
					 */
			if (e.getLine(1).isEmpty()) {
				p.sendMessage(ChatColor.RED
						+ " Line 2 should be the mcMMO skill"); }
			if (e.getLine(2).isEmpty()) {
				p.sendMessage(ChatColor.RED
						+ " Line 3 should be the amount of xp"); }
			if (e.getLine(3).isEmpty()) {
				p.sendMessage(ChatColor.RED
						+ " Line 4 should be the level up cost"); }
					/*
					 * A rather solid way of alerting player they created the shop correctly
					 * Checks for:
					 * Lines to not be empty
					 * Line 2 to contain correct skill
					 * Line 3 to contain a integer					 
					 */
					if (!e.getLine(1).isEmpty() && !e.getLine(2).isEmpty()
							&& !e.getLine(3).isEmpty() && lino && derp) {
						p.sendMessage(ChatColor.GREEN
								+ " Sucessfully created a level up sign");
						if(derp && lino) {
						String CappedSkill = Character.toUpperCase(e.getLine(1).charAt(0)) + e.getLine(1).substring(1);
						e.setLine(0, "[LevelUp]");
						e.setLine(1, CappedSkill);
						return;
						}
					}	
		} else {
			/*
			 * Rape the sign if they have no permission, makes it impossible to make the required sign
			 * if the noob who tries to make is has no permz
			 */
			e.setLine(0, "&4Sorry but you");
			e.setLine(1, "&4need permission");
			e.setLine(2, "&4to do that");
			e.getPlayer()
			.sendMessage(
					ChatColor.RED 
							+ " You do not have permission to create [LevelUp] signs");
			return;
		}
		}
		}
	}
}
