package ProjetMNT;

import java.util.List;

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
		 * Ne pas choisir une altitude au dessus ou √©gal √† 9999 
		 */
		if ((altitude <= point1.getZ() & altitude >= point2.getZ()) | (altitude <= point2.getZ() & altitude >= point1.getZ()))	{	
			double distance            = distance(point1, point2);
			double difference_altitude = Math.abs(point2.getZ() - point1.getZ());
			double X                   = 0;
			double Y                   = 0;
			Point pointMin             = new Point(X,Y,altitude);
			Point pointMax             = new Point(X,Y,altitude);
			if(point1.getZ() < point2.getZ())	{
				pointMin = point1;
				pointMax = point2;
			}
			else	{
				pointMin = point2;
				pointMax = point1;
			}
			//System.out.println("pointMin: " + pointMin.toString() + "pointMax: " + pointMax.toString());
			double difference_petite = altitude - pointMin.getZ();	//DiffÈrence d'altitude entre point le plus bas et altitude souhaitÈe
			double distance_petite   = difference_petite * distance / difference_altitude;	//Distance entre point le plus bas et le future point ‡ l'altitude souhaitÈe
			if(pointMin.getX() == pointMax.getX()) {
				//System.out.println("X Ègaux");
				double angle = (Math.abs(pointMax.getY() - pointMin.getY()) / (pointMax.getY() - pointMin.getY())) * Math.PI/2;
				X                        = pointMin.getX() + (Math.cos(angle)*distance_petite); 
				Y                        = pointMin.getY() + (Math.sin(angle)*distance_petite);
				//System.out.println("Ajout: " + Math.sin(angle)*distance_petite + ", Angle: " + angle);
			}
			else	{	//Soucis, on fait toujours cette condition
				//System.out.println("PAS COOL");
				if ((pointMin.getX() - pointMax.getX())/distance >= 0)	{	///////////////Erreur ici, s√ªrement au niveau de l'angle alors que dans l'autre condition √ßa marche
					double angle             = Math.atan((pointMax.getY() - pointMin.getY()) / (pointMax.getX() - pointMin.getX()));
					X                        = pointMin.getX() - (Math.cos(angle)*distance_petite); 
					Y                        = pointMin.getY() - (Math.sin(angle)*distance_petite);
				}
				else	{
					double angle             = Math.atan((pointMin.getY() - pointMax.getY()) / (pointMin.getX() - pointMax.getX()));
					X                        = pointMin.getX() + (Math.cos(angle)*distance_petite); 
					Y                        = pointMin.getY() + (Math.sin(angle)*distance_petite);
				}
			}
		this.setX(X);
		this.setY(Y);
		this.setZ(altitude);
		}
		else	{
			System.out.println("L'altitude choisie n'est pas comprise entre ces deux points");
			this.setX(0);
			this.setY(0);
			this.setZ(9999);	//Cette altitude signifie que ce point cr√©√© est une erreur
			
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
		 * Ne pas choisir une altitude au dessus ou √©gal √† 9999 
		 */
		if ((altitude <= point1.getZ() & altitude >= point2.getZ()) | (altitude <= point2.getZ() & altitude >= point1.getZ()))	{	
			double distance            = distance(point1, point2);
			double difference_altitude = Math.abs(point2.getZ() - point1.getZ());
			double X                   = 0;
			double Y                   = 0;
			Point pointMin             = new Point(X,Y,altitude);
			Point pointMax             = new Point(X,Y,altitude);
			if(point1.getZ() < point2.getZ())	{
				pointMin = point1;
				pointMax = point2;
			}
			else	{
				pointMin = point2;
				pointMax = point1;
			}
			//System.out.println("pointMin: " + pointMin.toString() + "pointMax: " + pointMax.toString());
			double difference_petite = altitude - pointMin.getZ();	//DiffÈrence d'altitude entre point le plus bas et altitude souhaitÈe
			double distance_petite   = difference_petite * distance / difference_altitude;	//Distance entre point le plus bas et le future point ‡ l'altitude souhaitÈe
			if(pointMin.getX() == pointMax.getX()) {
				//System.out.println("X Ègaux");
				double angle = (Math.abs(pointMax.getY() - pointMin.getY()) / (pointMax.getY() - pointMin.getY())) * Math.PI/2;
				X                        = pointMin.getX() + (Math.cos(angle)*distance_petite); 
				Y                        = pointMin.getY() + (Math.sin(angle)*distance_petite);
				//System.out.println("Ajout: " + Math.sin(angle)*distance_petite + ", Angle: " + angle);
			}
			else	{	//Soucis, on fait toujours cette condition
				//System.out.println("PAS COOL");
				if ((pointMin.getX() - pointMax.getX())/distance >= 0)	{	///////////////Erreur ici, s√ªrement au niveau de l'angle alors que dans l'autre condition √ßa marche
					double angle             = Math.atan((pointMax.getY() - pointMin.getY()) / (pointMax.getX() - pointMin.getX()));
					X                        = pointMin.getX() - (Math.cos(angle)*distance_petite); 
					Y                        = pointMin.getY() - (Math.sin(angle)*distance_petite);
				}
				else	{
					double angle             = Math.atan((pointMin.getY() - pointMax.getY()) / (pointMin.getX() - pointMax.getX()));
					X                        = pointMin.getX() + (Math.cos(angle)*distance_petite); 
					Y                        = pointMin.getY() + (Math.sin(angle)*distance_petite);
				}
			}
		double[] coordonnees = {X, Y, altitude};
		return coordonnees;
		}
		else	{
			System.out.println("L'altitude choisie n'est pas comprise entre ces deux points");
			return null;	////////////////////////Faire en sorte que les erreurs ne bloquent pas le code
		}
	}
	
	public static double distance(Point point1, Point point2)	{
		return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
	}
	
	public static Point plusProche(Point point, List<Point> liste)	{
		double minimum = (double) Integer.MAX_VALUE;	//Initialisation de la distance minimale
		int index      = 0;	//Variable qui garde en mÈmoire l'emplacement dans la liste
							//du point le plus proche du dernier point de la liste triÈe
		for(int i=0; i<liste.size(); i++)	{
			double distance = distance(point, liste.get(i));
			if( distance < minimum)	{ //Distance euclidienne
				minimum = distance;
				index = i;
			}
		}
		return liste.get(index);
	}
	
	public static void main(String[] args) {
		Point point1   = new Point(1,1,0);
		Point point2   = new Point(0,0,10);
		Point point3   = new Point(2,2,10);
		Point point4   = new Point(2,0,10);
		Point point5   = new Point(0,2,10);
		Point point6   = new Point(1,1,10);
		Point point7   = new Point(0,0,0);
		Point point8   = new Point(2,2,0);
		Point point9   = new Point(2,0,0);
		Point point10  = new Point(0,2,0);
		double[] value = pointIntermediaire(point1, point2, 2);			
		System.out.println(value[0] + ", " + value[1] + ", " + value[2]);
		System.out.println("RÈsultat attendu: 0.8, 0.8");
		double[] value2 = pointIntermediaire(point1, point3, 2);		
		System.out.println(value2[0] + ", " + value2[1] + ", " + value2[2]);
		System.out.println("RÈsultat attendu: 1.2, 1.2");
		double[] value3 = pointIntermediaire(point1, point4, 2);
		System.out.println(value3[0] + ", " + value3[1] + ", " + value3[2]);
		System.out.println("RÈsultat attendu: 1.2, 0.8");
		double[] value4 = pointIntermediaire(point1, point5, 2);	
		System.out.println(value4[0] + ", " + value4[1] + ", " + value4[2]);
		System.out.println("RÈsultat attendu: 0.8, 1.2");
		double[] value5 = pointIntermediaire(point6, point7, 2);		
		System.out.println(value5[0] + ", " + value5[1] + ", " + value5[2]);
		System.out.println("RÈsultat attendu: 0.2, 0.2");
		double[] value6 = pointIntermediaire(point6, point8, 2);		
		System.out.println(value6[0] + ", " + value6[1] + ", " + value6[2]);
		System.out.println("RÈsultat attendu: 1.8, 1.8");
		double[] value7 = pointIntermediaire(point6, point9, 2);		
		System.out.println(value7[0] + ", " + value7[1] + ", " + value7[2]);
		System.out.println("RÈsultat attendu: 1.8, 0.2");
		double[] value8 = pointIntermediaire(point6, point10, 2);
		System.out.println(value8[0] + ", " + value8[1] + ", " + value8[2]);
		System.out.println("RÈsultat attendu: 0.2, 1.8");
		
		Point point12 = new Point(point1, point2, 2);
		Point point13 = new Point(point1, point3, 2);
		Point point14 = new Point(point1, point4, 2);
		Point point15 = new Point(point1, point5, 2);
		Point point17 = new Point(point6, point7, 2);
		Point point18 = new Point(point6, point8, 2);
		Point point19 = new Point(point6, point9, 2);
		Point point20 = new Point(point6, point10, 2);
		System.out.println(point12.toString());
		System.out.println("RÈsultat attendu: 0.8, 0.8");
		System.out.println(point13.toString());
		System.out.println("RÈsultat attendu: 1.2, 1.2");
		System.out.println(point14.toString());
		System.out.println("RÈsultat attendu: 1.2, 0.8");
		System.out.println(point15.toString());
		System.out.println("RÈsultat attendu: 0.8, 1.2");
		System.out.println(point17.toString());
		System.out.println("RÈsultat attendu: 0.2, 0.2");
		System.out.println(point18.toString());
		System.out.println("RÈsultat attendu: 1.8, 1.8");
		System.out.println(point19.toString());
		System.out.println("RÈsultat attendu: 1.8, 0.2");
		System.out.println(point20.toString());
		System.out.println("RÈsultat attendu: 0.2, 1.8");
		
		Point point21 = new Point(0,1,10);
		Point point22 = new Point(1,0,10);
		Point point23 = new Point(2,1,10);
		Point point24 = new Point(1,2,10);
		Point point26 = new Point(point1, point21, 2);
		Point point27 = new Point(point1, point22, 2);
		Point point28 = new Point(point1, point23, 2);
		Point point29 = new Point(point1, point24, 2);
		System.out.println(point26.toString());
		System.out.println(point27.toString());	
		System.out.println(point28.toString());	
		System.out.println(point29.toString());	
	}


}
