package ProjetMNT;

import java.util.ArrayList;
import java.util.List;

public class Courbe {
	private List<Point> points;	//Liste de points triés 
								//de sorte que l'on puisse dessiner la courbe en suivant l'ordre de cette liste
	private MNT modele;
	
	
	public List<Point> getPoints() {
		return points;
	}
	
	
	public Courbe(List<Point> points, MNT modele)	{
		/**
		 * Constructeur classique de la classe Courbe
		 * 
		 * @param points: liste de points de même altitude
		 * @param modele: MNT dont appartient la courbe de niveau
		 */
		this.modele = modele;
		this.points = points;
	}
	
	public static List<Courbe> planAltimetrique(List<Point> points, MNT modele)	{
		/**Le but de cette méthode est de différencier plusieurs courbes de niveau de même altitude à partir d'un nuage de point,
		 * Tout d'abord il faut trier la liste donnée en argument, 
		 * de sorte que l'ordre de la liste corresponde à l'ordre de dessin 
		 * (c'est à dire dessiner la courbe sans lever le crayon).
		 * Puis on parcourt cette liste triée, et plus particulièrement les distances entre chaque points juxtaposés dans la liste,
		 * Si une distance est trop grande (selon un critère arbitraire),
		 * On sépare la liste triée en deux. La première partie de la liste de point est convertie en Courbe
		 * tandis que l'on applique à la seconde partie la méthode planAltimetrique (c'est donc une méthode récursive)
		 * 
		 * @param points: nuage de point de même altitude
		 * @return liste de courbes distinctes de même altitude 
		 * 
		 * @see ProjetMNT.Point.plusProche
		 * @see ProjetMNT.Point.egal
		 * @see ProjetMNT.Point.distance
		 */
		int taille_liste = points.size();
		if(taille_liste == 0)	{
			System.out.println("Taille nulle de la liste de point en entrée");
			return null;
		}
		List<Point> liste_triee     = new ArrayList<Point>();	//Liste qui trie de proche en proche la liste d'entrée 
		List<Courbe> liste_return   = new ArrayList<Courbe>();	//Liste finale qui sera retourné par la méthode
		List<Point> partie1         = new ArrayList<Point>();	//Sous-liste de la liste triée
		List<Point> partie2         = new ArrayList<Point>();	//Sous-liste de la liste triée
		List<Double> liste_distance = new ArrayList<Double>();	//Liste qui contient les distances entre deux points voisins dans la liste triée
		liste_triee.add(points.get(0));	//On prend arbitrairement comme point de départ le premier élément de la liste d'entrée
		points.remove(0);	//Et on enlève ce même point pour ne pas le re-choisir
		while(liste_triee.size() < taille_liste)	{
			Point pointPlusProche = Point.plusProche(liste_triee.get(liste_triee.size() - 1), points);
			liste_triee.add(pointPlusProche);	//On choisit ce point 
												//car étant le plus proche du dernier point de la liste triée
			points.remove(pointPlusProche);	//Et on le supprime de la liste pour ne pas le re-choisir
		}
		//Elimination des doublons dans la liste triée
		for(int i=0; i<liste_triee.size() - 1; i++)	{
			if(liste_triee.get(i).egal(liste_triee.get(i+1)))	{
				liste_triee.remove(i);
				i --;	//Si un élément est supprimé, l'itérateur doit rester en place pour ne pas sauter de valeurs
			}
		}
		for(int i=0; i<liste_triee.size() - 1; i++)	{	//Ajout des distances, entre points voisins de la liste triée, dans une liste dédiée
			double distance = Point.distance(liste_triee.get(i), liste_triee.get(i+1));
			liste_distance.add(distance);
		}
		boolean continuer = true;
		for(int k=0; k<liste_distance.size(); k++)	{
			if(continuer)	{	//Tant qu'on a pas rencontré une distance anormalement grande
				if(liste_distance.get(k) >= 1.5*(liste_distance.get(0) + liste_distance.get(liste_distance.size() - 1)))	{	//Critère arbitraire
					continuer = false;	//Si on a rencontré une distance trop grande, on divise la liste triée en deux parties
					partie1.add(liste_triee.get(k));
				}
				else	{
					partie1.add(liste_triee.get(k));
				}
			}
			else	{
				partie2.add(liste_triee.get(k));
			}
		}
		if(continuer)	{	//Si pas de distance anormalement grande rencontrée
			partie1.add(liste_triee.get(liste_triee.size() -1));	//Cela veut dire que la liste triée ne contient qu'une courbe de niveau
			liste_return.add(new Courbe(partie1, modele));	
		}
		else	{	//Sinon cela veut dire que la première partie correspond à une courbe ou un morceau de courbe.
					//La seconde partie va être traiter récursivement par la méthode planAltimetrique
			partie2.add(liste_triee.get(liste_triee.size() - 1));
			Courbe courbe1 = new Courbe(partie1, modele);
			liste_return.add(courbe1);
			if(partie2.size() != 0)	{
				List<Courbe> courbes2 = planAltimetrique(partie2, modele);
				for(int i=0; i<courbes2.size(); i++)	{
					if(courbes2 == null)	{
						//Do nothing
					}
					else	{
						liste_return.add(courbes2.get(i));
					}
				}
			}
		}
		return liste_return;
	}
	
	
	
	public static void main(String[] args) {	//Debug	
		Point point1 = new Point(0,0,10);
		Point point2 = new Point(0,1,5);
		Point point3 = new Point(0,2,10);
		Point point4 = new Point(1,0,5);
		Point point5 = new Point(1,1,0);
		Point point6 = new Point(1,2,5);
		Point point7 = new Point(2,0,10);
		Point point8 = new Point(2,1,5);
		Point point9 = new Point(2,2,10);
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
		List<Point> points = MNT1.pointAltitude(9.5);
		List<Courbe> courbes = planAltimetrique(points, MNT1);
		System.out.println("Nombre de courbes: " + courbes.size());
		for(int i=0; i<courbes.size(); i++)	{
			Courbe courbe_actuelle = courbes.get(i);
			System.out.println("Courbe numéro: " + i);
			System.out.println("Nombre de points: " + courbe_actuelle.getPoints().size());
			for(int j=0; j<courbe_actuelle.getPoints().size(); j++)	{
				System.out.println(courbe_actuelle.getPoints().get(j).toString());
			}
		}
		MNT1.setCourbes(courbes);
	}

	

}
