package ProjetMNT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Courbe {
	private List<Point> points;	//Liste de points triés 
								//de sorte que l'on puisse dessiner la courbe en suivant l'ordre de cette liste
	private MNT modele;
	
	public Courbe(List<Point> points, MNT modele)	{
		this.modele = modele;
		this.points = points;
	}
	
	public static List<Courbe> planAltimetrique(List<Point> points, MNT modele)	{
		/**Le but de cette méthode est de différencier plusieurs courbes de niveau de même altitude à partir d'un nuage de point,
		 * Tout d'abord il faut trier la liste donnée en argument, 
		 * de sorte que l'ordre de la liste corresponde à l'ordre de dessin 
		 * (c'est à dire dessiner la courbe sans lever le crayon).
		 * Puis on parcourt cette liste triée, et plus particulièrement les distances entre chaque points juxtaposés dans la liste,
		 * Si un
		 * 
		 * 
		 */
		int taille_liste = points.size();
		if(taille_liste == 0)	{
			System.out.println("Taille nulle de la liste de point en entrée");
			return null;
		}
		List<Point> liste_triee = new ArrayList<Point>();
		List<Courbe> liste_return = new ArrayList<Courbe>();
		List<Point> partie1 = new ArrayList<Point>();
		List<Point> partie2 = new ArrayList<Point>();
		boolean continuer = true;
		liste_triee.add(points.get(0));
		points.remove(0);
		List<Double> liste_distance = new ArrayList<Double>();
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
				i --;
			}
		}
		double somme = 0;
		for(int i=0; i<liste_triee.size() - 1; i++)	{
			double distance = Point.distance(liste_triee.get(i), liste_triee.get(i+1));
			liste_distance.add(distance);
			somme += liste_distance.get(i);
		}
		double moyenne = somme/liste_distance.size();
		System.out.println("Distance moyenne = " + moyenne);
		double momentCentreOrdre2 = 0;
		for(int j=0; j<liste_distance.size(); j++)	{
			momentCentreOrdre2 += Math.pow(liste_distance.get(j) - moyenne, 2);
		}
		double variance = momentCentreOrdre2 / liste_distance.size();
		double ecartType = Math.sqrt(variance);
		continuer = true;
		for(int k=0; k<liste_distance.size(); k++)	{
			if(continuer)	{	//Tant qu'on a pas rencontré une distance n'étant pas d'ans l'intervalle de confiance à 95%
				
				if(liste_distance.get(k) >= 1.5*(liste_distance.get(0) + liste_distance.get(liste_distance.size() - 1)))	{
					continuer = false;
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
		if(continuer)	{	//Si pas de distance trop grande rencontrée
			partie1.add(liste_triee.get(liste_triee.size() -1));
			liste_return.add(new Courbe(partie1, modele));
		}
		else	{
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
	
	public List<Point> getPoints() {
		return points;
	}
	

	public static void main(String[] args) {	//Debug
		List<Integer> liste = new ArrayList<Integer>();
		liste.add(10);
		liste.add(20);
		liste.add(30);
		liste.add(40);
		Integer nombre = 20;
		
		for(int i=0; i<liste.size(); i++)	{
			if(liste.get(i) == 20)	{
				liste.remove(i);
			}
		}
		for(int i=0; i<liste.size(); i++)	{
			System.out.println(liste.get(i));
		}
		System.out.println("Fini1");
		System.out.println(liste.get(liste.size()-1));
		
		List<Integer> liste2 = new ArrayList<Integer>();
		liste2.add(50);
		liste2.add(60);
		liste2.add(70);
		liste2.add(80);
		
		
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
