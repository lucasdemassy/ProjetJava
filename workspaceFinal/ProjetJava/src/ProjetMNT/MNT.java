package ProjetMNT;
import ProjetMNT.Point;
import java.util.ArrayList;
import java.util.List;

public class MNT {
	private List<Point> points;
	private List<Courbe> courbes;
	
	
	public MNT(List<Point> points)	{
		/**
		 * Constructeur classique qui à partir d'une liste de point créer un MNT.
		 * Ce constructeur initialise la liste de courbes de l'attribut suivant: List<Courbe> courbes
		 * 
		 * @param points: liste de point sous forme List<Point>
		 * 
		 */
		this.setPoints(points);
		this.setCourbes(new ArrayList<Courbe>());
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public List<Courbe> getCourbes() {
		return courbes;
	}

	public void setCourbes(List<Courbe> courbes) {
		this.courbes = courbes;
	}
	
	public void CourbePas(double pas)	{
		/**
		 * Méthode ajoutant au MNT des courbes de niveau équidistantes d'un pas renseigné en entrée.
		 * La première courbe de niveau est à 1 mètre au dessus du niveau de la mer.
		 * Nous avons fait ce choix car le format ASCII GRID ne nous permet pas un affichage correcte d'une courbe de niveau d'altitude 0
		 * 
		 * @param pas: altitude séparant chaque courbe de niveau. il est de type double
		 * 
		 * @see ProjetMNT.Courbe.planAltimetrique
		 * @see ProjetMNT.MNT.pointAltitude
		 * @see Dessin2D.MNTDessin.zMax
		 */
		for(double altitude=1; altitude <= Dessin2D.MNTDessin.zMax(this.getPoints()); altitude += pas)	{
			List<Point> points_altitude = this.pointAltitude(altitude);
			List<Courbe> plan_altitude  = Courbe.planAltimetrique(points_altitude, this);
			for(int i=0; i<plan_altitude.size(); i++)	{
				this.getCourbes().add(plan_altitude.get(i));
			}
		}
	}
	
	public List<Point> pointAltitude(double altitude)	{
		/**
		 * Méthode renvoyant tout les points du MNT qui ont une altitude donnée.
		 * Cette méthode crée aussi les points intermédiaires capables d'avoir l'altitude demandée
		 * 
		 * @param altitude: altitude souhaitée
		 * @return liste de points de même altitude que celle donnée en entrée
		 * 
		 * @see ProjetMNT.Point.Point
		 * @see ProjetMNT.Point.plusProche
		 */
		List<Point> liste_return = new ArrayList<Point>();
		for(int i=0; i<this.points.size(); i++)	{	//On parcours l'ensemble des points du MNT
			List<Point> liste_copie  = new ArrayList<Point>();
			List<Point> liste_buffer = new ArrayList<Point>();
			Point point_actuel       = this.points.get(i);
			System.out.println(this.points.size() - i);	//Affiche un décompte pour voir l'avancement de la fonction
			for(int j=0; j<this.points.size(); j++)	{	//On crée une copie des points du MNT,
														//suceptibles de nous donner un point intermédiaire avec point_actuel,
														//afin pouvoir effectuer des suppressions dessus sans modifier le MNT
				liste_copie.add(this.points.get(j));
			}
			liste_copie.remove(point_actuel);	//On ne veut pas rajouter point_actuel à la liste
			while(liste_buffer.size()<3 && liste_copie.size() != 0)	{	//Le nombre 3 est arbitraire.
				Point voisin = Point.plusProche(point_actuel, liste_copie);
				liste_copie.remove(voisin);
				liste_buffer.add(voisin);			
			}
			for(int l=0; l<liste_buffer.size(); l++)	{
				Point variable_tampon = new Point(point_actuel, liste_buffer.get(l), altitude);	//Point intermédiaire ayant l'altitude souhaitée
				//Mettre les points créés dans la List return
				if(variable_tampon.getZ() != 9999)	{	//Sinon, ça veut dire que le point est une erreur
					liste_return.add(variable_tampon);
				}
			}
		}
		return liste_return;
	}
	
	
	public static void main(String[] args) {
		Point point1 = new Point(0,0,0);
		Point point2 = new Point(0,1,5);
		Point point3 = new Point(0,2,10);
		Point point4 = new Point(1,0,2);
		Point point5 = new Point(1,1,4);
		Point point6 = new Point(1,2,8);
		Point point7 = new Point(2,0,1);
		Point point8 = new Point(2,1,3);
		Point point9 = new Point(2,2,6);
		List<Point> grille =  new ArrayList<Point>();
		grille.add(point1);
		grille.add(point2);
		grille.add(point3);
		grille.add(point4);
		grille.add(point5);
		grille.add(point6);
		grille.add(point7);
		grille.add(point8);
		grille.add(point9);
		MNT MNT1 = new MNT(grille);
		List<Point> exemple1 = MNT1.pointAltitude(9);
		System.out.println(exemple1);
		List<Point> exemple2 = MNT1.pointAltitude(7);
		System.out.println(exemple2);
	}

	

}
