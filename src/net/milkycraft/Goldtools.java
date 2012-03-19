package net.milkycraft;

import java.util.logging.Logger;

import net.milkycraft.CommandHandlers.MyGoldCommandExecutor;
import net.milkycraft.CommandHandlers.MyGoldyCommandExecutor;
import net.milkycraft.Configuration.Configuration;
import net.milkycraft.Listeners.MyBlockListener;
import net.milkycraft.Listeners.MyGoldListener;
import net.milkycraft.Listeners.MyPsListener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Goldtools extends PluginWrapper {
	private Configuration config;
	public Plugin worldguard;
	public static Logger log = Logger.getLogger("Minecraft");
	@Override
	public void onEnable() {
		final MyGoldListener goldListener = new MyGoldListener(this);
		final MyBlockListener blockListener = new MyBlockListener(this);
		final MyPsListener psListener = new MyPsListener(this);
		MyGoldCommandExecutor myGoldExecutor;
		 myGoldExecutor = new MyGoldCommandExecutor(this);
	    getCommand("goldtools").setExecutor(myGoldExecutor);
	    MyGoldyCommandExecutor myGoldyExecutor;
	    myGoldyExecutor = new MyGoldyCommandExecutor(this);
	    getCommand("gold").setExecutor(myGoldyExecutor);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(psListener, this);
		pm.registerEvents(goldListener, this);
		pm.registerEvents(blockListener, this);
		saveConfig();
		config = new Configuration(this);
		config.create();
		config.reload();
		this.getServer().getPluginManager();
		this.getDescription();	
		if (!getWorldGuard()) {
			log.severe("[Goldtools] WorldGuard was not detected!");
			return;
		}
		}	
			@Override
			public void onDisable() {
				{
					log.info("GoldTools disabled");
				}
			}
				public Configuration config(){
					return config;
				}
				private boolean getWorldGuard() {
				    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
				    if(plugin != null) {
				    	System.out.println("[Goldtools] Hooked into Worldguard!");
				    }
				    return (worldguard !=null);
				}
}
