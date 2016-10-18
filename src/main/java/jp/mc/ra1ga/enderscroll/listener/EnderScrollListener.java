package jp.mc.ra1ga.enderscroll.listener;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import jp.mc.ra1ga.enderscroll.main.EnderScroll;

public class EnderScrollListener implements Listener {
	public EnderScrollListener(EnderScroll plugin) {
		this.plugin = plugin;
		this.casting = new HashMap<>();
	}

	private EnderScroll plugin;
	private HashMap<Player, Boolean> casting;

	@EventHandler
	public void onClickScroll(PlayerInteractEvent e){ //enderscroll.use
		final Player p = e.getPlayer();
		if(p.hasPermission("enderscroll.use")){
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
				ItemStack i = e.getItem();
				if(i != null && i.getType().equals(Material.PAPER)){
					String scrollName = plugin.getPacketUtil().getScrollName(i);
					if(scrollName != null){
						if(plugin.getConfig().contains(scrollName + ".World") &&
								plugin.getConfig().contains(scrollName + ".X") &&
								plugin.getConfig().contains(scrollName + ".Y") &&
								plugin.getConfig().contains(scrollName + ".Z") &&
								plugin.getConfig().contains(scrollName + ".Yaw") &&
								plugin.getConfig().contains(scrollName + ".Pitch")){
							if(!casting.containsKey(p)) casting.put(p, false);
							if(!casting.get(p)){
								final String w = plugin.getConfig().getString(scrollName + ".World");
								final double x = plugin.getConfig().getDouble(scrollName + ".X");
								final double y = plugin.getConfig().getDouble(scrollName + ".Y");
								final double z = plugin.getConfig().getDouble(scrollName + ".Z");
								final float yaw = (float)plugin.getConfig().getDouble(scrollName + ".Yaw");
								final float pitch = (float)plugin.getConfig().getDouble(scrollName + ".Pitch");

								casting.put(p, true);

								new BukkitRunnable() {
									int count = 40;
									@Override
									public void run() {
										count--;
										plugin.getPacketUtil().playScrollParticle(p.getLocation());
										plugin.getPacketUtil().playScrollParticle(new Location(Bukkit.getWorld(w), x, y, z));
										if(count <= 0){
											p.teleport(new Location(Bukkit.getWorld(w), x, y, z, yaw, pitch));
											Bukkit.getWorld(w).playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
											casting.put(p, false);
											cancel();
										}
									}
								}.runTaskTimer(plugin, 0, 1L);

								if(i.getAmount() == 1){
									ItemStack set = new ItemStack(Material.AIR);
									p.getInventory().setItem(p.getInventory().getHeldItemSlot(), set);
								}else{
									i.setAmount(i.getAmount() - 1);
								}
							}else{
								p.sendMessage(ChatColor.DARK_PURPLE + "Casting...");
							}
						}else{
							ItemStack set = new ItemStack(Material.AIR);
							p.getInventory().setItem(p.getInventory().getHeldItemSlot(), set);
							p.sendMessage(ChatColor.RED + "This location has been deleted.");
						}
					}
				}
			}
		}

	}

}
