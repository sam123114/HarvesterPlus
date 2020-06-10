package ca.wartog.harvesterplus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.wartog.harvesterplus.Main;

public class HarvesterplusCommand implements CommandExecutor{
	
	private Main main = Main.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		
		if(args.length == 0) {
			displayMenu(p);
			return true;
		}
		else {
			if(args[0].equals("reload") && args.length == 1) {
				main.reloadConfig();
				p.sendMessage("§aConfig reloaded");
			}
			else {
				displayMenu(p);
				return false;
			}
		}
		
		return true;
	}
	
	private void displayMenu(Player p) {
		p.sendMessage("§8-=-=-=-=-[§aHarvesterPlus§8]-=-=-=-=-");
		p.sendMessage("");
		p.sendMessage("§e /hp §f- §7Command list");
		p.sendMessage("§e /hp reload §f- §7Reload the config file");
		p.sendMessage("");
		p.sendMessage("§8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}

}
