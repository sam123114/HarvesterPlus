package ca.wartog.harvesterplus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ca.wartog.harvesterplus.commands.HarvesterplusCommand;
import ca.wartog.harvesterplus.events.FertilizingEvent;

public class Main extends JavaPlugin{
	
	public static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		//Init config
		saveDefaultConfig();
		
		//Init commands
		getCommand("harvesterplus").setExecutor(new HarvesterplusCommand());
		
		//Init listeners
		Bukkit.getPluginManager().registerEvents(new FertilizingEvent(), this);
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		instance = null;
		//saveConfig();
	}

	
	public static Main getInstance() {
		return instance;
	}
}
