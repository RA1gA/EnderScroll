package jp.mc.ra1ga.enderscroll.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import jp.mc.ra1ga.enderscroll.main.EnderScroll;
import jp.mc.ra1ga.mycore.json2.JsonFile;

public class ScrollLocationManager {

	private List<ScrollLocation> list;

	private static final ScrollLocationManager ins = new ScrollLocationManager();
	public static ScrollLocationManager getInstance() {
		return ins;
	}

	private ScrollLocationManager() {
		list = new ArrayList<>();
	}

	public void register(ScrollLocation location) {
		list.add(location);
	}

	public void registerAll(List<ScrollLocation> locations) {
		list.addAll(locations);
	}

	public void registerAll(ScrollLocation[] locations) {
		registerAll(Arrays.asList(locations));
	}

	public void unregister(ScrollLocation location) {
		list.remove(location);
	}

	public void unregisterFrom(String name) {
		for(ScrollLocation loc : list) {
			if(loc.getName().equals(name)) {
				unregister(loc);
				break;
			}
		}
	}

	public void unregisterAll() {
		list.clear();
	}

	public ScrollLocation getScrollLocationFrom(String locationName) {
		for(ScrollLocation loc : list) {
			if(loc.getName().equals(locationName)) {
				return loc;
			}
		}
		return null;
	}

	public List<ScrollLocation> getScrollLocations() {
		return list;
	}

	public static void save(JavaPlugin plugin) {
		List<ScrollLocation> list = ScrollLocationManager.getInstance().getScrollLocations();
		JsonFile.writeJson(plugin, EnderScroll.SCROLL_LOCATION_FILENAME, list);
	}

	public static void reload(JavaPlugin plugin) {
		ScrollLocation[] list = JsonFile.loadJsonToObject(plugin, EnderScroll.SCROLL_LOCATION_FILENAME, ScrollLocation[].class);
		if(list != null) {
			ScrollLocationManager.getInstance().unregisterAll();
			ScrollLocationManager.getInstance().registerAll(list);
		}
	}

}
