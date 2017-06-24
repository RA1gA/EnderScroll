package jp.mc.ra1ga.enderscroll.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.json.ScrollLocationManager;
import jp.mc.ra1ga.mycore.command.SubCommandable;

public class EnderScrollSaveCommand implements SubCommandable {

	@Override
	public boolean run(CommandSender sender, String[] args, JavaPlugin plugin) {
		ScrollLocationManager.save(plugin);
		sender.sendMessage(ChatColor.GREEN + "セーブしました");
		return true;
	}

	@Override
	public String getCommand() {
		return "save";
	}

	@Override
	public String getPermission() {
		return "enderscroll.command.save";
	}

	@Override
	public List<String> getHelp() {
		return Arrays.asList(
				ChatColor.YELLOW + "/enderscroll save",
				ChatColor.YELLOW + "Jsonをセーブする"
		);
	}

	@Override
	public int getLength() {
		return 1;
	}

}
