package jp.mc.ra1ga.enderscroll.util;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public abstract class PacketUtil {

	public abstract void playScrollParticle(Location loc);
	public abstract ItemStack setScrollTag(ItemStack item, String locName);
	public abstract String getScrollName(ItemStack item);
	
}
