package jp.mc.ra1ga.enderscroll.json;

public class ScrollLocation {

	private String name;
	private String world;
	private double x;
	private double y;
	private double z;
	private float yaw;
	private float pitch;

	public ScrollLocation(String name, String world, double x, double y, double z, float yaw, float pitch) {
		this.name = name;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getWorld() {
		return world;
	}
	public void setWorld(String world) {
		this.world = world;
	}

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}

	public float getYaw() {
		return yaw;
	}
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getPitch() {
		return pitch;
	}
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

}
