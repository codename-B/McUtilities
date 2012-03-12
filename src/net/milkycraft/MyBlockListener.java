package net.milkycraft;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MyBlockListener implements Listener {
	public void onBlockBreak(BlockBreakEvent ev) {
		Block block = ev.getBlock();
		World world = block.getWorld();
		Location loc = block.getLocation();
		BlockState bs = block.getState();
		ItemStack goldpick = new ItemStack(285);
		if (ev.getPlayer().getItemInHand() == goldpick) {
			if (ev.getPlayer().hasPermission("goldtools.drop")) {
				if (block.getType() == Material.IRON_ORE) {
					world.dropItemNaturally(loc, new ItemStack(bs.getType(), 1,
							bs.getRawData(), bs.getRawData()));
					return;
				}
				if (block.getType() == Material.GOLD_ORE) {
					world.dropItemNaturally(loc, new ItemStack(bs.getType(), 1,
							bs.getRawData(), bs.getRawData()));
					return;
				}
				if (block.getType() == Material.LAPIS_ORE) {
					world.dropItemNaturally(loc, new ItemStack(bs.getType(), 1,
							bs.getRawData(), bs.getRawData()));
					return;
				}
				if (block.getType() == Material.REDSTONE_ORE) {
					world.dropItemNaturally(loc, new ItemStack(bs.getType(), 1,
							bs.getRawData(), bs.getRawData()));
					return;
				}
				if (block.getType() == Material.GOLD_BLOCK) {
					world.dropItemNaturally(loc, new ItemStack(bs.getType(), 1,
							bs.getRawData(), bs.getRawData()));
					return;
				}
				if (block.getType() == Material.IRON_BLOCK) {
					world.dropItemNaturally(loc, new ItemStack(bs.getType(), 1,
							bs.getRawData(), bs.getRawData()));
					return;
				}
				if (block.getType() == Material.LAPIS_BLOCK) {
					world.dropItemNaturally(loc, new ItemStack(bs.getType(), 1,
							bs.getRawData(), bs.getRawData()));
					return;
				}
			}
		}
	}
}