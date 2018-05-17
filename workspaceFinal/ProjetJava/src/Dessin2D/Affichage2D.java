package Dessin2D;

import java.awt.*;       // Using AWT's Graphics and Color
import java.util.List;

import ProjetMNT.Courbe;
import ProjetMNT.MNT;
import ProjetMNT.Point;
import ProjetMNT.PointImport;



import javax.swing.*;    // Using Swing's components and containers
 
/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
@SuppressWarnings("serial")
public class Affichage2D extends JFrame {
   // Define constants
   public static final int CANVAS_WIDTH  = 840;
   public static final int CANVAS_HEIGHT = 680;
   private MNTDessin MNT1;
   private List<Color> couleur;
   // Declare an instance of the drawing canvas,
   // which is an inner class called Dessin2D extending javax.swing.JPanel.
   private Dessin2D canvas;
 
   // Constructor to set up the GUI components and event handlers
   public Affichage2D(MNTDessin MNTDessin, List<Color> couleur) {
	   /**
	    * Constructeur de la fenêtre d'affichage
	    * 
	    * @param MNTDessin: MNT qui sera affiché
	    * @param couleur: Liste des couleurs que devra prendre les courbes de niveaux
	    * 
	    */
	  this.MNT1 = MNTDessin;
	  this.couleur = couleur;
      canvas = new Dessin2D();    // Construct the drawing canvas
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // Set the Drawing JPanel as the JFrame's content-pane
      Container cp = getContentPane();
      cp.add(canvas);
 
      setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
      pack();              // Either pack() the components; or setSize()
      setTitle("Fenêtre d'affichage");  // "super" JFrame sets the title
      setVisible(true);    // "super" JFrame show
   }
 
   /**
    * Define inner class Dessin2D, which is a JPanel used for custom drawing.
    */
   private class Dessin2D extends JPanel {
      // Override paintComponent to perform your own painting
      @Override
      public void paintComponent(Graphics g) {
    	  /**
    	   * Méthode permettant de dessiner
    	   * 
    	   * @param g: Graphics
    	   */
         super.paintComponent(g);     // paint parent's background
         setBackground(Color.BLACK);  // set background color for this JPanel
         for(int i=0; i<MNT1.getPoints().size(); i++)	{
        	 if (MNT1.getPoints().get(i).getZ() == 0)	{
        		 g.setColor(Color.BLUE);	//On colorie en bleu les points au niveau de la mer
        	 }
        	 else	{	//Sinon, on les colorie en fonction de leur altitude 
        		 		//(noir est l'altitude minimale, et blanc est l'altitude maximale du MNT)
        		 g.setColor(Color.getHSBColor(0, 0, (float) MNT1.getPoints().get(i).getZ() / (float) MNTDessin.zMax(MNT1.getPoints())));
        	 }	//On dessine les points sous forme d'ellipse de petit et grand rayon identiques
        	 g.fillOval((int)MNT1.getPoints().get(i).getX(), (int)MNT1.getPoints().get(i).getY(), 5, 5);	
        	 }
         for(int i=0; i<MNT1.getCourbes().size(); i++)	{	//On parcourt l'ensemble des courbes du MNT, 
        	 												//ainsi que les couleurs qui leur sont associées
        	 Courbe niveau = MNT1.getCourbes().get(i);
        	 Color couleurCourbe = couleur.get(i);
        	 g.setColor(couleurCourbe);
        	 for(int j=0; j<niveau.getPoints().size() - 1; j++)	{
        		 Graphics2D g2d = (Graphics2D) g;
        		 if(couleurCourbe == Color.YELLOW)	{	//Si la courbe fait partie d'un ensemble de courbe avec un petit pas
        			 BasicStroke bs1 = new BasicStroke(1); // pinceau du contour : taille 1
        			 g2d.setStroke(bs1);
        		 }
        		 if(couleurCourbe == Color.RED)	{	//Si la courbe de niveau fait partie d'un ensemble de courbe avec un grand pas
        			 BasicStroke bs1 = new BasicStroke(3); // pinceau du contour : taille 3
        			 g2d.setStroke(bs1);
        		 }
        		 if(couleurCourbe == Color.GREEN)	{	//Si la courbe de niveau est construite à partir d'une altitude précise
        			 BasicStroke bs1 = new BasicStroke(2); // pinceau du contour : taille 2
        			 g2d.setStroke(bs1);
        		 }
        		 //La condition suivante élimine les aberrations visuelles car notre fonction pointAltimetrique n'est pas encore idéale
         		 if(ProjetMNT.Point.distance(niveau.getPoints().get(j), niveau.getPoints().get(j+1)) <= ProjetMNT.Point.distance(MNT1.getPoints().get(0), MNT1.getPoints().get(5)))	{
         			//Dessin d'une ligne
         			 g.drawLine((int)niveau.getPoints().get(j).getX(), (int)niveau.getPoints().get(j).getY(), (int)niveau.getPoints().get(j+1).getX(), (int)niveau.getPoints().get(j+1).getY());
         		 }
        	 }        	 
         } 		
      }
   }
 
   public static void main(String[] args) {
		//List<Point> liste =PointImport. MNTImport("/home/prof/Bureau/ProjetJava-master/Reunion_MNT250_ASC/DEPT974.asc");
		//List<Point> liste =PointImport. MNTImport("C:\\Users\\lucas\\Downloads\\Guyane_MNT250_ASC\\DEPT973.asc");
		List<Point> liste =PointImport. MNTImport("C:\\Users\\lucas\\Downloads\\Reunion_MNT250_ASC\\DEPT974.asc");
		MNT MNT3 = new MNT(liste);
		List<Point> points150 = MNT3.pointAltitude(300);
		List<Courbe> courbes150 = ProjetMNT.Courbe.planAltimetrique(points150, MNT3);
		MNT3.setCourbes(courbes150);
		//MNT3.CourbePas(1500);
		System.out.println("Nombre de courbe à 150m de l'île de la réunion = " + MNT3.getCourbes().size());
		MNTDessin MNT3Dessin = new MNTDessin(MNT3, 90);
	    System.out.println(MNT3Dessin.getPoints().get(0).toString());
	    System.out.println(MNT3Dessin.getPoints().get(1).toString());
	    
   }


}

