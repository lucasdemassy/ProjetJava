package ProjetMNT;
import ProjetMNT.Point;
import java.util.ArrayList;
import java.util.List;

public class MNT {
	private List<Point> points;
	private List<Courbe> courbes;
	private List<Point> bord;
	
	public MNT(List<Point> points)	{
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
	
	public List<Point> getBord() {
		return bord;
	}

	public void setBord(List<Point> bord) {
		this.bord = bord;
	}
	
	public List<Point> pointAltitude(double altitude)	{
		System.out.println("Début");	//Debug
		System.out.println("Taille initiale: " + this.points.size());
		List<Point> liste_return = new ArrayList<Point>();
		
		//parcours de la liste de points du MNT
		for(int i=0; i<this.points.size(); i++)	{	//On parcours l'ensemble des points du MNT
			//crÃ©ation buffer
			List<Point> liste_copie   = new ArrayList<Point>();
			for(int j=0; j<this.points.size(); j++)	{	//On crée une copie de l'ensemble des points du MNT, pour pouvoir effectuer des suppressions dessus sans modifier le MNT
				liste_copie.add(this.points.get(j));
			}
			List<Point> liste_buffer  = new ArrayList<Point>();
			Point point_actuel = this.points.get(i);
			liste_copie.remove(point_actuel);	//On ne veut pas rajouter point_actuel Ã  la liste
			while(liste_buffer.size()<4)	{	//////////////////////////A ajuster pour avoir des points intermÃ©diaires en diagonale !!!!!!!!!!!!!
				System.out.println(liste_copie.size());
				Point voisin = Point.plusProche(point_actuel, liste_copie);
				liste_copie.remove(voisin);
				liste_buffer.add(voisin);
			}
			for(int l=0; l<liste_buffer.size(); l++)	{
				//point intermediaire avec les 8 points et le Point centre du buffer
				Point variable_tampon = new Point(point_actuel, liste_buffer.get(l), altitude);
				//
				//Je n'ai pas rÃ©ussi Ã  utiliser la fonction pointIntermediaire, j'ai dÃ» crÃ©er un constructeur qui fait pareil que la fonction pointIntermediaire
				//
				//Mettre les points créés dans la List return
				if(variable_tampon.getZ() != 9999)	{	//Sinon, ça veut dire que le point est une erreur
					liste_return.add(variable_tampon);
					System.out.println("Ajout final du point de coordonnées: " + variable_tampon.getX() + ", " + variable_tampon.getY() +" Ã  partir des points: " + point_actuel.getX() + ", " + point_actuel.getY() + " et " + liste_buffer.get(l).getX() + ", " + liste_buffer.get(l).getY());
				}
			}
		}
		for(int m=0; m<liste_return.size(); m++)	{
			for(int n=m; n<liste_return.size(); n++)	{
				if(liste_return.get(m).getX() == liste_return.get(n).getX() && liste_return.get(m).getY() == liste_return.get(n).getY()) {
					liste_return.remove(n);
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
