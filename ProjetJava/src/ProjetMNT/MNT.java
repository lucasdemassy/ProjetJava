package ProjetMNT;

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
	
	

}
