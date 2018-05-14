package Dessin2D;

import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.util.ArrayList;
import java.util.List;

import ProjetMNT.Courbe;
import ProjetMNT.MNT;
import ProjetMNT.PointAleatoire;
import ProjetMNT.Point;
import ProjetMNT.PointImport;



import javax.swing.*;    // Using Swing's components and containers
 
/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class Affichage2D extends JFrame {
   // Define constants
   public static final int CANVAS_WIDTH  = 640;
   public static final int CANVAS_HEIGHT = 480;
   MNTDessin MNT1;
  
   
   
   // Declare an instance of the drawing canvas,
   // which is an inner class called Dessin2D extending javax.swing.JPanel.
   private Dessin2D canvas;
 
   // Constructor to set up the GUI components and event handlers
   public Affichage2D(MNTDessin MNTDessin) {
	   this.MNT1 = MNTDessin;
      canvas = new Dessin2D();    // Construct the drawing canvas
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // Set the Drawing JPanel as the JFrame's content-pane
      Container cp = getContentPane();
      cp.add(canvas);
      // or "setContentPane(canvas);"
 
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
         super.paintComponent(g);     // paint parent's background
         setBackground(Color.BLACK);  // set background color for this JPanel
         /*
         // Your custom painting codes. For example,
         // Drawing primitive shapes
         g.setColor(Color.YELLOW);    // set the drawing color
         g.drawLine(30, 40, 100, 200);
         g.drawOval(150, 180, 10, 10);
         g.drawRect(200, 210, 20, 30);
         g.setColor(Color.RED);       // change the drawing color
         g.fillOval(300, 310, 60, 60);
         g.fillRect(400, 350, 60, 50);
         // Printing texts
         g.setColor(Color.WHITE);
         g.setFont(new Font("Monospaced", Font.PLAIN, 12));
         g.drawString("Testing custom drawing ...", 10, 20);
         */
         
         g.setColor(Color.YELLOW);
         for(int i=0; i<MNT1.getPoints().size(); i++)	{
        	 if (MNT1.getPoints().get(i).getZ() == 0)	{
        		 g.setColor(Color.BLUE);
        	 }
        	 /*
        	 if (MNT1.getPoints().get(i).getZ() < 50 && MNT1.getPoints().get(i).getZ() > 0)	{
        		 g.setColor(Color.GREEN);
        	 }
        	 if (MNT1.getPoints().get(i).getZ() >= 50 && MNT1.getPoints().get(i).getZ() < 100)	{
        		 g.setColor(Color.YELLOW);
        	 }
        	 if (MNT1.getPoints().get(i).getZ() > 100)	{
        		 g.setColor(Color.RED);
        	 }
        	 */
        	 else	{
        		 g.setColor(Color.getHSBColor(0, 0, (float) MNT1.getPoints().get(i).getZ() / (float) MNTDessin.zMax(MNT1.getPoints())));
        	 }
        	 g.fillOval((int)MNT1.getPoints().get(i).getX(), (int)MNT1.getPoints().get(i).getY(), 5, 5);
        	 //g.drawLine((int)MNT1.getPoints().get(i).getX(), (int)MNT1.getPoints().get(i).getY(), (int)MNT1.getPoints().get(i).getX()+1, (int)MNT1.getPoints().get(i).getY()+1);
        	 }
         g.setColor(Color.YELLOW);
         for(int i=0; i<MNT1.getCourbes().size(); i++)	{
        	 Courbe niveau = MNT1.getCourbes().get(i);
        	 for(int j=0; j<niveau.getPoints().size() - 1; j++)	{
        		 Graphics2D g2d = (Graphics2D) g;
        		 BasicStroke bs1 = new BasicStroke(5); // pinceau du contour : taille 5
         		 g2d.setStroke(bs1);
        		 g.drawLine((int)niveau.getPoints().get(j).getX(), (int)niveau.getPoints().get(j).getY(), (int)niveau.getPoints().get(j+1).getX(), (int)niveau.getPoints().get(j+1).getY());
        		 System.out.println("Point1: " + (int)niveau.getPoints().get(j).getX() +", "+ (int)niveau.getPoints().get(j).getY() +", "+ (int)niveau.getPoints().get(j).getZ() +" Point2: " + (int)niveau.getPoints().get(j+1).getX() + ", "+ (int)niveau.getPoints().get(j+1).getY() + ", "+ (int)niveau.getPoints().get(j+1).getZ());
        	 }
        	 
        	 
         }
         

         
         /*
         Graphics2D g2d = (Graphics2D) g;
         int w = this.getWidth();	//Je soupçonne que ça prend la largeur de la fenêtre 
         int h = this.getHeight();	//Je soupçonne que ça prend la hauteur de la fenêtre 
         int sizeW = 50; // largeur du rectangle qu’on va afficher
 		 int sizeh = 30; // hauteur du rectangle qu’on va afficher
 		 g2d.setPaint(Color.black); // couleur de l’interieur
 		 g2d.fillRect(w / 2 - sizeW / 2, h / 2 - sizeh / 2, sizeW, sizeh); // dessin
 																			// de
 																			// l’interieur
 		 g2d.setPaint(Color.red); // couleur du contour

 		 BasicStroke bs1 = new BasicStroke(5); // pinceau du contour : taille 5
 		 g2d.setStroke(bs1);

 		 g2d.drawRect(w / 2 - sizeW / 2, h / 2 - sizeh / 2, sizeW, sizeh); // dessin
 																			// du
 																			// contour
 		*/
 		
      }
      
   }
 
   // The entry main method
   public static void main(String[] args) {
	   /*
	   List<Point> points = ProjetMNT.PointAleatoire.MNTAleatoire(300,400,10,0,100);
	   MNT MNT1 = new ProjetMNT.MNT(points);
	   List<Point> point30 = MNT1.pointAltitude(30);
	   Courbe courbe = new Courbe(point30, MNT1);
	   MNT1.getCourbes().add(courbe);
	   */
      
      
      	Point point1 = new Point(0, 0, 0);
      	Point point2 = new Point(0, 100, 0);
		Point point3 = new Point(0, 200, 0);
		Point point4 = new Point(100, 0, 0);
		Point point5 = new Point(100, 100, 10);
		Point point6 = new Point(100, 200, 0);
		Point point7 = new Point(200, 0, 0);
		Point point8 = new Point(200, 100, 0);
		Point point9 = new Point(200, 200, 0);
		List<Point> grille = new ArrayList<Point>();
		grille.add(point1);
		grille.add(point2);
		grille.add(point3);
		grille.add(point4);
		grille.add(point5);
		grille.add(point6);
		grille.add(point7);
		grille.add(point8);
		grille.add(point9);
		MNT MNT2 = new MNT(grille);
		List<Point> points9 = MNT2.pointAltitude(9);
		List<Courbe> courbes9 = ProjetMNT.Courbe.planAltimetrique(points9, MNT2);
		MNT2.setCourbes(courbes9);
		MNTDessin MNT2Dessin = new MNTDessin(MNT2);
		
		/*
		List<Point> liste =PointImport. MNTImport("/home/prof/Bureau/ProjetJava-master/Reunion_MNT250_ASC/DEPT974.asc");
		//List<Point> liste =PointImport. MNTImport("C:\\Users\\lucas\\Downloads\\Reunion_MNT250_ASC\\DEPT974.asc");
		MNT MNT3 = new MNT(liste);
		List<Point> points150 = MNT3.pointAltitude(150);
		List<Courbe> courbes150 = ProjetMNT.Courbe.planAltimetrique(points150, MNT3);
		MNT3.setCourbes(courbes150);
		MNTDessin MNT3Dessin = new MNTDessin(MNT3, 90);
	    System.out.println(MNT3Dessin.getPoints().get(0).toString());
	    System.out.println(MNT3Dessin.getPoints().get(1).toString());
	    */
		
	    /*
	    for(int i=0; i<MNT2.getCourbes().get(0).getPoints().size(); i++)	{
	    	System.out.println(MNT2.getCourbes().get(0).getPoints().get(i));
	    }
	    */
	    
	   
	    
		Point point01 = new Point(0,0,5);
		Point point02 = new Point(0,100,5);
		Point point03 = new Point(0,200,10);
		Point point04 = new Point(100,0,5);
		Point point05 = new Point(100,100,0);
		Point point06 = new Point(100,200,5);
		Point point07 = new Point(200,0,10);
		Point point08 = new Point(200,100,5);
		Point point09 = new Point(200,200,10);
		List<Point> grille0 =  new ArrayList<Point>();
		grille0.add(point01);
		grille0.add(point02);
		grille0.add(point03);
		grille0.add(point04);
		grille0.add(point05);
		grille0.add(point06);
		grille0.add(point07);
		grille0.add(point08);
		grille0.add(point09);
		MNT MNT01 = new MNT(grille0);
		List<Point> points = MNT01.pointAltitude(9);
		System.out.println("Taille points de MNT01: " + points.size());
		List<Courbe> courbes = ProjetMNT.Courbe.planAltimetrique(points, MNT01);
		MNT01.setCourbes(courbes);
		MNTDessin MNT01Dessin = new MNTDessin(MNT01);
	    System.out.println("Nombre de courbes: " + MNT01Dessin.getCourbes().size());
	    for(int i=0; i<courbes.size(); i++)	{
			Courbe courbe_actuelle = courbes.get(i);
			System.out.println("Courbe numéro " + i);
			System.out.println("Nombre de points: " + courbe_actuelle.getPoints().size());
			for(int j=0; j<courbe_actuelle.getPoints().size(); j++)	{
				System.out.println(courbe_actuelle.getPoints().get(j).toString());
			}
		}
		
		// Run the GUI codes on the Event-Dispatching thread for thread safety
	    SwingUtilities.invokeLater(new Runnable() {
	    	@Override
	    	public void run() {
	    		new Affichage2D(MNT01Dessin); // Let the constructor do the job
	        }
	    });
		
	    
   }


}

