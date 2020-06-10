package ca.wartog.harvesterplus.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import ca.wartog.harvesterplus.Main;

public class FertilizingEvent implements Listener{
	
	private Main main = Main.getInstance();
	
	private static final int RADIUS = 5;

	@EventHandler
	public void onInteractEvent(PlayerInteractEvent e) {
		Action action = e.getAction();
		ItemStack itemInHand = e.getItem();
		if(itemInHand == null) return;
		if(action == Action.RIGHT_CLICK_BLOCK && itemInHand.getType().equals(Material.BONE_MEAL) && e.getClickedBlock().getType().equals(Material.GRASS_BLOCK)) {
			Block block = e.getClickedBlock();
			Random rnd = new Random();
			FileConfiguration config = main.getConfig();
		    int RADIUS_squared = RADIUS * RADIUS;
		    Block toHandle;
		    for (int x = -RADIUS; x <= RADIUS; x++) {
		    	for (int z = -RADIUS; z <= RADIUS; z++) {
		    		toHandle =  block.getWorld().getHighestBlockAt(block.getX() + x, block.getZ() + z);
		            if (toHandle.getType().equals(Material.AIR) && toHandle.getRelative(BlockFace.DOWN).getType() == Material.GRASS_BLOCK) {
		            	if (block.getLocation().distanceSquared(toHandle.getLocation()) <= RADIUS_squared) {
		            		for(String blockType : config.getConfigurationSection("addon-crops-list").getKeys(false)) {
		            			setBlockType(toHandle, blockType, config.getBoolean("addon-crops-list." + blockType + ".is-waterlogged"), config.getInt("addon-crops-list." + blockType + ".spawn-rate"), rnd);
		            		}
		                }
		            }
		        }
		    }
		}
	}
	
	private void setBlockType(Block toHandle, String material, boolean waterlogged, int spawnRate, Random rnd) {
		if(waterlogged) {
			if (rnd.nextInt(100) < spawnRate) {    // Random chance
				toHandle.setType(Material.getMaterial(material));
	    		Waterlogged test = (Waterlogged) toHandle.getBlockData();
	    		test.setWaterlogged(false);
	    		toHandle.setBlockData(test);
	        }
		} else {
			if(rnd.nextInt(100) < spawnRate) {
				toHandle.setType(Material.getMaterial(material));
			}
		}
	}
	
}
