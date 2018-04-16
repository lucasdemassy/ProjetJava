package ProjetMNT;
import ProjetMNT.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class MNT {
	private List<Point> points;
	private List<Courbe> courbes;
	
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
	
	public List<Point> pointAltitude(double altitude)	{
		System.out.println("Début");	//Debug
		List<Point> liste_return = new ArrayList<Point>();
		//parcours de la liste de points du MNT
		for(int i=0; i<this.points.size(); i++)	{
			//création buffer
			double rayon              = 0;
			List<Point> liste_buffer  = new ArrayList<Point>();
			List<Integer> liste_index = new ArrayList<Integer>();
			Point point_actuel = this.points.get(i);
			//Agrandissement du buffer jusqu'à rencontrer 8 points (cercle, sphère, ou carré ?)
			while(liste_buffer.size()<1)	{	//////////////////////////A ajuster pour avoir des points intermédiaires en diagonale !!!!!!!!!!!!!
				rayon += 0.5;	//Agrandissement du buffer  //////////////////////////A ajuster en fonction de la distance entre les points du MNT
				for(int j=0; j<this.points.size(); j++)	{
					if(j != i)	{	//On ne veut pas rajouter point_actuel à la liste
						//Debug
						System.out.println("Point centre: " + point_actuel.getX() + ", " + point_actuel.getY() + " Point variable: " + this.points.get(j).getX() + ", " + this.points.get(j).getY() + " Rayon: " + rayon);
						if(Math.pow(this.points.get(j).getX() - point_actuel.getX(), 2) + Math.pow(this.points.get(j).getY() - point_actuel.getY(), 2) == Math.pow(rayon,  2))	{		//Buffer en forme de cercle
							boolean test_redondance = true;
							for(int k=0; k<liste_index.size(); k++)	{
								if(liste_index.get(k) == j)	{	//On vérifie que le point que l'on compte ajouter à liste_buffer n'y est pas déjà 
									test_redondance = false;
								}
							}
							if(test_redondance)	{	//Si ce n'est pas un point que l'on a déjà ajouter à liste_buffer
								liste_buffer.add(this.points.get(j));
								liste_index.add(j);
								System.out.println("Ajout du point " + j);  //Debug
							}
						}
					}
				}
			}
			for(int l=0; l<liste_buffer.size(); l++)	{
				//point intermediaire avec les 8 points et le Point centre du buffer
				Point variable_tampon = new Point(point_actuel, liste_buffer.get(l), altitude);
				//
				//Je n'ai pas réussi à utiliser la fonction pointIntermediaire, j'ai dû créer un constructeur qui fait pareil que la fonction pointIntermediaire
				//
				//Mettre les points créés dans la List return
				if(variable_tampon.getZ() != 9999)	{
					liste_return.add(variable_tampon);
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
		MNT2D MNT1 = new MNT2D(grille);
		List<Point> exemple1 = MNT1.pointAltitude(9);
		System.out.println(exemple1);
		List<Point> exemple2 = MNT1.pointAltitude(7);
		System.out.println(exemple2);
	}

}
