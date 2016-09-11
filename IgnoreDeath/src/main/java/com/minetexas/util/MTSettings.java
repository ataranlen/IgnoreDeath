package com.minetexas.util;

import com.minetexas.ignoredeath.IgnoreDeath;

public class MTSettings {

	public static IgnoreDeath plugin;
	public static Boolean hasHerochat = false;
	
	public static void init(IgnoreDeath plugin) {
		MTSettings.plugin = plugin;
		MTSettings.hasHerochat = plugin.hasPlugin("Herochat");
	}
	
	
}
