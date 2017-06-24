package jp.mc.ra1ga.enderscroll.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.json.ScrollLocationManager;
import jp.mc.ra1ga.mycore.command.SubCommandable;

public class EnderScrollListCommand implements SubCommandable {

	@Override
	public boolean run(CommandSender sender, String[] args, JavaPlugin plugin) {
		String list = ScrollLocationManager.getInstance().getScrollLocations().get(0).getName();
		for(int i = 1; i < ScrollLocationManager.getInstance().getScrollLocations().size(); i++) {
			list += ", " + ScrollLocationManager.getInstance().getScrollLocations().get(i).getName();
		}
		sender.sendMessage(ChatColor.GREEN + list);
		return true;
	}

	@Override
	public String getCommand() {
		return "list";
	}

	@Override
	public String getPermission() {
		return "enderscroll.command.list";
	}

	@Override
	public List<String> getHelp() {
		return Arrays.asList(
				ChatColor.YELLOW + "/enderscroll list",
				ChatColor.YELLOW + "現在存在するすべての<LocationName>を表示する"
		);
	}

	@Override
	public int getLength() {
		return 1;
	}

}
