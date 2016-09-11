package com.minetexas.ignoredeath;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.dthielke.herochat.Chatter;
import com.dthielke.herochat.ChatterManager;
import com.dthielke.herochat.Herochat;
import com.minetexas.util.MTSettings;
import com.minetexas.util.MTLog;

public class DeathListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent e){
		String message = e.getDeathMessage();
		MTLog.info(message);
		e.setDeathMessage("");
		Player sender = e.getEntity().getPlayer();
		Boolean useHerochat = false;
		Collection <? extends Player> players = Bukkit.getOnlinePlayers();//.forEach(p -> p.sendMessage(GTColor.LightGreen+meme));

		if (MTSettings.hasHerochat == true) {
			Herochat hc = Herochat.getPlugin();
			useHerochat = hc.isEnabled();
		}
		for(Player p : players) {
			if (useHerochat) {
				ChatterManager cm = Herochat.getChatterManager();
				Player player=(Player) sender;
				Chatter chatter = cm.getChatter(player);
				Chatter chattee = cm.getChatter(p);
				if (chattee.isIgnoring(chatter)) {
					continue;
				}
			}
			p.sendMessage(message);
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent e){
		String message = e.getJoinMessage();
		if (message == null) {
			MTLog.debug("Message is null");
			return;
		}
		MTLog.info(message);
		e.setJoinMessage("");
		Player sender = e.getPlayer();
		if (sender == null) {
			MTLog.debug("sender is null");
			return;
		}
		Boolean useHerochat = false;
		Collection <? extends Player> players = Bukkit.getOnlinePlayers();//.forEach(p -> p.sendMessage(GTColor.LightGreen+meme));

		if (MTSettings.hasHerochat == true) {
			Herochat hc = Herochat.getPlugin();
			useHerochat = hc.isEnabled();
			
		}
		Chatter chatter = null;
		ChatterManager cm = null;
		if (useHerochat) {
		cm = Herochat.getChatterManager();
		chatter = cm.addChatter(sender);
		}

		for(Player p : players) {
			if (p == null) {
				MTLog.debug("p is null");
				continue;
			}

			if (useHerochat) {
				Chatter chattee = cm.getChatter(p);
				if (chatter == null) {
					MTLog.debug("chatter is null");
					return;
				}
				if (chattee == null) {
					MTLog.debug("chattee is null");
					return;
				}
				if (chattee.isIgnoring(chatter)) {
					continue;
				}
			}

			p.sendMessage(message);

		}
		if (useHerochat) {
			
			cm.removeChatter(chatter);
		}

		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e){
		String message = e.getQuitMessage();
		MTLog.info(message);
		e.setQuitMessage("");
		Player sender = e.getPlayer();
		Boolean useHerochat = false;
		Collection <? extends Player> players = Bukkit.getOnlinePlayers();//.forEach(p -> p.sendMessage(GTColor.LightGreen+meme));

		if (MTSettings.hasHerochat == true) {
			Herochat hc = Herochat.getPlugin();
			useHerochat = hc.isEnabled();
		}
		for(Player p : players) {
			if (p == null) {
				continue;
			}
			if (useHerochat) {
				ChatterManager cm = Herochat.getChatterManager();
				Player player=(Player) sender;
				Chatter chatter = cm.getChatter(player);
				Chatter chattee = cm.getChatter(p);
				if (chattee.isIgnoring(chatter)) {
					continue;
				}
			}
			p.sendMessage(message);
		}
	}
	
}
