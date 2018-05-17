package ProjetMNT;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PointImport extends Point{

	public PointImport(double x, double y, double z) {
		/**
		 * Constructeur hérité de la classe Point
		 * 
		 * @param x: coordonnée x du point
		 * @param y: coordonnée y du point
		 * @param z: coordonnée z du point
		 * 
		 * @see ProjetMNT.Point.Point
		 */
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}
	
	public static double StringToDouble(String texte) {
		/**
		 * Méthode de classe convertissant un nombre au format String en un nombre au format double
		 * 
		 * @param texte: Le format du texte ne contient uniquement que les signes "-" "." "," 
		 * @return nombre de type double
		 */
		char[] caractere = texte.toCharArray();
		double nombre = 0;	//Initialisation
		double virgule = 1;
		double presence_virgule = 0;
		double signe = 1;
		if((int) caractere[0] == 45)	{	//Si le nombre commence par "-" il est négatif
			signe = -1;
		}
		int nombreChiffre = texte.length();
		for(int i=0; i<nombreChiffre; i++)	{
			if((int) caractere[i] == 48)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 0 * signe / virgule;
			}
			if((int) caractere[i] == 49)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 1 * signe / virgule;
			}
			if((int) caractere[i] == 50)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 2 * signe / virgule;
			}
			if((int) caractere[i] == 51)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 3 * signe / virgule;
			}
			if((int) caractere[i] == 52)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 4 * signe / virgule;
			}
			if((int) caractere[i] == 53)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 5 * signe / virgule;
			}
			if((int) caractere[i] == 54)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 6 * signe / virgule;
			}
			if((int) caractere[i] == 55)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 7 * signe / virgule;
			}
			if((int) caractere[i] == 56)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 8 * signe / virgule;
			}
			if((int) caractere[i] == 57)	{
				nombre += Math.pow(10, nombreChiffre - i - 1 + presence_virgule) * 9 * signe / virgule;
			}
			if((int) caractere[i] == 46 || (int) caractere[i] == 44) {	//On rajoute la virgule ou le point à la fin
				virgule = Math.pow(10, nombreChiffre - i);
				nombre /= Math.pow(10, nombreChiffre - i);
				presence_virgule = 1;
			}
			
		}
	return nombre;
	}
	
	public static List<Point> MNTImport(String chemin)	{
		/**
		 * Méthode de classe permettant l'import d'un MNT à partir d'un fichier au format GRID ASCII
		 * 
		 * @param chemin: chemin absolu du fichier asc
		 * @return liste de point correspondant aux données du fichier. Cette liste servira à la création d'un futur MNT
		 * 
		 * @see ProjetMNT.PointImport.StringToDouble
		 */
		// Lecture d'un fichier 
		List<Point> liste = new ArrayList<Point>();
		String filePath = chemin; // chemin absolu vers le fichier
		Path p = Paths.get(filePath); // creation de l'objet Path
		double colonne = 0;
		double ligne =0;
		double x_origine = 0;
		double y_origine = 0;
		double pas = 0;
		double noData = 0;
		try (BufferedReader reader = Files.newBufferedReader(p)) { // ouverture d'un buffer en lecture
			String line;
			for(int j=0; j<6; j++)	{
				line = reader.readLine();
				//System.out.println(line);
				if(line.startsWith("ncols"))	{	//Correspond au nombre de colonne
					String[] substring = line.split(" ");
					colonne = StringToDouble(substring[substring.length-1]);
				}
				if(line.startsWith("nrows"))	{	//Correspond au nombre de ligne
					String[] substring = line.split(" ");
					ligne = StringToDouble(substring[substring.length-1]);
				}
				if(line.startsWith("xllcorner"))	{	//Correspond à l'abscisse minimale du MNT
					String[] substring = line.split(" ");
					x_origine = StringToDouble(substring[substring.length-1]);
				}
				if(line.startsWith("yllcorner"))	{	//Correspond à l'ordonnée minimale du MNT
					String[] substring = line.split(" ");
					y_origine = StringToDouble(substring[substring.length-1]);
				}
				if(line.startsWith("cellsize"))	{	//Correspond au pas du MNT
					String[] substring = line.split(" ");
					pas = StringToDouble(substring[substring.length-1]);
				}
				if(line.startsWith("NODATA_value"))	{	//Correspond à une donnée non-existante
					String[] substring = line.split(" ");
					noData = StringToDouble(substring[substring.length-1]);
				}
			}
			double y = y_origine;
			while ((line = reader.readLine()) != null) {	
				double x = x_origine;
				String[] substring = line.split(" ");
				for(int i=0; i<substring.length; i++)	{
					if(substring[i] != " ")	{
						if(StringToDouble(substring[i]) != noData)	{
							Point point = new Point(x, y, StringToDouble(substring[i]));	//Nécessité d'une conversion String --> double
							liste.add(point);
						}
						x += pas;	//Placé ici car même si noData, le pas avance
					}
				}
				y += pas;	//Placé ici car pas en y après lecture d'une ligne entière
			}
		
		} catch (IOException e)	{
			e.printStackTrace();
		}
		return liste;
	}
	
	public static void main(String[] args) {
		MNTImport("C:\\Users\\lucas\\Downloads\\Reunion_MNT250_ASC\\DEPT974.asc");
		System.out.println((int) ".".toCharArray()[0]);
		System.out.println((int) ",".toCharArray()[0]);
		System.out.println(StringToDouble("123,456789"));
		System.out.println(StringToDouble("-12345678,9"));
		System.out.println(StringToDouble("1,234"));
		
	}

}
