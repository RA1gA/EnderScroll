package jp.mc.ra1ga.enderscroll.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.command.EnderScrollCommand;
import jp.mc.ra1ga.enderscroll.listener.EnderScrollListener;
import jp.mc.ra1ga.enderscroll.util.PacketUtil;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_10_R1;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_8_R2;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_8_R3;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_9_R1;
import jp.mc.ra1ga.enderscroll.util.PacketUtil_1_9_R2;

public class EnderScroll extends JavaPlugin {

	private PacketUtil packet;

	@Override
	public void onEnable(){

		if(setupPacketUtil()){

			getCommand("enderscroll").setExecutor(new EnderScrollCommand(this));

			getServer().getPluginManager().registerEvents(new EnderScrollListener(this), this);

			getConfig().addDefault("ScrollName", "&5EnderScroll [&name&]");
			getConfig().options().copyDefaults(true);
			saveConfig();

		}else{

			getLogger().severe("Failed to setup EnderScroll!");

			Bukkit.getPluginManager().disablePlugin(this);

		}

	}

	@Override
	public void onDisable(){
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

		if(version.equals("v1_10_R1")){ //1.10.0 - 1.10.2
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
