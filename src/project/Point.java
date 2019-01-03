package project;

public class Point {

	private double x;
	private double z;

	public Point(double x, double z) {
		this.x = x;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double dist(Point b) {
		double dx = this.x - b.x;
		double dz = this.z - b.z;
		return Math.sqrt(dx*dx + dz*dz);
	}
	
	public String toString() {
		return "x=" + x + "\t z=" + z;
	}
}
