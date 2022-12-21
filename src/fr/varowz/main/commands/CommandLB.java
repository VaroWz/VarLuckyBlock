package fr.varowz.main.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.varowz.main.VarLuckyBlock;

public class CommandLB implements CommandExecutor {
	
	VarLuckyBlock main;
	
	public CommandLB(VarLuckyBlock luckyblock) {
		this.main =  luckyblock;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("luckyblock")) {
			
			if(sender.hasPermission("varluckyblock.command")) {
				
				
				if(args.length == 1) {
					String arg = args[0];
					if(arg.equalsIgnoreCase("reload") || arg.equalsIgnoreCase("rl")) {
						
						main.reloadConfig();
						main.prefix = main.getConfig().getString("Lang.Prefix").replace("&", "§");
						sender.sendMessage(main.prefix + "§aConfig reloaded !");
						
					}
					else {
						CommandHelp(sender);
					}
					
				}
				else if(args.length == 2) {
					String arg = args[0];
					String arg2 = args[1];
					
					if(arg.equalsIgnoreCase("give")) {
						
						if(Bukkit.getPlayer(arg2) != null) {
						
							Player player = Bukkit.getPlayer(arg2);
							
							player.getInventory().addItem(getLuckyBlock());
							player.sendMessage(main.prefix+ main.getConfig().getString("Lang.ReceiveLuckyBlock")
									.replace("&", "§")
									.replace("%givenumber%", 1+""));
							sender.sendMessage(main.prefix + "§aYou have give §e" + 1 + " §aluckyblock to §e" + player.getName());
							
						}
						else {
							sender.sendMessage(main.getConfig().getString("Lang.NoPlayer").replace("&", "§"));
						}
					}
					else if(arg.equalsIgnoreCase("giveall")) {
						
						for(Player player : Bukkit.getServer().getOnlinePlayers()) {
							
							int n = Integer.valueOf(arg2);
							
							for(int i =0; i<n; i++) {
								player.getInventory().addItem(getLuckyBlock());
							}
							player.sendMessage(main.prefix+ main.getConfig().getString("Lang.ReceiveByGiveall")
									.replace("&", "§")
									.replace("%givenumber%", n+""));
							sender.sendMessage(main.prefix + "§aYou have give §e" + n + " §aluckyblocks to §eall");
							
						}
						
					}
					else {
						CommandHelp(sender);
					}
				}
				else if(args.length == 3) {
					String arg = args[0];
					String arg2 = args[1];
					String arg3 = args[2];
					
					if(arg.equalsIgnoreCase("give")) {
						
						if(Bukkit.getPlayer(arg2) != null) {
						
							Player player = Bukkit.getPlayer(arg2);
							
							if(Integer.valueOf(arg3) instanceof Integer) {
								int n = Integer.valueOf(arg3);
								
								for(int i =0; i<n; i++) {
									player.getInventory().addItem(getLuckyBlock());
								}
								player.sendMessage(main.prefix+ main.getConfig().getString("Lang.ReceiveLuckyBlock")
										.replace("&", "§")
										.replace("%givenumber%", n+""));
								sender.sendMessage(main.prefix + "§aYou have give §e" + n + " §aluckyblocks to §e"+player.getName());
							}
						}
						else {
							sender.sendMessage(main.getConfig().getString("Lang.NoPlayer").replace("&", "§"));
						}
					}
					else {
						CommandHelp(sender);
					}
				}
				else {
					CommandHelp(sender);
				}
			}
			else {
				sender.sendMessage(main.getConfig().getString("Lang.NoPerm").replace("&", "§"));
			}
		}
		return false;
	}
	public void CommandHelp (CommandSender sender) {
		sender.sendMessage("§8§m--------------------------------");
		sender.sendMessage("§6/luckyblock §esee all commands.");
		sender.sendMessage("§6/luckyblock give <player> [number] §eGive luckyblocks to a player.");
		sender.sendMessage("§6/luckyblock giveall <number> §eGive luckyblocks to all player.");
		sender.sendMessage("§6/luckyblock reload §eReload config.");
		sender.sendMessage("§8§m--------------------------------");
	}
	
	public ItemStack getLuckyBlock() {
		
		
		ItemStack luckyblock = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		SkullMeta meta = (SkullMeta) luckyblock.getItemMeta();
		meta.setDisplayName(main.getConfig().getString("Luckyblock.Name").replace("&", "§"));
		List<String> lore = main.getConfig().getStringList("Luckyblock.Lore");
		List<String> loree = new ArrayList<String>();
		for(int i = 0; i < lore.size(); i++) {
			String line = lore.get(i).replace("&", "§");
			
			loree.add(line);
		}
		meta.setLore(loree);
		meta.setOwner("luck");
		luckyblock.setItemMeta(meta);
		
		return luckyblock;
	}

}
