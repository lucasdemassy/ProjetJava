package ProjetMNT;

import java.util.ArrayList;
import java.util.List;

public class PointAleatoire extends Point{

	public PointAleatoire(double x, double y, double z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}
	
	public static List<Point> MNTAleatoire(double longueur, double largeur, double precision, double altitudeMin, double altitudeMax)	{
		// Faire gaffe aux points entiers (éviter les points décimaux)
		List<Point> grille = new ArrayList<Point>();
		double altitude;
		for (double i = 0; i <= largeur; i+=precision)	{
			for (double j = 0; j <= longueur; j+=precision)	{
				altitude = altitudeMin + Math.random()*(altitudeMax - altitudeMin);
				grille.add(new PointAleatoire(i,j,altitude));
			}
		}
		return grille;
	}
	
	public static void main(String[] args) {
		List<Point> MNT1 = MNTAleatoire(20,30,1,0,100);
		for(int i=0; i<MNT1.size(); i++)	{
			System.out.println(MNT1.get(i).getX() + ", " + MNT1.get(i).getY() + ", " + MNT1.get(i).getZ());
		}
	}

}
