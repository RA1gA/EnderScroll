package jp.mc.ra1ga.enderscroll.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.json.ScrollLocation;
import jp.mc.ra1ga.enderscroll.json.ScrollLocationManager;
import jp.mc.ra1ga.mycore.command.SubCommandable;

public class EnderScrollRemoveCommand implements SubCommandable {

	@Override
	public boolean run(CommandSender sender, String[] args, JavaPlugin plugin) {
		String locName = args[1];

		ScrollLocation sloc;
		if((sloc = ScrollLocationManager.getInstance().getScrollLocationFrom(locName)) != null){
			ScrollLocationManager.getInstance().unregister(sloc);
			sender.sendMessage(ChatColor.GREEN + locName + "を削除しました");
			return true;
		}else{
			sender.sendMessage(ChatColor.RED + "LocationNameが正しくありません");
			return false;
		}
	}

	@Override
	public String getCommand() {
		return "remove";
	}

	@Override
	public String getPermission() {
		return "enderscroll.command.remove";
	}

	@Override
	public List<String> getHelp() {
		return Arrays.asList(
				ChatColor.YELLOW + "/enderscroll remove <LocationName>",
				ChatColor.YELLOW + "保存されている<LocationName>を削除する"
		);
	}

	@Override
	public int getLength() {
		return 2;
	}

}
