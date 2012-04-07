package net.milkycraft;

import net.milkycraft.Configpack.MCConfiguration;


public class Wrapper extends org.bukkit.plugin.java.JavaPlugin {
    private MCConfiguration config;
    
    @Override
	public MCConfiguration getConfig() {
        if(config == null) {
            config = new MCConfiguration(this);
        }
        return config;
    }
    @Override
	public void reloadConfig() {
        getConfig().load();
    }
    @Override
	public void saveConfig() {
        getConfig().save();
    }
    @Override
	public void saveDefaultConfig() {
        getConfig().saveDefaults();
    }
}