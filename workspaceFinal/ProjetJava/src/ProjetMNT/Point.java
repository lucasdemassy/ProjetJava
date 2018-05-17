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
		/**
		 * Constructeur créant un point intermédiaire entre deux points à une altitude donnée. 
		 * On suppose que l'altitude varie linéairement entre les deux points en entrée
		 * Si l'altitude voulue n'est pas comprise entre ces deux points, le constructeur crée un point erroné
		 * d'altitude 9999 (altitude impossible en réalité), et situé à l'origine (x=0, et y=0)
		 * Il ne faut donc pas choisir une altitude au dessus ou égal à 9999 
		 * Si l'altitude est comprise entre l'altiude des deux points, le nouveau point sera créé sur le segment reliant le premier point au second
		 * 
		 * @param point1: Premier point
		 * @param point2: Second point
		 * @param altitude: altitude souhaitée du nouveau point
		 * 
		 */
		if ((altitude <= point1.getZ() & altitude >= point2.getZ()) | (altitude <= point2.getZ() & altitude >= point1.getZ()))	{	//Si l'altiude donnée est comprise entre l'altitude des deux points en entrée
			double distance            = distance(point1, point2);
			double difference_altitude = Math.abs(point2.getZ() - point1.getZ());
			double X                   = -1;	//Initialisation de la coordonnée X
			double Y                   = -1;	//Initialisation de la coordonnée Y
			Point pointMin             = new Point(X,Y,altitude);	//Initialisation du point de plus petite altitude
			Point pointMax             = new Point(X,Y,altitude);	//Initialisation du point de plus grande altitude
			if(point1.getZ() < point2.getZ())	{
				pointMin = point1;	//PointMin est le point de plus petite altitude
				pointMax = point2;	//PointMax est le point de plus grande altitude
			}
			else	{
				pointMin = point2;	//PointMin est le point de plus petite altitude
				pointMax = point1;	//PointMax est le point de plus grande altitude
			}
			double difference_petite = altitude - pointMin.getZ();	//Différence d'altitude entre point le plus bas et altitude souhaitée
			double distance_petite   = difference_petite * distance / difference_altitude;	//Distance entre point le plus bas et le future point à l'altitude souhaitée
			if(pointMin.getX() == pointMax.getX()) {	//Si les points ont la même abscisse
				double angle = (Math.abs(pointMax.getY() - pointMin.getY()) / (pointMax.getY() - pointMin.getY())) * Math.PI/2;
				X            = pointMin.getX() + (Math.cos(angle)*distance_petite); 
				Y            = pointMin.getY() + (Math.sin(angle)*distance_petite);
			}
			else	{
				if ((pointMin.getX() - pointMax.getX())/distance >= 0)	{	//Si le cosinus de l'angle horizontal-pointMin-pointMax est positif
					double angle = Math.atan((pointMax.getY() - pointMin.getY()) / (pointMax.getX() - pointMin.getX()));
					X            = pointMin.getX() - (Math.cos(angle)*distance_petite); 
					Y            = pointMin.getY() - (Math.sin(angle)*distance_petite);
				}
				else	{
					double angle = Math.atan((pointMin.getY() - pointMax.getY()) / (pointMin.getX() - pointMax.getX()));
					X            = pointMin.getX() + (Math.cos(angle)*distance_petite); 
					Y            = pointMin.getY() + (Math.sin(angle)*distance_petite);
				}
			}
		this.setX(X);
		this.setY(Y);
		this.setZ(altitude);
		}
		else	{	//L'altitude choisie n'est pas comprise entre ces deux points
			this.setX(0);
			this.setY(0);
			this.setZ(9999);	//Cette altitude signifie que ce point créé est une erreur
			
		}
	}
	
	public String toString(){
		/**
		 * Méthode convertissant un point en String
		 * Cette méthode est commode pour afficher les coordonnées d'un point sous forme de texte
		 * 
		 * @return Coordonnées x, y, et z en String
		 */
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
	
	public static double distance(Point point1, Point point2)	{
		/**
		 * Méthode de classe calculant la distance euclidienne entre deux points
		 * 
		 * @param point1: Premier point
		 * @param point2: Second point
		 * @return distance entre le premier et le second point
		 */
		return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
	}
	
	public static Point plusProche(Point point, List<Point> liste)	{
		/**
		 * Méthode de classe déterminant le point le plus proche entre un point et une liste de point
		 * 
		 * @param point: point dont l'on souhaite connaître son plus proche voisin
		 * @param liste de point
		 * @return point, contenue dans la liste de point, ayant la distance euclidienne la plus faible avec le point donné en entrée
		 * 
		 * @see ProjetMNT.Point.egal
		 * @see ProjetMNT.Point.distance
		 */
		double minimum = (double) Integer.MAX_VALUE;	//Initialisation de la distance minimale
		int index      = 0;	//Variable qui garde en mémoire l'emplacement dans la liste
							//du point le plus proche du dernier point de la liste triée
		for(int i=0; i<liste.size(); i++)	{
			if(liste.get(i).egal(point))	{	//Si le point est 
			}
			else	{
				double distance = distance(point, liste.get(i));
				if( distance < minimum)	{ //Distance euclidienne
					minimum = distance;
					index = i;
				}
			}
		}
		return liste.get(index);
	}
	
	public boolean egal(Point point)	{
		/**
		 * Méthode permettant de savoir si un point est égale à un autre à un sigma près
		 * 
		 * @param point: Point que l'on veut vérifier l'égalité
		 * @return true si les deux points sont égaux à un sigma près. false sinon
		 */
		double X = point.getX();
		double Y = point.getY();
		double Z = point.getZ();
		double sigma = 0.0000000000000001;	//La valeur de sigma a été choisi comme la plus petite décimale qu'Eclipse peut supporter
		if(this.getX() >= X-sigma && this.getX() <= X+sigma)	{
			if(this.getY() >= Y-sigma && this.getY() <= Y+sigma)	{
				if(this.getZ() >= Z-sigma && this.getZ() <= Z+sigma)	{
					return true;
				}
			}
		}
		return false;
		
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
		
		Point point12 = new Point(point1, point2, 2);
		Point point13 = new Point(point1, point3, 2);
		Point point14 = new Point(point1, point4, 2);
		Point point15 = new Point(point1, point5, 2);
		Point point17 = new Point(point6, point7, 2);
		Point point18 = new Point(point6, point8, 2);
		Point point19 = new Point(point6, point9, 2);
		Point point20 = new Point(point6, point10, 2);
		System.out.println(point12.toString());
		System.out.println("Résultat attendu: 0.8, 0.8");
		System.out.println(point13.toString());
		System.out.println("Résultat attendu: 1.2, 1.2");
		System.out.println(point14.toString());
		System.out.println("Résultat attendu: 1.2, 0.8");
		System.out.println(point15.toString());
		System.out.println("Résultat attendu: 0.8, 1.2");
		System.out.println(point17.toString());
		System.out.println("Résultat attendu: 0.2, 0.2");
		System.out.println(point18.toString());
		System.out.println("Résultat attendu: 1.8, 1.8");
		System.out.println(point19.toString());
		System.out.println("Résultat attendu: 1.8, 0.2");
		System.out.println(point20.toString());
		System.out.println("Résultat attendu: 0.2, 1.8");
		
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
