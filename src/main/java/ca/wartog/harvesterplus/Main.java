package ca.wartog.harvesterplus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ca.wartog.harvesterplus.commands.HarvesterplusCommand;
import ca.wartog.harvesterplus.events.FertilizingEvent;
import ca.wartog.harvesterplus.events.HarvestingEvent;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.manager.ItemManager;

public class Main extends JavaPlugin{
	
	public ItemManager itemManager;
	public static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		itemManager = MMOItems.plugin.getItems();
		
		//Init config
		saveDefaultConfig();
		
		//Init commands
		getCommand("harvesterplus").setExecutor(new HarvesterplusCommand());
		
		//Init listeners
		Bukkit.getPluginManager().registerEvents(new FertilizingEvent(), this);
		Bukkit.getPluginManager().registerEvents(new HarvestingEvent(), this);
	}
	
	public static Main getInstance() {
		return instance;
	}
}
