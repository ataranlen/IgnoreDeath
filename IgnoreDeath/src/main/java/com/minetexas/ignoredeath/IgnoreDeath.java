package com.minetexas.ignoredeath;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.minetexas.util.MTLog;
import com.minetexas.util.MTSettings;

public class IgnoreDeath extends JavaPlugin {
	@Override
	public void onEnable() {
		MTLog.init(this);
		MTLog.info("Enabled");
		final PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new DeathListener(), this);

		MTSettings.init(this);

	}
	@Override
	public void onDisable() {
		MTLog.info("Disable");
	}

	
	public boolean hasPlugin(String name) {
		Plugin p;
		p = getServer().getPluginManager().getPlugin(name);
		return (p != null);
	}
	
}
