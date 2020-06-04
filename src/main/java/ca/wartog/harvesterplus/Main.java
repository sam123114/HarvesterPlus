package ca.wartog.harvesterplus;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
	}

	
	public static Main getInstance() {
		return instance;
	}
}
