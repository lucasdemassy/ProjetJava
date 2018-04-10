package ProjetMNT;

import java.util.ArrayList;
import java.util.List;

public class MNT2D extends MNT{

	public MNT2D(List<Point> points) {
		super(points);
		// TODO Auto-generated constructor stub
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
		System.out.println(MNT1.getPoints());
		System.out.println(MNT1.getCourbes());
	}

}
