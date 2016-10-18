package jp.mc.ra1ga.enderscroll.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class PacketUtil_1_8_R3 extends PacketUtil {

	@Override
	public void playScrollParticle(Location loc) {
		sendPacket(getParticlePacket(EnumParticle.PORTAL, loc, true, 0.0F, 0.8F, 0.0F, 0.7F, 50));
	}

	@Override
	public org.bukkit.inventory.ItemStack setScrollTag(org.bukkit.inventory.ItemStack item, String locName) {
		ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		if(nmsStack != null){
			NBTTagCompound tag = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
			tag.setString("EnderScroll", locName);
			nmsStack.setTag(tag);
			item = CraftItemStack.asBukkitCopy(nmsStack);
		}
		return item;
	}

	@Override
	public String getScrollName(org.bukkit.inventory.ItemStack item) {
		String name = null;
		ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		if(nmsStack != null){
			NBTTagCompound tag = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
			name = tag.getString("EnderScroll");
		}
		return name;
	}

	private void sendPacket(Player player, Packet<?> packet){
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}
	private void sendPacket(Packet<?> packet){
		for(Player player : Bukkit.getOnlinePlayers()){
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
		}
	}

	private PacketPlayOutWorldParticles getParticlePacket(EnumParticle type, Location loc, boolean longdistance, float x, float y, float z, float speed, int amount,int data){
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type,longdistance,(float)loc.getX(),(float)loc.getY(),(float)loc.getZ(),x,y,z,speed,amount,data);
		return packet;
	}
	private PacketPlayOutWorldParticles getParticlePacket(EnumParticle type, Location loc, boolean longdistance, float x, float y, float z, float speed, int amount){
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type,longdistance,(float)loc.getX(),(float)loc.getY(),(float)loc.getZ(),x,y,z,speed,amount);
		return packet;
	}

}
