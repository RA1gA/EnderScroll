package jp.mc.ra1ga.enderscroll.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import jp.mc.ra1ga.enderscroll.main.EnderScroll;

public class EnderScrollCommand implements CommandExecutor {
	public EnderScrollCommand(EnderScroll plugin) {
		this.plugin = plugin;
	}

	private EnderScroll plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(args.length > 0){

			String command = args[0];

			if(command.equalsIgnoreCase("set")){
				if(hasPermission(sender, command)){
					if(args.length == 2){
						if(sender instanceof Player){
							Player p = (Player) sender;
							String locName = args[1];
							setConfig(locName, p.getWorld().getName(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
							sender.sendMessage(ChatColor.GREEN + locName + "を設定しました");
						}else{
							sender.sendMessage(ChatColor.RED + "このコマンドはプレイヤー用です");
						}
					}else if(args.length == 6){
						try{
							String locName = args[1];
							String world = args[2];
							double x = Double.parseDouble(args[3]);
							double y = Double.parseDouble(args[4]);
							double z = Double.parseDouble(args[5]);
							setConfig(locName, world, x, y, z, 0, 0);
							sender.sendMessage(ChatColor.GREEN + locName + "を設定しました");
						}catch (NumberFormatException e){
							sender.sendMessage(ChatColor.RED + "数値が不正です");
						}
					}else if(args.length == 8){
						try{
							String locName = args[1];
							String world = args[2];
							double x = Double.parseDouble(args[3]);
							double y = Double.parseDouble(args[4]);
							double z = Double.parseDouble(args[5]);
							float yaw = Float.parseFloat(args[6]);
							float pitch = Float.parseFloat(args[7]);
							setConfig(locName, world, x, y, z, yaw, pitch);
							sender.sendMessage(ChatColor.GREEN + locName + "を設定しました");
						}catch (NumberFormatException e){
							sender.sendMessage(ChatColor.RED + "数値が不正です");
						}
					}else{
						sender.sendMessage(ChatColor.YELLOW + "/enderscroll set <LocationName>");
						sender.sendMessage(ChatColor.YELLOW + "<LocationName>で現在の座標と向きを保存する");
						sender.sendMessage(ChatColor.YELLOW + "/enderscroll set <LocationName> world x y z");
						sender.sendMessage(ChatColor.YELLOW + "<LocationName>で指定の座標を保存する");
						sender.sendMessage(ChatColor.YELLOW + "/enderscroll set <LocationName> world x y z yaw pitch");
						sender.sendMessage(ChatColor.YELLOW + "<LocationName>で指定の座標と向き保存する");
					}
				}
				return true;
			}else if(command.equalsIgnoreCase("remove")){
				if(hasPermission(sender, command)){
					if(args.length == 2){
						String locName = args[1];
						if(plugin.getConfig().contains(locName)){
							plugin.getConfig().set(locName, null);
							plugin.saveConfig();
							sender.sendMessage(ChatColor.GREEN + locName + "を削除しました");
						}else{
							sender.sendMessage(ChatColor.RED + "LocationNameが正しくありません");
						}
					}else{
						sender.sendMessage(ChatColor.YELLOW + "/enderscroll remove <LocationName>");
						sender.sendMessage(ChatColor.YELLOW + "保存されている<LocationName>を削除する");
					}
				}
				return true;
			}else if(command.equalsIgnoreCase("give")){
				if(hasPermission(sender, command)){
					if(args.length == 4){
						try{
							Player target = Bukkit.getPlayer(args[1]);
							if(target != null){
								String locName = args[2];
								int amount = Integer.parseInt(args[3]);
								if (plugin.getConfig().contains(locName + ".World") &&
										plugin.getConfig().contains(locName + ".X") &&
										plugin.getConfig().contains(locName + ".Y") &&
										plugin.getConfig().contains(locName + ".Z") &&
										plugin.getConfig().contains(locName + ".Yaw") &&
										plugin.getConfig().contains(locName + ".Pitch")){
									ItemStack scroll = new ItemStack(Material.PAPER, amount);
									ItemMeta meta = scroll.getItemMeta();
									if(plugin.getConfig().contains("ScrollName")){
										String display = plugin.getConfig().getString("ScrollName");
										display = display.replaceAll("&", "§");
										display = display.replaceAll("%name%", locName);
										meta.setDisplayName(display);
									}else{
										meta.setDisplayName(ChatColor.DARK_PURPLE + "EnderScroll [" + locName + "]");
									}
									scroll.setItemMeta(meta);
									target.getInventory().addItem(plugin.getPacketUtil().setScrollTag(scroll, locName));
									sender.sendMessage(ChatColor.GREEN + locName + "のEnderScrollを生成しました");
								}else{
									sender.sendMessage(ChatColor.RED + "LocationNameが正しくないか全ての数値が正常に設定されていません");
								}
							}else{
								sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません");
							}
						}catch (NumberFormatException e){
							sender.sendMessage(ChatColor.RED + "数値が不正です");
						}
					}else{
						sender.sendMessage(ChatColor.YELLOW + "/enderscroll give <PlayerName> <LocationName> <Amount>");
						sender.sendMessage(ChatColor.YELLOW + "<LocationName>に行けるEnderScrollをプレイヤーに付与する");
					}
				}
				return true;
			}else if(command.equalsIgnoreCase("list")){
				if(hasPermission(sender, command)){
					sender.sendMessage(ChatColor.GREEN + this.plugin.getConfig().getKeys(false).toString());
				}
				return true;
			}else if(command.equalsIgnoreCase("reload")){
				if(hasPermission(sender, command)){
					plugin.reloadConfig();
				}
				sender.sendMessage(ChatColor.GREEN + "コンフィグをリロードしました");
				return true;
			}else if(command.equalsIgnoreCase("help")){
				if(hasPermission(sender, command)){
					sender.sendMessage(ChatColor.YELLOW + "/enderscroll set <LocationName>");
					sender.sendMessage(ChatColor.YELLOW + "<LocationName>で現在の座標と向きを保存する");
					sender.sendMessage(ChatColor.YELLOW + "/enderscroll set <LocationName> world x y z");
					sender.sendMessage(ChatColor.YELLOW + "<LocationName>で指定の座標を保存する");
					sender.sendMessage(ChatColor.YELLOW + "/enderscroll set <LocationName> world x y z yaw pitch");
					sender.sendMessage(ChatColor.YELLOW + "<LocationName>で指定の座標と向き保存する");
					sender.sendMessage(ChatColor.YELLOW + "/enderscroll remove <LocationName>");
					sender.sendMessage(ChatColor.YELLOW + "保存されている<LocationName>を削除する");
					sender.sendMessage(ChatColor.YELLOW + "/enderscroll give <PlayerName> <LocationName> <Amount>");
					sender.sendMessage(ChatColor.YELLOW + "<LocationName>に行けるEnderScrollをプレイヤーに付与する");
					sender.sendMessage(ChatColor.YELLOW + "/enderscroll list");
					sender.sendMessage(ChatColor.YELLOW + "現在存在するすべての<LocationName>を表示する");
				}
				return true;
			}
		}

		return false;
	}

	private boolean hasPermission(CommandSender sender, String command){
		String permissionNode = "enderscroll.command." + command.toLowerCase();
		if(!sender.hasPermission(permissionNode)){
			sender.sendMessage(ChatColor.RED + "You dont have permission : " + permissionNode);
			return false;
		}
		return true;
	}

	private void setConfig(String locName, String world, double x, double y, double z, float yaw, float pitch){
		plugin.reloadConfig();
		plugin.getConfig().set(locName + ".World", world);
		plugin.getConfig().set(locName + ".X", x);
		plugin.getConfig().set(locName + ".Y", y);
		plugin.getConfig().set(locName + ".Z", z);
		plugin.getConfig().set(locName + ".Yaw", yaw);
		plugin.getConfig().set(locName + ".Pitch", pitch);
		plugin.saveConfig();
	}

}
