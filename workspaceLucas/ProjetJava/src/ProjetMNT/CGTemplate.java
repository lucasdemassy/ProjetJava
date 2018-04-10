package ProjetMNT;

import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.util.ArrayList;
import java.util.List;
import ProjetMNT.PointAleatoire;


import javax.swing.*;    // Using Swing's components and containers
 
/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class CGTemplate extends JFrame {
   // Define constants
   public static final int CANVAS_WIDTH  = 640;
   public static final int CANVAS_HEIGHT = 480;
   
 
   // Declare an instance of the drawing canvas,
   // which is an inner class called DrawCanvas extending javax.swing.JPanel.
   private DrawCanvas canvas;
 
   // Constructor to set up the GUI components and event handlers
   public CGTemplate() {
      canvas = new DrawCanvas();    // Construct the drawing canvas
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // Set the Drawing JPanel as the JFrame's content-pane
      Container cp = getContentPane();
      cp.add(canvas);
      // or "setContentPane(canvas);"
 
      setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
      pack();              // Either pack() the components; or setSize()
      setTitle("......");  // "super" JFrame sets the title
      setVisible(true);    // "super" JFrame show
   }
 
   /**
    * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
    */
   private class DrawCanvas extends JPanel {

      // Override paintComponent to perform your own painting
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);     // paint parent's background
         setBackground(Color.BLACK);  // set background color for this JPanel
 
         // Your custom painting codes. For example,
         // Drawing primitive shapes
         g.setColor(Color.YELLOW);    // set the drawing color
         g.drawLine(30, 40, 100, 200);
         g.drawOval(150, 180, 10, 10);
         g.drawRect(200, 210, 20, 30);
         g.setColor(Color.RED);       // change the drawing color
         g.fillOval(300, 310, 30, 50);
         g.fillRect(400, 350, 60, 50);
         // Printing texts
         g.setColor(Color.WHITE);
         g.setFont(new Font("Monospaced", Font.PLAIN, 12));
         g.drawString("Testing custom drawing ...", 10, 20);
         
         List<Point> MNT1 = ProjetMNT.PointAleatoire.MNTAleatoire(300,400,10,0,100);
         g.setColor(Color.GREEN);
         for(int i=0; i<MNT1.size(); i++)	{
        	 g.drawLine((int)MNT1.get(i).getX(), (int)MNT1.get(i).getY(), (int)MNT1.get(i).getX()+1, (int)MNT1.get(i).getY()+1);
        	 System.out.println("Done");
        	 g.drawLine(80, 20, 50, 250);
         }
         
         Graphics2D g2d = (Graphics2D) g;
         int w = this.getWidth();
         int h = this.getHeight();
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
 		
 		
      }
      
   }
 
   // The entry main method
   public static void main(String[] args) {
      // Run the GUI codes on the Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new CGTemplate(); // Let the constructor do the job
         }
      });
      
      	Point point1 = new Point(0, 0, 0);
      	Point point2 = new Point(0, 1, 5);
		Point point3 = new Point(0, 2, 10);
		Point point4 = new Point(1, 0, 2);
		Point point5 = new Point(1, 1, 4);
		Point point6 = new Point(1, 2, 8);
		Point point7 = new Point(2, 0, 1);
		Point point8 = new Point(2, 1, 3);
		Point point9 = new Point(2, 2, 6);
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
   }


}

