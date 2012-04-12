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

import com.gmail.nossr50.datatypes.SkillType;
import com.gmail.nossr50.skills.Skills;

public class XpSignListener implements Listener {
	McLevelUp plugin;
	private static String line = "[LevelUp]";
	
	public XpSignListener(McLevelUp instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onSignClick(PlayerInteractEvent e) {
		if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			return;
		}
		final Block block = e.getClickedBlock();
		String[] skillnamess = { "ARCHERY", "ACROBATICS", "ALL", "AXES",
				"EXCAVATION", "FISHING", "HERBALISM", "MINING", "REPAIR",
				"SWORDS", "TAMING", "UNARMED", "WOODCUTTING" };
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
				/*
				 * Checks to ensure line 1 is [LevelUp] and not anything else
				 * Important to not attach an else to it because then any sign
				 * changed would cause this class to fire for any sign which is
				 * not wanted. To set up a another sign just make 2 new class to
				 * check for the right sign lines and another for when a player
				 * right clicks it
				 */
				if (s.getLine(0).equalsIgnoreCase(line)) {
					if (!s.getLine(1).isEmpty() && !s.getLine(2).isEmpty()
							&& !s.getLine(3).isEmpty()) {
						boolean derp = false;
						for (String st : skillnamess) {
							if (!s.getLine(1).isEmpty()) {
								if (s.getLine(1).equalsIgnoreCase(st)) {
									derp = true;
								}
							}
						}
						if (derp) {
							if (player.hasPermission("mcutils.buy."
									+ s.getLine(1).toLowerCase())
									|| player.hasPermission("mcutils.buy.*")) {
								double balance = McLevelUp.econ.getBalance(e
										.getPlayer().getName());
								/*
								 * Replace the $ on line 4 if its there so that
								 * vault can charge properly The below parse
								 * apparently works b/c you cant compare $1000
								 * to a players balance Also on line 69, it
								 * charges a player a double with the value of
								 * line 4 replacing $ And that for sure would
								 * throw a NumberFormatException if the
								 * replaceAll didnt work
								 */
								int cost = Integer.parseInt(s.getLine(3)
										.replaceAll("\\$", "0"));
								// Makes sure player has enough money
								if (balance >= cost) {
									e.setCancelled(true);
									plugin.getPlayerProfile(player).addXPOverrideBonus(
													SkillType.valueOf(s
															.getLine(1)
															.toUpperCase()),
													Integer.valueOf(s
															.getLine(2)
															.replace("xp", "")));
									Skills.XpCheckSkill(SkillType.valueOf(s
											.getLine(1)
											.toUpperCase()), player);
									player.sendMessage(ChatColor.GREEN
											+ "Successfully Bought "
											+ s.getLine(2).replace("xp", "")
											+ " of "
											+ s.getLine(1).toLowerCase()
											+ " xp");
									// Does player have bypass perm? If not then
									// charge them the amount on the sign
									if (!player
											.hasPermission("mcutils.bypass.charge")) {
										McLevelUp.econ
												.withdrawPlayer(e.getPlayer()
														.getName(), Double
														.valueOf(s.getLine(3)
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
							} else {
								// Player does have permission to use the sign
								// they are trying to use
								e.setCancelled(true);
								player.sendMessage(ChatColor.RED 
										+ "You dont have permission for "
										+ s.getLine(1).toLowerCase() + " signs");
							}
						} else {
							// An Extra check to make sure if player uses wrong
							// mcmmo skill type name it doesnt try to use it and
							// throw an error.
							e.setCancelled(true);
							player.sendMessage(ChatColor.RED 
									+ "Line 2 contains invalid mcMMO Skillname");
							return;
						}
					}
				}
			}
		}
	}
}
