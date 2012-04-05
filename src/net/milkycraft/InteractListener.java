package net.milkycraft;

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
import com.gmail.nossr50.datatypes.SkillType;

public class InteractListener implements Listener{
	McLevelUp plugin;
	public InteractListener(McLevelUp instance) {
		plugin = instance;
	}
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onSignClick(PlayerInteractEvent e ) {
		final Block block = e.getClickedBlock();
		String[] skillnamess = 
			{"ARCHERY", "ACROBATICS", "ALL", "AXES", "EXCAVATION", 
				"FISHING", "HERBALISM", "MINING", "REPAIR", "SWORDS", "TAMING", "UNARMED", "WOODCUTTING"};
		if (block == null)
		{
			return;
		}
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		final int mat = block.getTypeId();
		if (mat == Material.SIGN_POST.getId() || mat == Material.WALL_SIGN.getId())
		{
		BlockState state = e.getClickedBlock().getState();
                final Sign s = (Sign)state;					
                final Player player = e.getPlayer();	               
                if(s.getLine(0).equalsIgnoreCase("[LevelUp]")){         	
					if(!s.getLine(1).isEmpty() && !s.getLine(2).isEmpty() && !s.getLine(3).isEmpty()) {
						boolean derp = false;
						for (String st : skillnamess) {
							if(!s.getLine(1).isEmpty()) {
							if(s.getLine(1).equalsIgnoreCase(st)) {
								derp = true;	
							}
							}
						}
						if(derp) {
						if(player.hasPermission("mclevelup.buy." + s.getLine(0).toLowerCase()) || player.hasPermission("mclevelup.buy.*")){						
							double balance = McLevelUp.econ.getBalance(e.getPlayer().getName());
							int cost = Integer.parseInt(s.getLine(3).replaceAll("\\$", "0"));
							if(balance >= cost) {
							e.setCancelled(true);
							mcMMO.getPlayerProfile(player).addXPOverrideNoBonus(
									SkillType.valueOf(s.getLine(1).toUpperCase()), Integer.valueOf(s.getLine(2).replace("xp", "")));
							mcMMO.checkXp(player, SkillType.valueOf(s.getLine(1).toUpperCase()));
							player.sendMessage(ChatColor.GREEN  + "Successfully Bought " + s.getLine(2).replace("xp", "") + " of " + s.getLine(1).toLowerCase() + " xp");
							if(!player.hasPermission("mclevelup.bypass.charge")) {		
							McLevelUp.econ.withdrawPlayer(e.getPlayer().getName(), Double.valueOf(s.getLine(3).replaceAll("\\$", "0")));
							return;		
							}
							} else {
								player.sendMessage(ChatColor.RED + "Insufficient funds!");
							}
					} else {
						player.sendMessage(ChatColor.RED + "You dont have permission for " + s.getLine(1).toLowerCase() + " signs");
					}
						} else {
							e.setCancelled(true);
							player.sendMessage(ChatColor.RED + "Line 2 contains invalid mcMMO Skillname");
							return;
						}
						}
						}
                }
		}
	}
}
