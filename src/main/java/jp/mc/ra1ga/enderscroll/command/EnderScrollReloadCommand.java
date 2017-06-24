package jp.mc.ra1ga.enderscroll.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.json.ScrollLocationManager;
import jp.mc.ra1ga.mycore.command.SubCommandable;

public class EnderScrollReloadCommand implements SubCommandable {

	@Override
	public boolean run(CommandSender sender, String[] args, JavaPlugin plugin) {
		plugin.reloadConfig();
		ScrollLocationManager.reload(plugin);
		sender.sendMessage(ChatColor.GREEN + "リロードしました");
		return true;
	}

	@Override
	public String getCommand() {
		return "reload";
	}

	@Override
	public String getPermission() {
		return "enderscroll.command.reload";
	}

	@Override
	public List<String> getHelp() {
		return Arrays.asList(
				ChatColor.YELLOW + "/enderscroll reload",
				ChatColor.YELLOW + "コンフィグ/Jsonをリロードする"
		);
	}

	@Override
	public int getLength() {
		return 1;
	}

}
