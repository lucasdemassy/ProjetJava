package Dessin2D;

import java.awt.Component;
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
		double xMin = xMin(modele.getPoints());
		double yMin = yMin(modele.getPoints());
		double distanceMin = distanceMin(modele.getPoints());
		List<Point> listePoints = new ArrayList<Point>();
		List<Courbe> listeCourbes = new ArrayList<Courbe>();
		for(int i=0; i<modele.getPoints().size(); i++)	{
			Point pointDessin = new Point(modele.getPoints().get(i).getX() - xMin - distanceMin,
					modele.getPoints().get(i).getY() - yMin - distanceMin,
					modele.getPoints().get(i).getZ());
			pointDessin.setX(pointDessin.getX() / facteur);
			pointDessin.setY(pointDessin.getY() / facteur);
			listePoints.add(pointDessin);
		}
		for(int i=0; i<modele.getCourbes().size(); i++)	{
			Courbe courbe = modele.getCourbes().get(i);
			List<Point> liste_tampon = new ArrayList<Point>();
			for(int j=0; j<courbe.getPoints().size(); j++)	{
				Point point = new Point(courbe.getPoints().get(j).getX() - xMin - distanceMin,
						courbe.getPoints().get(j).getY() - yMin - distanceMin,
						courbe.getPoints().get(j).getZ());
				point.setX(point.getX() / facteur);
				point.setY(point.getY() / facteur);
				liste_tampon.add(point);
			}
			listeCourbes.add(new Courbe(liste_tampon, modele));
		}
		this.setPoints(listePoints);
		this.setCourbes(listeCourbes);
	}
	
	public MNTDessin(MNT modele)	{
		setPoints(modele.getPoints());
		setCourbes(modele.getCourbes());
	}
	
	public static double xMin(List<Point> liste)	{
		double min = Integer.MAX_VALUE;
		for(int i=0; i<liste.size(); i++)	{
			if(liste.get(i).getX() < min)	{
				min = liste.get(i).getX();
			}
		}
		return min;
	}
	
	public static double yMin(List<Point> liste)	{
		double min = Integer.MAX_VALUE;
		for(int i=0; i<liste.size(); i++)	{
			if(liste.get(i).getY() < min)	{
				min = liste.get(i).getY();
			}
		}
		return min;
	}
	
	public static double zMax(List<Point> liste)	{
		double max = Integer.MIN_VALUE;
		for(int i=0; i<liste.size(); i++)	{
			if(liste.get(i).getZ() > max)	{
				max = liste.get(i).getZ();
			}
		}
		return max;
	}
	
	public double distanceMin(List<Point> liste)	{
		double min = Integer.MAX_VALUE;
		for(int i=0; i<liste.size(); i++)	{
			if(Point.distance(liste.get(i), Point.plusProche(liste.get(i), liste)) < min)	{
				min = Point.distance(liste.get(i), Point.plusProche(liste.get(i), liste));
			}
		}
		return min;
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
