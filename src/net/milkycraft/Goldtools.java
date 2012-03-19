/*
 * This plugin was made by milkywayz
 * Version 2.0 3/19/12
 */
package net.milkycraft;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkycraft.CommandHandlers.MyGoldCommandExecutor;
import net.milkycraft.CommandHandlers.MyGoldyCommandExecutor;
import net.milkycraft.Configuration.Configuration;
import net.milkycraft.Listeners.MyBlockListener;
import net.milkycraft.Listeners.MyGoldListener;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import de.diddiz.LogBlock.Consumer;
import de.diddiz.LogBlock.LogBlock;
/*
 * This is the main class
 * This class handles plugin soft dependencies
 * Handles Registration of listeners and executors
 */
public class Goldtools extends PluginWrapper {
	private Configuration config;
	public static WorldGuardPlugin worldguardPlugin = null;
	public static Consumer lbconsumer = null;
	public static Logger log = Logger.getLogger("Minecraft");
	public static Economy econ = null;
	@Override
	public void onEnable() {
		setupPluginDependencies();
		//variables
		PluginManager pm = getServer().getPluginManager();
		//listeners
		final MyGoldListener goldListener = new MyGoldListener(this);
		final MyBlockListener blockListener = new MyBlockListener();
		pm.registerEvents(goldListener, this);
		pm.registerEvents(blockListener, this);	
		//executors
		MyGoldCommandExecutor myGoldExecutor;
		 myGoldExecutor = new MyGoldCommandExecutor(this);
	    getCommand("goldtools").setExecutor(myGoldExecutor);
	    MyGoldyCommandExecutor myGoldyExecutor;
	    myGoldyExecutor = new MyGoldyCommandExecutor(this);
	    getCommand("gold").setExecutor(myGoldyExecutor);					
		//configuration
		config = new Configuration(this);
		config.create();
		config.reload();		
		}	
	@Override
	public void onDisable() { log.info(getDescription().getName() + " " + getDescription().getVersion() + " unloaded."); }

	private void setupPluginDependencies() {
		try {
			setupWorldGuard();
		} catch (Exception e) {
			log.warning("[Goldtools] Failed to load WorldGuard");
			e.printStackTrace();
		}
		try {
			setupLogBlock();
		} catch (Exception e) {
			log.warning("[Goldtools] Failed to load LogBlock");
			e.printStackTrace();
		}
		try {
			setupEconomy();
		} catch (Exception e) {
			log.warning("[Goldtools] Failed to load Vault");
			e.printStackTrace();
		}
	}
	private void setupLogBlock() {
		// Register logblock plugin so that we can send break event notices to it
		final Plugin logBlockPlugin = getServer().getPluginManager().getPlugin("LogBlock");
		if (logBlockPlugin != null) {
			lbconsumer = ((LogBlock)logBlockPlugin).getConsumer();
	        log.info("[Goldtools] Hooked into LogBlock!");
		} else {
			log.warning("[Goldtools] Failed to load LogBlock");
		}
	}
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
            econ = null;
			log.warning("[Goldtools] Vault not found, economy support disabled");
        } else {
		log.info("[Goldtools] Hooked into Vault!");
        }
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	private void setupWorldGuard() {
		Plugin wg = this.getServer().getPluginManager().getPlugin("WorldGuard");
		if (wg == null) {
			log.info("[Goldtools] Couldn't hook into worldguard");
		} else {
			Goldtools.worldguardPlugin = (WorldGuardPlugin)wg;
			log.info("[Goldtools] Hooked into WorldGuard!");			
		}
	}
	public boolean queueBlockBreak(String playerName, Block block)
	{
		if (block == null) {
			log.warning("Queueblockbreak: block is null - this should not happen!");			
			return false;
		}
		if (lbconsumer != null) {
			BlockState before = block.getState();
			lbconsumer.queueBlockBreak(playerName, before);
		}
		return true;
	}
				public Configuration config(){
					return config;
				}
}
