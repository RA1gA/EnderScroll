package jp.mc.ra1ga.enderscroll.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.command.EnderScrollGiveCommand;
import jp.mc.ra1ga.enderscroll.command.EnderScrollListCommand;
import jp.mc.ra1ga.enderscroll.command.EnderScrollLongSetCommand;
import jp.mc.ra1ga.enderscroll.command.EnderScrollMiddleSetCommand;
import jp.mc.ra1ga.enderscroll.command.EnderScrollReloadCommand;
import jp.mc.ra1ga.enderscroll.command.EnderScrollRemoveCommand;
import jp.mc.ra1ga.enderscroll.command.EnderScrollSaveCommand;
import jp.mc.ra1ga.enderscroll.command.EnderScrollShortSetCommand;
import jp.mc.ra1ga.enderscroll.json.ScrollLocationManager;
import jp.mc.ra1ga.enderscroll.listener.EnderScrollListener;
import jp.mc.ra1ga.enderscroll.util.PacketUtil;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_10_R1;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_11_R1;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_12_R1;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_8_R2;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_8_R3;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_9_R1;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_9_R2;
import jp.mc.ra1ga.mycore.command.CoreCommand;

public class EnderScroll extends JavaPlugin {

	public static final String SCROLL_LOCATION_FILENAME = "ScrollLocation.json";

	private PacketUtil packet;

	@Override
	public void onEnable(){

		if(setupPacketUtil()){

			getCommand("enderscroll").setExecutor(
					new CoreCommand(this,
							new EnderScrollShortSetCommand(),
							new EnderScrollMiddleSetCommand(),
							new EnderScrollLongSetCommand(),
							new EnderScrollGiveCommand(),
							new EnderScrollRemoveCommand(),
							new EnderScrollListCommand(),
							new EnderScrollReloadCommand(),
							new EnderScrollSaveCommand()));

			getServer().getPluginManager().registerEvents(new EnderScrollListener(this), this);

			getConfig().addDefault("ScrollName", "&5EnderScroll [%name%]");
			getConfig().options().copyDefaults(true);
			saveConfig();

			ScrollLocationManager.reload(this);

		}else{

			getLogger().severe("Failed to setup EnderScroll!");

			Bukkit.getPluginManager().disablePlugin(this);

		}

	}

	@Override
	public void onDisable(){
		ScrollLocationManager.save(this);
	}

	public PacketUtil getPacketUtil(){
		return packet;
	}

	private boolean setupPacketUtil(){
		String version;

		try{
			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		}catch (ArrayIndexOutOfBoundsException e){
			return false;
		}

		if(version.equals("v1_12_R1")){ //1.12 -
			packet = new PacketUtil_1_12_R1();
		}else if(version.equals("v1_11_R1")){ //1.11 - 1.11.2
			packet = new PacketUtil_1_11_R1();
		}else if(version.equals("v1_10_R1")){ //1.10.0 - 1.10.2
			packet = new PacketUtil_1_10_R1();
		}else if(version.equals("v1_9_R2")){ //1.9.4
			packet = new PacketUtil_1_9_R2();
		}else if(version.equals("v1_9_R1")){ //1.9.0 - 1.9.2
			packet = new PacketUtil_1_9_R1();
		}else if(version.equals("v1_8_R3")){ //1.8.4 - 1.8.8
			packet = new PacketUtil_1_8_R3();
		}else if(version.equals("v1_8_R2")){ //1.8.3
			packet = new PacketUtil_1_8_R2();
		}

		return packet != null;

	}

}
