package ca.wartog.harvesterplus.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FertilizingEvent implements Listener{
	
	private static final int RADIUS = 7;

	@EventHandler
	public void onInteractEvent(PlayerInteractEvent e) {
		Action action = e.getAction();
		ItemStack itemInHand = e.getItem();
		
		if(itemInHand == null) return;
		
		if(action == Action.RIGHT_CLICK_BLOCK && itemInHand.getType().equals(Material.BONE_MEAL)) {
			Block block = e.getClickedBlock();
			Random rnd = new Random();
		    int RADIUS_squared = RADIUS * RADIUS;
		    Block toHandle;
		    for (int x = -RADIUS; x <= RADIUS; x++) {
		    	for (int z = -RADIUS; z <= RADIUS; z++) {
		    		toHandle =  block.getWorld().getHighestBlockAt(block.getX() + x, block.getZ() + z);
		            if (toHandle.getRelative(BlockFace.DOWN).getType() == Material.GRASS) { // Block beneath is grass
		            	if (block.getLocation().distanceSquared(toHandle.getLocation()) <= RADIUS_squared) { // Block is in RADIUS
		                	if (rnd.nextInt(100) < 100) {    // Random chance
		                		toHandle.setType(Material.BRAIN_CORAL);
		                    }
		                }
		            }
		        }
		    }
		}
	}
	
}
