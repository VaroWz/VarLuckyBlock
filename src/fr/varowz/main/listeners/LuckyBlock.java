package fr.varowz.main.listeners;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import fr.varowz.main.VarLuckyBlock;

public class LuckyBlock implements Listener {

	VarLuckyBlock main;
	
	public LuckyBlock(VarLuckyBlock varLuckyBlock) {
		this.main = varLuckyBlock;
	}
	public HashMap<Integer, ItemStack> event = new HashMap<>();
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		Block blockbreak = event.getBlock();
		Location blockloc = blockbreak.getLocation();
		BlockState bs = blockbreak.getState();
		
		if(bs instanceof Skull) {
			Skull skull = (Skull) bs;
			if(skull.getOwner().equalsIgnoreCase("luck")) {
				event.setCancelled(true);
				blockbreak.setType(Material.AIR);
				for(int i = 0; i<=main.getConfig().getInt("Luckyblock.ParticleQuantity"); i++) {
					blockbreak.getWorld().playEffect(blockloc, Effect.EXPLOSION, 1);
				}
				
				Random random = new Random();
				int amount = 0;
				for(String string: main.getConfig().getConfigurationSection("Events").getKeys(false)) {
					
					ItemStack Item = getItem(
							Material.valueOf(main.getConfig().getConfigurationSection("Events").getString(string + ".Material")), 
							main.getConfig().getConfigurationSection("Events").getInt(string + ".Quantity"), 
							main.getConfig().getConfigurationSection("Events").getInt(string + ".Data")
							);
					this.event.put(amount, Item);
					amount++;
					
				}
				int alea = random.nextInt(amount);
				
				player.getWorld().dropItem(blockloc, this.event.get(alea));
			}
		}
	}
	public static ItemStack getItem(Material material, int number, int data) {
		ItemStack it = new ItemStack(material, number, (byte)data);
		return it;
	}
}
