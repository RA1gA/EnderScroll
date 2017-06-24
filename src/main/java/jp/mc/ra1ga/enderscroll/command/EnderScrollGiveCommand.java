package jp.mc.ra1ga.enderscroll.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.json.ScrollLocationManager;
import jp.mc.ra1ga.enderscroll.main.EnderScroll;
import jp.mc.ra1ga.mycore.command.SubCommandable;

public class EnderScrollGiveCommand implements SubCommandable {

	@Override
	public boolean run(CommandSender sender, String[] args, JavaPlugin plugin) {
		try{
			Player target = Bukkit.getPlayer(args[1]);
			if(target != null){
				String locName = args[2];
				int amount = Integer.parseInt(args[3]);
				if (ScrollLocationManager.getInstance().getScrollLocationFrom(locName) != null){
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
					target.getInventory().addItem(((EnderScroll)plugin).getPacketUtil().setScrollTag(scroll, locName));
					sender.sendMessage(ChatColor.GREEN + locName + "のEnderScrollを生成しました");
					return true;
				}else{
					sender.sendMessage(ChatColor.RED + "LocationNameが正しくないか全ての数値が正常に設定されていません");
					return false;
				}
			}else{
				sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません");
				return false;
			}
		}catch (NumberFormatException e){
			sender.sendMessage(ChatColor.RED + "数値が不正です");
			return false;
		}
	}

	@Override
	public String getCommand() {
		return "give";
	}

	@Override
	public String getPermission() {
		return "enderscroll.command.give";
	}

	@Override
	public List<String> getHelp() {
		return Arrays.asList(
				ChatColor.YELLOW + "/enderscroll give <PlayerName> <LocationName> <Amount>",
				ChatColor.YELLOW + "<LocationName>に行けるEnderScrollをプレイヤーに付与する"
		);
	}

	@Override
	public int getLength() {
		return 4;
	}

}
