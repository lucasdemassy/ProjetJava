package ProjetMNT;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyApplication extends JFrame{
	private void init()	{
		MyPanel panneau = new MyPanel(); // on instancie un nouveal objet MyPanel
		this.setContentPane(panneau); // on redefinit le conteneur principal de notre fenetre
		panneau.fenetre();
		
	}
	
	public static void main(String[] args) {
		MyApplication yo = new MyApplication();
		yo.init();
		
		
	}
	
}
