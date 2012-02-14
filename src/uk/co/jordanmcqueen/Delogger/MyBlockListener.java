package uk.co.jordanmcqueen.Delogger;
//Jordan McQueen -- Everlong -- www.technical-stop.net
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MyBlockListener implements Listener{
	
	public static Delogger plugin;
	
	public MyBlockListener(Delogger instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onBlockBreak (BlockBreakEvent event) {
		Material block = event.getBlock().getType();
		Player player = event.getPlayer();
		if(block == Material.LOG && player.isOp() && player.getItemInHand().getType().name().toLowerCase().contains("axe")) {
			Location blockLocation = event.getBlock().getLocation();
				double y = blockLocation.getBlockY();
				double x = blockLocation.getBlockX();
				double z = blockLocation.getBlockZ();
				
			World currentWorld = event.getPlayer().getWorld();
			
			boolean logsLeft = true;
			
			while(logsLeft == true) {
				y++; //Increment Y Coordinate 
				Location blockAbove = new Location(currentWorld, x, y, z);
				Material blockAboveType = blockAbove.getBlock().getType();
				Byte blockAboveData = blockAbove.getBlock().getData();
				if(blockAboveType == Material.LOG) {
					ItemStack droppedItem = new ItemStack(blockAboveType, 1, blockAboveData);
					currentWorld.playEffect(blockAbove, Effect.SMOKE, 10);
					blockAbove.getBlock().setType(Material.AIR);
					currentWorld.dropItem(blockAbove, droppedItem);
					logsLeft = true;
				} else {
					logsLeft = false;
				}
			}
		}
	}
}
