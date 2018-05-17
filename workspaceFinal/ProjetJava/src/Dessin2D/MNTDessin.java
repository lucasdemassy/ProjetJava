package Dessin2D;

import java.util.ArrayList;
import java.util.List;

import ProjetMNT.Courbe;
import ProjetMNT.MNT;
import ProjetMNT.Point;

public class MNTDessin {
	private List<Point> points;
	private List<Courbe> courbes;
	private List<Point> bord;
	
	public MNTDessin(MNT modele, double facteur)	{
		/**
		 * Constructeur, qui à partir d'un MNT et d'un facteur d'échelle, 
		 * instancie un MNT affichable correctement.
		 * On translate le MNT de façon à être à l'origine du repère d'affichage,
		 * et on réduit/augmente la distance entre les points du MNT
		 * 
		 * @param modele: MNT déjà existant et dôté d'un nuage de point et possiblement d'une liste de courbe
		 * @param facteur: facteur d'échelle. Rétécissement si facteur > 1. Agrandissement si facteur < 1
		 * 
		 * @see Dessin2D.MNTDessin.xMin
		 * @see Dessin2D.MNTDessin.yMin
		 */
		double xMin               = xMin(modele.getPoints());
		double yMin               = yMin(modele.getPoints());
		List<Point> listePoints   = new ArrayList<Point>();
		List<Courbe> listeCourbes = new ArrayList<Courbe>();
		for(int i=0; i<modele.getPoints().size(); i++)	{
			Point pointDessin = new Point(modele.getPoints().get(i).getX() - xMin,
					modele.getPoints().get(i).getY() - yMin,
					modele.getPoints().get(i).getZ());	//Translation des points jusqu'à l'origine
			pointDessin.setX(pointDessin.getX() / facteur);	//Application du facteur d'échelle
			pointDessin.setY(pointDessin.getY() / facteur);	//Application du facteur d'échelle
			listePoints.add(pointDessin);
		}
		for(int i=0; i<modele.getCourbes().size(); i++)	{
			Courbe courbe = modele.getCourbes().get(i);
			List<Point> liste_tampon = new ArrayList<Point>();
			for(int j=0; j<courbe.getPoints().size(); j++)	{
				Point point = new Point(courbe.getPoints().get(j).getX() - xMin,
						courbe.getPoints().get(j).getY() - yMin,
						courbe.getPoints().get(j).getZ());	//Translation des points jusqu'à l'origine
				point.setX(point.getX() / facteur);	//Application du facteur d'échelle
				point.setY(point.getY() / facteur);	//Application du facteur d'échelle
				liste_tampon.add(point);
			}
			listeCourbes.add(new Courbe(liste_tampon, modele));
		}
		this.setPoints(listePoints);
		this.setCourbes(listeCourbes);
	}
	
	public MNTDessin(MNT modele)	{
		/**
		 * Constructeur classique de MNTDessin à partir d'un MNT
		 * 
		 * @param modele: MNT déjà existant
		 */
		setPoints(modele.getPoints());
		setCourbes(modele.getCourbes());
	}
	
	public static double xMin(List<Point> liste)	{
		/**
		 * Méthode de classe déterminant l'abscisse minimale d'un nuage de point
		 * 
		 * @param liste: nuage de point
		 * @return abscisse minimale du nuage de point. De type double
		 */
		double min = Integer.MAX_VALUE;
		for(int i=0; i<liste.size(); i++)	{
			if(liste.get(i).getX() < min)	{
				min = liste.get(i).getX();
			}
		}
		return min;
	}
	
	public static double yMin(List<Point> liste)	{
		/**
		 * Méthode de classe déterminant l'ordonnée minimale d'un nuage de point
		 * 
		 * @param liste: nuage de point
		 * @return ordonnée minimale du nuage de point. De type double
		 */
		double min = Integer.MAX_VALUE;
		for(int i=0; i<liste.size(); i++)	{
			if(liste.get(i).getY() < min)	{
				min = liste.get(i).getY();
			}
		}
		return min;
	}
	
	public static double zMax(List<Point> liste)	{
		/**
		 * Méthode de classe déterminant l'altitude maximale d'un nuage de point
		 * 
		 * @param liste: nuage de point
		 * @return altitude maximale du nuage de point. De type double
		 */
		double max = Integer.MIN_VALUE;
		for(int i=0; i<liste.size(); i++)	{
			if(liste.get(i).getZ() > max)	{
				max = liste.get(i).getZ();
			}
		}
		return max;
	}

	public List<Point> getPoints() {
		// TODO Auto-generated method stub
		return points;
	}
	
	public void setPoints(List<Point> points)	{
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

}
