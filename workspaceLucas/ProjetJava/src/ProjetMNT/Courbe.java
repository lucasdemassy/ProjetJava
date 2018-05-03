package ProjetMNT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Courbe {
	private List<Point> points;	//Liste de points triés 
								//de sorte que l'on puisse dessiner la courbe en suivant l'ordre de cette liste
	private boolean ouverte;
	private MNT modele;
	
	public Courbe(List<Point> points, MNT modele)	{
		/*Le but de ce constructeur est de trier la liste donnée en argument 
		*de sorte que l'ordre de la liste corresponde à l'ordre de dessin 
		*(c'est à dire dessiner la courbe sans lever le crayon)
		*/
		this.modele  = modele;
		this.ouverte = false;
		int taille_liste = points.size();
		List<Point> liste_triee = new ArrayList<Point>();	//Liste finale triée
		int i = 0;	//Initialisation d'un compteur pour la boucle suivante
		while(liste_triee.size() == 0 || i <= points.size())	{	//On ne veut pas que la condition 
																	//qu'un point soit en limite de MNT soit réalisée deux fois
			if(modele.getBord().contains(points.get(i)))	{	//Si un point est en limite de MNT
				liste_triee.add(points.get(i));		//On le choisit comme début de la liste triée
				points.remove(i);	//Et on le supprime de la liste pour ne pas le re-choisir
				this.ouverte = true;	//Si on a un point en limite de MNT alors la courbe est ouverte.
										//Cela suppose aussi que le dernier point de la liste triée sera aussi en limite de MNT
			}
			i++;
		}
		while(liste_triee.size() < taille_liste)	{
			double minimum = (double) Integer.MAX_VALUE;	//Initialisation de la distance minimale
			int index      = 0;	//Variable qui garde en mémoire l'emplacement dans la liste
								//du point le plus proche du dernier point de la liste triée
			for(int j=0; j<points.size(); j++)	{	//Parcours de la liste à triée
				double distance = Math.sqrt(Math.pow(liste_triee.get(liste_triee.size()).getX() - points.get(j).getX(), 2) 
						+ Math.pow(liste_triee.get(liste_triee.size()).getY() - points.get(j).getY(), 2));
				if( distance < minimum)	{ //Distance euclidienne
					minimum = distance;
					index = j;
				}
			}
			liste_triee.add(points.get(index));	//On choisit ce point 
												//car étant le plus proche du dernier point de la liste triée
			points.remove(index);	////Et on le supprime de la liste pour ne pas le re-choisir
		}
		if(this.ouverte)	{
			//Ne rien faire
		}
		else	{	//Si la courbe est fermée, on rajoute le premier point à la fin de la liste 
					//(ce sera plus pratique pour dessiner)
			liste_triee.add(liste_triee.get(0));
		}
		this.points = liste_triee;
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
		
	}

	

}
