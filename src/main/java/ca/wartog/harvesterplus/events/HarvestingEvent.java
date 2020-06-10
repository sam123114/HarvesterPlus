package ca.wartog.harvesterplus.events;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import ca.wartog.harvesterplus.Main;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.MMOItem;

public class HarvestingEvent implements Listener{
	
	private Main main = Main.getInstance();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		FileConfiguration config = main.getConfig();
		Block block = e.getBlock();
		Material blockMaterial = block.getBlockData().getMaterial();
		Set<String> keysItemType = config.getConfigurationSection("addon-crops-list").getKeys(false);
		if(keysItemType.contains(blockMaterial.toString())) {
			e.setCancelled(true);
			block.setType(Material.AIR);
			for(String key : config.getConfigurationSection("addon-crops-list." + blockMaterial.toString() + ".drops").getKeys(false)) {
				MMOItem mmoitem = main.itemManager.getMMOItem(MMOItems.plugin.getTypes().get(config.getString("addon-crops-list." + blockMaterial.toString() + ".drops." + key + ".type")), key);
				ItemStack item = mmoitem.newBuilder().build();
				item.setAmount(config.getInt("addon-crops-list." + blockMaterial.toString() + ".drops." + key + ".amount"));
				block.getWorld().dropItemNaturally(block.getLocation(), item);
			}
		}
	}
	
}
