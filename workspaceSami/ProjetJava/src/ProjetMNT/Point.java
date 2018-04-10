package ProjetMNT;

public class Point {
	
	private double x;
	private double y;
	private double z;
	
	public Point(double x, double y, double z)	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static double[] pointIntermediraire(Point point1, Point point2, double altitude)	{
		double distance = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
		double difference_altitude = Math.abs(point2.z - point1.z);
		double deniveleParUnite = distance/difference_altitude;
		if (point2.z < point1.z)	{
			
		}
		return null;
	}
	
	public String toString(){
		return "x= " + this.x + " y= " + this.y + " z= " + this.z + "\n";
	}

	public double getX() {
		return x;
	}

	public void setX() {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY() {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ() {
		this.z = z;
	}

}
