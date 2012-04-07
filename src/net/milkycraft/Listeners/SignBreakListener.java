package net.milkycraft.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class SignBreakListener implements Listener {

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onSignBreak(BlockBreakEvent e) {
		final int mat = e.getBlock().getTypeId();
		if (mat == Material.SIGN_POST.getId()
				|| mat == Material.WALL_SIGN.getId()) {
			BlockState state = e.getBlock().getState();
			final Sign s = (Sign) state;
			if(s.getLine(0).equalsIgnoreCase("[levelup]") 
				|| s.getLine(0).equalsIgnoreCase("[mcreset]")
					|| s.getLine(0).equalsIgnoreCase("[mcgod]")
					|| s.getLine(0).equalsIgnoreCase("[mccooldown]")) {
					if(!e.getPlayer().hasPermission("mcutils.destroy")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "" +
								"You dont have permission to break " + s.getLine(0) + " signs");					
					} else {
						e.getPlayer().sendMessage(ChatColor.GREEN + "Destroyed a " + s.getLine(1).toLowerCase() + " sign");
					}
					}
				}
	}
}
