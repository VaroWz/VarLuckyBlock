package fr.varowz.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.varowz.main.commands.CommandLB;
import fr.varowz.main.listeners.LuckyBlock;

public class VarLuckyBlock extends JavaPlugin{
	
	public String prefix = "§f[§eVarLuckyBlock§f] ";
	public static VarLuckyBlock instance;
	
	
	@Override
	public void onEnable() {
		
		saveDefaultConfig(); 
		
		getCommand("luckyblock").setExecutor(new CommandLB(this));
		getServer().getPluginManager().registerEvents(new LuckyBlock(this), this);
		
		Bukkit.getConsoleSender().sendMessage(prefix + "§aPlugin §7[§e1.0.0§7] §ais online.");
		Bukkit.getConsoleSender().sendMessage(prefix + "§aPlugin §7[§e1.0.0§7] §cDev by VaroWz.");
		Bukkit.getConsoleSender().sendMessage(prefix + "§aPlugin §7[§e1.0.0§7] §9Discord: https://discord.gg/SbKrKehCpq.");
	}
	
	
	@Override
	public void onDisable() {
		
		Bukkit.getConsoleSender().sendMessage(prefix + "§cPlugin §7[§e1.0.0§7] §cis offline.");
		
	}
	
	public static VarLuckyBlock getInstance() {
		return instance;
	}
	

}
