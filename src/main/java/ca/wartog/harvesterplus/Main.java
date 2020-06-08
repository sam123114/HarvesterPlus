package ca.wartog.harvesterplus;

import org.bukkit.plugin.java.JavaPlugin;

import ca.wartog.harvesterplus.commands.HarvesterplusCommand;

public class Main extends JavaPlugin{
	
	public static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		//Init config
		saveDefaultConfig();
		
		//Init commands
		getCommand("harvesterplus").setExecutor(new HarvesterplusCommand());
	}

	
	public static Main getInstance() {
		return instance;
	}
}
