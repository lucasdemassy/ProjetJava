package ProjetMNT;

import java.util.List;

public class MNT3D extends MNT{

	public MNT3D(List<Point> points) {
		super(points);
		// TODO Auto-generated constructor stub
	}
	
	public static double Facto(double n)	{
		if(n == 1 || n == 0)	{
			return 1;
		}
		else	{
			return n * Facto(n-1); 
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Facto(4));
	}

}
