package ProjetMNT;

public class Point {
	private double x;
	private double y;
	private double z;
	
	public Point(double x, double y, double z)	{
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	public Point(Point point1, Point point2, double altitude)	{
		/*
		 * Ne pas choisir une altitude au dessus ou égal à 9999 
		 */
		if ((altitude <= point1.getZ() & altitude >= point2.getZ()) | (altitude <= point2.getZ() & altitude >= point1.getZ()))	{	
			double distance            = Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
			double difference_altitude = Math.abs(point2.getZ() - point1.getZ());
			double X                   = 0;
			double Y                   = 0;
				if (point1.getZ() < point2.getZ())	{
					double difference_petite = altitude - point1.getZ();
					double distance_petite   = difference_petite * distance / difference_altitude;
					double angle             = Math.atan((point2.getY() - point1.getY()) / (point2.getX() - point1.getX()));
					X                        = point1.getX() + (Math.cos(angle)*distance_petite); 
					Y                        = point1.getY() + (Math.sin(angle)*distance_petite);
				}
				else	{
					double difference_petite = altitude - point2.getZ();
					double distance_petite   = difference_petite * distance / difference_altitude;
					double angle             = Math.atan((point1.getY() - point2.getY()) / (point1.getX() - point2.getX()));
					X                        = point2.getX() + (Math.cos(angle)*distance_petite); 
					Y                        = point2.getY() + (Math.sin(angle)*distance_petite);
				}
				this.setX(X);
				this.setY(Y);
				this.setZ(altitude);
		}
		else	{
			System.out.println("L'altitude choisie n'est pas comprise entre ces deux points");
			this.setX(0);
			this.setY(0);
			this.setZ(9999);	//Cette altitude signifie que ce point créé est une erreur
		}
	}
	
	
	
	
	
	
	public String toString(){
		return "x= " + this.x + " y= " + this.y + " z= " + this.z + "\n";
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
	
	public static double[] pointIntermediaire(Point point1, Point point2, double altitude)	{
		/*
		 * Ne pas choisir une altitude au dessus ou égal à 9999 
		 */
		if ((altitude <= point1.getZ() & altitude >= point2.getZ()) | (altitude <= point2.getZ() & altitude >= point1.getZ()))	{	
			double distance            = Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
			double difference_altitude = Math.abs(point2.getZ() - point1.getZ());
			double X                   = 0;
			double Y                   = 0;
				if (point1.getZ() < point2.getZ())	{
					double difference_petite = altitude - point1.getZ();
					double distance_petite   = difference_petite * distance / difference_altitude;
					double angle             = Math.atan((point2.getY() - point1.getY()) / (point2.getX() - point1.getX()));
					X                        = point1.getX() + (Math.cos(angle)*distance_petite); 
					Y                        = point1.getY() + (Math.sin(angle)*distance_petite);
				}
				else	{
					double difference_petite = altitude - point2.getZ();
					double distance_petite   = difference_petite * distance / difference_altitude;
					double angle             = Math.atan((point1.getY() - point2.getY()) / (point1.getX() - point2.getX()));
					X                        = point2.getX() + (Math.cos(angle)*distance_petite); 
					Y                        = point2.getY() + (Math.sin(angle)*distance_petite);
				}
				double[] coordonnees = {X, Y, altitude};
				return coordonnees;
		}
		else	{
			System.out.println("L'altitude choisie n'est pas comprise entre ces deux points");
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		Point point1   = new Point(0,0,0);
		Point point2   = new Point(0,1,5);
		Point point3   = new Point(1,0,10);
		double[] value = pointIntermediaire(point1, point2, 1);
		System.out.println(value[0] + ", " + value[1] + ", " + value[2]);
		double[] value2 = pointIntermediaire(point2, point3, 4);		//Faire en sorte que les erreurs ne bloquent pas le code
		System.out.println(value2[0] + ", " + value2[1] + ", " + value2[2]);
	}


}
