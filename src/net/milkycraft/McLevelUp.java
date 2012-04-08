package net.milkycraft;

import java.io.IOException;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkycraft.Configpack.MConfiguration;
import net.milkycraft.Listeners.CCooldownListener;
import net.milkycraft.Listeners.ClearSignListener;
import net.milkycraft.Listeners.GodSignListener;
import net.milkycraft.Listeners.SignBreakListener;
import net.milkycraft.Listeners.XpSignListener;
import net.milkycraft.metrics.Metrics;
import net.milkycraft.signs.ClearSign;
import net.milkycraft.signs.CoolDownSign;
import net.milkycraft.signs.GodSign;
import net.milkycraft.signs.XpSign;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import com.gmail.nossr50.mcMMO;

public class McLevelUp extends Wrapper {
	private MConfiguration config;
	public static mcMMO mcmmo = null;
	public static Economy econ = null;
	public static Logger log = Logger.getLogger("Minecraft");
	private XpSign xpListener = new XpSign(this);
	private GodSignListener godsignListener = new GodSignListener(this);
	private GodSign godListener = new GodSign(this);
	private CoolDownSign coolListener = new CoolDownSign(this);
	private ClearSign clearListener = new ClearSign(this);
	private XpSignListener interactListener = new XpSignListener(this);
	private ClearSignListener clearsignListener = new ClearSignListener(this);
	private CCooldownListener cleardownListener = new CCooldownListener(this);
	private SignBreakListener signListener = new SignBreakListener();
	
	@Override
	public void onEnable() {	   
		setupPluginDependencies();
		getServer().getPluginManager().registerEvents(xpListener, this);
		getServer().getPluginManager().registerEvents(godsignListener, this);
		getServer().getPluginManager().registerEvents(godListener, this);
		getServer().getPluginManager().registerEvents(signListener, this);
		getServer().getPluginManager().registerEvents(coolListener, this);
		getServer().getPluginManager().registerEvents(clearListener, this);
		getServer().getPluginManager().registerEvents(clearsignListener, this);
		getServer().getPluginManager().registerEvents(cleardownListener, this);
		getServer().getPluginManager().registerEvents(interactListener, this);
		Metrics metrics;
		try {
			metrics = new Metrics(this);
			metrics.beginMeasuringPlugin(this);
		} catch (IOException e) {
			writeLog(e.getMessage());
		}		
		writeLog("[MCU]Metrics loaded!");
}
@Override
public void onDisable() {
	this.reloadConfig();
	this.saveConfig();
    }
// Hooking and defining mcmmo and vault
private void setupPluginDependencies() {
	try {
		setupMcMmo();
	} catch (Exception e) {
		log.warning("[McUtilities] Failed to load McMMO, plugin disabling!");
		e.printStackTrace();
	}
	try {
		setupEconomy();
	} catch (Exception e) {
		log.warning("[McUtilities] Failed to load Vault, plugin disabling!");
		e.printStackTrace();
	}
}
private boolean setupEconomy() {
	if (getServer().getPluginManager().getPlugin("Vault") == null) {
		econ = null;
		log.warning("[McUtilities] Vault not found, economy support disabled");
	} else {
		log.info("[McUtilities] Hooked into Vault!");
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
		log.info("[McUtilities] mcMMO not detected, plugin disabling");
	} else {
		McLevelUp.mcmmo = (mcMMO) mcmmo;
		log.info("[McUtilities] Hooked into mcMMO!");
	}
}
public MConfiguration config() {
	return config;
}
public void writeLog(String text) {
    McLevelUp.log.info(text);
}
}