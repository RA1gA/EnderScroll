package jp.mc.ra1ga.enderscroll.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.json.ScrollLocation;
import jp.mc.ra1ga.enderscroll.json.ScrollLocationManager;
import jp.mc.ra1ga.mycore.command.SubCommandable;

public class EnderScrollShortSetCommand implements SubCommandable {

	@Override
	public boolean run(CommandSender sender, String[] args, JavaPlugin plugin) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			Location loc = p.getLocation();
			String locName = args[1];
			ScrollLocation sloc = new ScrollLocation(locName, p.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
			ScrollLocationManager.getInstance().register(sloc);
			sender.sendMessage(ChatColor.GREEN + locName + "を設定しました");
			return true;
		}else{
			sender.sendMessage(ChatColor.RED + "このコマンドはプレイヤー用です");
			return false;
		}
	}

	@Override
	public String getCommand() {
		return "set";
	}

	@Override
	public String getPermission() {
		return "enderscroll.command.set";
	}

	@Override
	public List<String> getHelp() {
		return Arrays.asList(
				ChatColor.YELLOW + "/enderscroll set <LocationName>",
				ChatColor.YELLOW + "<LocationName>で現在の座標と向きを保存する"
		);
	}

	@Override
	public int getLength() {
		return 2;
	}

}
