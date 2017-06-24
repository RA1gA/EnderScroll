package jp.mc.ra1ga.mycore.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreCommand implements CommandExecutor {

	private JavaPlugin plugin;
	private List<SubCommandable> list;

	public CoreCommand(JavaPlugin plugin) {
		this.plugin = plugin;
		this.list = new ArrayList<>();
	}

	public CoreCommand(JavaPlugin plugin, SubCommandable... scmds) {
		this(plugin);
		registerSubCommands(scmds);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(args.length <= 0) {
			sender.sendMessage(ChatColor.RED + "/" + label + " help");
			return false;
		}

		String command = args[0];

		List<SubCommandable> scmds = new ArrayList<>();
		list.sort(new CoreCommandLengthComparator());
		for(SubCommandable element : list) {
			if(command.equalsIgnoreCase(element.getCommand())) {
				scmds.add(element);
			}
		}

		if(scmds.isEmpty()) {
			List<String> commands = new ArrayList<>();
			for(SubCommandable element : list) {
				if(!commands.contains(element.getCommand())) {
					commands.add(element.getCommand());
				}
			}

			Collections.sort(commands);
			for(String element : commands) {
				sender.sendMessage(ChatColor.RED + "/" + label + " " + element);
			}
			return false;
		}

		if(!sender.hasPermission(scmds.get(0).getPermission())) {
			sender.sendMessage(ChatColor.RED + "You dont have permission.(" + scmds.get(0).getPermission() + ")");
			return false;
		}

		Collections.reverse(scmds);
		for(SubCommandable scmd : scmds) {
			if(args.length >= scmd.getLength()){
				return scmd.run(sender, args, plugin);
			}
		}

		for(SubCommandable scmd : scmds) {
			for(String msg : scmd.getHelp()){
				sender.sendMessage(msg);
			}
		}
		return false;

	}

	public void registerSubCommand(SubCommandable scmd) {
		list.add(scmd);
	}

	public void registerSubCommands(SubCommandable[] scmds) {
		for(SubCommandable scmd : scmds) {
			list.add(scmd);
		}
	}

	public void unregisterSubCommand(SubCommandable scmd) {
		if(list.contains(scmd)) {
			list.remove(scmd);
		}
	}

}
