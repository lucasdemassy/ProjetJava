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
	
	public static double[] pointIntermediaire(Point point1, Point point2, double altitude)	{
		if ((altitude <= point1.z & altitude >= point2.z) | (altitude <= point2.z & altitude >= point1.z))	{	
			double distance = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
			double difference_altitude = Math.abs(point2.z - point1.z);
			double X = 0;
			double Y = 0;
				if (point1.z < point2.z)	{
					double difference_petite = altitude - point1.z;
					double distance_petite = difference_petite * distance / difference_altitude;
					double angle = Math.atan((point2.y - point1.y) / (point2.x - point1.x));
					X = point1.x + (Math.cos(angle)*distance_petite); 
					Y = point1.y + (Math.sin(angle)*distance_petite);
				}
				else	{
					double difference_petite = altitude - point2.z;
					double distance_petite = difference_petite * distance / difference_altitude;
					double angle = Math.atan((point1.y - point2.y) / (point1.x - point2.x));
					X = point2.x + (Math.cos(angle)*distance_petite); 
					Y = point2.y + (Math.sin(angle)*distance_petite);
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
		Point point1 = new Point(0,0,0);
		Point point2 = new Point(0,1,5);
		Point point3 = new Point(1,0,10);
		double[] value = pointIntermediaire(point1, point2, 1);
		System.out.println(value[0] + ", " + value[1] + ", " + value[2]);
		double[] value2 = pointIntermediaire(point2, point3, 4);		//Faire en sorte que les erreurs ne bloquent pas le code
		System.out.println(value2[0] + ", " + value2[1] + ", " + value2[2]);
	}

}
