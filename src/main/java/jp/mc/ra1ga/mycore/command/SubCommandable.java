package jp.mc.ra1ga.mycore.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public interface SubCommandable {

	public boolean run(CommandSender sender, String[] args, JavaPlugin plugin);

	public String getCommand();
	public String getPermission();
	public List<String> getHelp();
	public int getLength();

}
