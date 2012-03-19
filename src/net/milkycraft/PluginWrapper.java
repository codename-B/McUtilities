package net.milkycraft;

import net.milkycraft.Configuration.GoldConfiguration;

public class PluginWrapper extends org.bukkit.plugin.java.JavaPlugin {
    private GoldConfiguration config;

    public GoldConfiguration getConfig() {
        if(config == null) {
        	System.err.println("[GoldTools] Config not found, generating a new one");
            config = new GoldConfiguration(this);
        }
        return config;
    }
    public void reloadConfig() {
        getConfig().load();
    }
    public void saveConfig() {
        getConfig().save();
    }
    public void saveDefaultConfig() {
        getConfig().saveDefaults();
    }
}