package ProjetMNT;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class MyPanel extends JPanel {

	// private List<Point> grille;

	@Override
	public void paint(Graphics arg0) {
		Graphics2D g = (Graphics2D) arg0;
		g.setColor(new Color(100, 100, 100));
		g.fill3DRect(10, 10, 100, 100, true);

	}

	public static void fenetre() {
		JFrame fenetre = new JFrame();
		fenetre.setTitle("Ma première fenêtre Java");
		fenetre.setSize(400, 100);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

		MyPanel.fenetre();

	}
}
