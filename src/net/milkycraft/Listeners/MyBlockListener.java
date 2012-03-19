package net.milkycraft.Listeners;

import net.milkycraft.Goldtools;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
/*
 * This class does the following:
 * - Drop a block when its "broken" by a instantmine
 * - Allow for blocking drops for players without perms
 */
public class MyBlockListener implements Listener {
	Goldtools plugin;
	public enum Material {
		IRON_ORE, GOLD_ORE, LAPIS_ORE, REDSTONE_ORE, GOLD_BLOCK, IRON_BLOCK, LAPIS_BLOCK
	};
	public class MatUse {
		Material mat;
		public MatUse(Material mat) {
			this.mat = mat;
		}
		public MatUse(Goldtools instance) {
			plugin = instance;
		}
		@EventHandler(priority = EventPriority.HIGH)
		public void onBlockBreak(BlockBreakEvent ev) {
			Block block = ev.getBlock();
			Location loc = block.getLocation();
			BlockState bs = block.getState();
			World world = block.getWorld();
			ItemStack goldpick = new ItemStack(285);
			if (ev.getPlayer().getItemInHand() == goldpick) {
				if (ev.getPlayer().hasPermission("goldtools.drop")) {
					switch (mat) {
					case IRON_ORE:
						drop(world, loc, bs);
						break;
					case GOLD_ORE:
						drop(world, loc, bs);
						break;
					case LAPIS_ORE:
						drop(world, loc, bs);
						break;
					case REDSTONE_ORE:
						drop(world, loc, bs);
						break;
					case GOLD_BLOCK:
						drop(world, loc, bs);
						break;
					case IRON_BLOCK:
						drop(world, loc, bs);
						break;
					case LAPIS_BLOCK:
						drop(world, loc, bs);
						break;
					}
				}
			}
		}
	}
	public void drop(World world, Location loc, BlockState bs) {
		world.dropItemNaturally(loc,
				new ItemStack(bs.getType(), 1, bs.getRawData(),
						bs.getRawData()));
	}
}
