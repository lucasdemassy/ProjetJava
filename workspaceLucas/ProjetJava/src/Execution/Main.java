package Execution;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import Dessin2D.Affichage2D;
import Dessin2D.MNTDessin;
import ProjetMNT.PointAleatoire;
import ProjetMNT.PointImport;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ProjetMNT.Courbe;
import ProjetMNT.MNT;
import ProjetMNT.Point;

public class Main {
	 public static void main(String[] args) {
		System.out.println("Bienvenue dans l'afficheur de MNT");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		List<Color> listeCouleur = new ArrayList<Color>();
		try	{
			System.out.println("Voulez vous importer un MNT ? [true] "
					+ "Ou en créer un ? [false] ");
			boolean typeMNT = in.nextBoolean();
			List<Point> points = new ArrayList<Point>();
			if(typeMNT)	{
				boolean fini = false;
				while(fini == false)	{
					System.out.println("Chemin absolu du fichier d'import au format asc ?");
					String chemin = in.next();
					points = PointImport.MNTImport(chemin);
					if(points.size() != 0)	{
						fini = true;
					}
				}
			}
			
			else	{				
				System.out.println("Abscisse maximale du MNT ? (Utilisez la virgule au lieu du point pour toutes valeurs décimales)");
				double longueur = in.nextDouble();
				System.out.println("Ordonnée maximale du MNT ?");
				double largeur = in.nextDouble();
				System.out.println("Pas du MNT ? (Il déterminera le nombre de point du MNT tel que: (abscisseMax * ordonnéeMax)/pas )");
				System.out.println("Il est conseillé de ne pas dépasser 40000 points");
				double pas = in.nextDouble();
				System.out.println("Altitude minimale du MNT ?");
				double altitudeMin = in.nextDouble();
				System.out.println("Altitude maximale du MNT ? (pas au dessus ou égale à 9999)");
				double altitudeMax = in.nextDouble();
				points = PointAleatoire.MNTAleatoire(longueur, largeur, pas, altitudeMin, altitudeMax);
			}
			
			MNT MNT = new MNT(points);
			boolean continuer = true;
			double facteur;
			while(continuer)	{
				System.out.println("Affichage du MNT");
				System.out.println("Quel facteur d'échelle voulez-vous ? (rétécissement si facteur > 1, agrandissement si facteur < 1)");
				facteur = in.nextDouble();
				System.out.println("Génération de l'affichage, veuillez patienter");
				System.out.println("Ne pas fermer la fenêtre d'affichage sous peine d'arrêter le script");
				MNTDessin MNTDessin = new MNTDessin(MNT, facteur);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new Affichage2D(MNTDessin, listeCouleur); // Let the constructor do the job
					}
				});
				System.out.println("Voulez-vous changer le facteur d'échelle ? [true] [false]");
				continuer = in.nextBoolean();
			}
			
			System.out.println("Voulez construire une courbe de niveau à une altitude donnée ? [true] [false]");
			boolean courbeAltitude = in.nextBoolean();
			if(courbeAltitude)	{
				System.out.println("Quelle altitude souhaitez-vous ? (Un décompte vous indiquera l'avancée du calcul)");
				double altitude = in.nextDouble();
				List<Point> pointsAltitude = MNT.pointAltitude(altitude);
				List<Courbe> courbes = Courbe.planAltimetrique(pointsAltitude, MNT);
				MNT.setCourbes(courbes);
				for(int i=0; i<courbes.size(); i++)	{
					listeCouleur.add(Color.GREEN);
				}
			}
	
			System.out.println("Voulez construire des courbes de niveaux à partir d'un pas ? [true] [false]");
			boolean courbePas = in.nextBoolean();
			if(courbePas)	{
				System.out.println("Veuillez enter deux pas différents (attention un petit pas entraînera un temps de calcul très long !!)");
				System.out.println("Premier (et petit) pas");
				double pas1 = in.nextDouble();
				int tailleListeCouleurAvantPas1 = MNT.getCourbes().size();
				MNT.CourbePas(pas1);
				int tailleListeCouleurApresPas1 = MNT.getCourbes().size();
				for(int i=0; i<tailleListeCouleurApresPas1 - tailleListeCouleurAvantPas1; i++)	{
					listeCouleur.add(Color.YELLOW);
				}
				System.out.println("Voulez-vous toujours entrer un second pas ? [true] [false]");
				boolean courbePas2 =in.nextBoolean();
				if(courbePas2)	{
					System.out.println("Second (et grand) pas");
					double pas2 = in.nextDouble();
					int tailleListeCouleurAvantPas2 = MNT.getCourbes().size();
					MNT.CourbePas(pas2);
					int tailleListeCouleurApresPas2 = MNT.getCourbes().size();
					for(int i=0; i<tailleListeCouleurApresPas2 - tailleListeCouleurAvantPas2; i++)	{
						listeCouleur.add(Color.RED);
					}
				}
			}
			
			continuer = true;
			while(continuer)	{
				System.out.println("Affichage du MNT");
				System.out.println("Quel facteur d'échelle voulez-vous ? (rétécissement si facteur > 1, agrandissement si facteur < 1)");
				facteur = in.nextDouble();
				System.out.println("Génération de l'affichage, veuillez patienter");
				System.out.println("Ne pas fermer la fenêtre d'affichage sous peine d'arrêter le script");
				MNTDessin MNTDessin = new MNTDessin(MNT, facteur);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new Affichage2D(MNTDessin, listeCouleur); // Let the constructor do the job
					}
				});
				System.out.println("Voulez-vous changer le facteur d'échelle ? [true] [false]");
				continuer = in.nextBoolean();
			}
			System.out.println("Merci d'avoir utiliser l'afficheur de MNT by AZAROUAL&BRES");
	 } catch(Exception e)	{
		 System.out.println("Faute de frappe, veuillez relancer le script");
	 }
	 }

}
