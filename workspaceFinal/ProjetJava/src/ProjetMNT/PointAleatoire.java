package ProjetMNT;

import java.util.ArrayList;
import java.util.List;

public class PointAleatoire extends Point{

	public PointAleatoire(double x, double y, double z) {
		/**
		 * Constructeur hérité de la classe Point
		 * 
		 * @param x: coordonnée x du point
		 * @param y: coordonnée y du point
		 * @param z: coordonnée z du point
		 * 
		 * @see ProjetMNT.Point.Point
		 */
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}
	
	public static List<Point> MNTAleatoire(double longueur, double largeur, double pas, double altitudeMin, double altitudeMax)	{
		/**
		 * Méthode de classe créant une liste de point qui servira pour un MNT ayant une longueur, une largeur, et un pas.
		 * Les altitudes des points du MNT sont aléatoirement compris entre une altitude minimum et une altitude maximum
		 * Ce MNT sera considéré comme rectangulaire
		 * 
		 * @param longueur: valeur de l'abscisse maximale du MNT
		 * @param largeur: valeur de l'ordonnée maximale du MNT
		 * @param pas: distance entre chaque point du MNT
		 * @param altitudeMin: altitude minimale que peut atteindre un point du MNT
		 * @param altitudeMax: altitude maximalee que peut atteindre un point du MNT
		 * @return liste de point qui pourra être utiliser pour créer un MNT aléatoire
		 * 
		 */
		// Faire gaffe aux points entiers (éviter les points décimaux)
		List<Point> grille = new ArrayList<Point>();
		double altitude;
		for (double i = 0; i <= largeur; i+=pas)	{
			for (double j = 0; j <= longueur; j+=pas)	{
				altitude = altitudeMin + Math.random()*(altitudeMax - altitudeMin);
				grille.add(new PointAleatoire(i,j,altitude));
			}
		}
		return grille;
	}
	
	public static void main(String[] args) {
		List<Point> MNT1 = MNTAleatoire(10,200,1,0,100);
		for(int i=0; i<MNT1.size(); i++)	{
			System.out.println(MNT1.get(i).getX() + ", " + MNT1.get(i).getY() + ", " + MNT1.get(i).getZ());
		}
	}

}
