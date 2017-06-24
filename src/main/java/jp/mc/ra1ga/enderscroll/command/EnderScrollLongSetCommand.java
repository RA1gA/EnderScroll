package jp.mc.ra1ga.enderscroll.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.json.ScrollLocation;
import jp.mc.ra1ga.enderscroll.json.ScrollLocationManager;
import jp.mc.ra1ga.mycore.command.SubCommandable;

public class EnderScrollLongSetCommand implements SubCommandable {

	@Override
	public boolean run(CommandSender sender, String[] args, JavaPlugin plugin) {
		try{
			String locName = args[1];
			String world = args[2];
			double x = Double.parseDouble(args[3]);
			double y = Double.parseDouble(args[4]);
			double z = Double.parseDouble(args[5]);
			float yaw = Float.parseFloat(args[6]);
			float pitch = Float.parseFloat(args[7]);
			ScrollLocation sloc = new ScrollLocation(locName, world, x, y, z, yaw, pitch);
			ScrollLocationManager.getInstance().register(sloc);
			sender.sendMessage(ChatColor.GREEN + locName + "を設定しました");
			return true;
		}catch (NumberFormatException e){
			sender.sendMessage(ChatColor.RED + "数値が不正です");
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
				ChatColor.YELLOW + "/enderscroll set <LocationName> world x y z yaw pitch",
				ChatColor.YELLOW + "<LocationName>で指定の座標と向き保存する"
		);
	}

	@Override
	public int getLength() {
		return 8;
	}

}
