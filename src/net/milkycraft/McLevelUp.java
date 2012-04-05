package net.milkycraft;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.nossr50.mcMMO;

public class McLevelUp extends JavaPlugin {
	public static mcMMO mcmmo = null;
	public static Economy econ = null;
	public static Logger log = Logger.getLogger("Minecraft");
	private PlayerListener playerListener = new PlayerListener(this);
	private InteractListener interactListener = new InteractListener(this);
	
	@Override
	public void onEnable() {
		setupPluginDependencies();
		getServer().getPluginManager().registerEvents(playerListener, this);
		getServer().getPluginManager().registerEvents(interactListener, this);
}
@Override
public void onDisable() {
	this.reloadConfig();
	this.saveConfig();
    }

private void setupPluginDependencies() {
	try {
		setupMcMmo();
	} catch (Exception e) {
		log.warning("[McLevelUp] Failed to load McMMO, plugin disabling!");
		e.printStackTrace();
	}
	try {
		setupEconomy();
	} catch (Exception e) {
		log.warning("[McLevelUp] Failed to load Vault, plugin disabling!");
		e.printStackTrace();
	}
}
private boolean setupEconomy() {
	if (getServer().getPluginManager().getPlugin("Vault") == null) {
		econ = null;
		log.warning("[McLevelUp] Vault not found, economy support disabled");
	} else {
		log.info("[McLevelUp] Hooked into Vault!");
	}
	if (getServer().getPluginManager().getPlugin("Vault") == null) {
		return false;
	}
	RegisteredServiceProvider<Economy> rsp = getServer()
			.getServicesManager().getRegistration(Economy.class);
	if (rsp == null) {
		return false;
	}
	econ = rsp.getProvider();
	return econ != null;
}

private void setupMcMmo() {
	Plugin wg = this.getServer().getPluginManager().getPlugin("mcMMO");
	if (wg == null) {
		log.info("[McLevelUp] mcMMO not detected, plugin disabling");
	} else {
		McLevelUp.mcmmo = (mcMMO) mcmmo;
		log.info("[McLevelUp] Hooked into mcMMO!");
	}
}

}