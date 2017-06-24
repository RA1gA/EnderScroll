package jp.mc.ra1ga.enderscroll.config;

import jp.mc.ra1ga.enderscroll.main.EnderScroll;

public class ConfigUtil {

	private ConfigUtil() {
	}

	public static void setConfig(EnderScroll plugin, String locName, String world, double x, double y, double z, float yaw, float pitch){
		plugin.reloadConfig();
		plugin.getConfig().set(locName + ".World", world);
		plugin.getConfig().set(locName + ".X", x);
		plugin.getConfig().set(locName + ".Y", y);
		plugin.getConfig().set(locName + ".Z", z);
		plugin.getConfig().set(locName + ".Yaw", yaw);
		plugin.getConfig().set(locName + ".Pitch", pitch);
		plugin.saveConfig();
	}

}
