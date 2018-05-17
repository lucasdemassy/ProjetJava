package Dessin3D;

import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 

//Le script nécessite d'installer préalablement les librairies :
// java média
import javax.media.opengl.GLCapabilities; 
import javax.media.opengl.GLProfile; 
import javax.media.opengl.awt.GLCanvas; 
import javax.swing.JFrame; 

import com.jogamp.opengl.util.FPSAnimator; 


public class MainFrame extends JFrame { 

	/**
	 * 
	 * @author AZAROUAL_BRES
	 * @param null
	 * @return animation de l'objet circulaire
	 * 
	 *
	 */
    public MainFrame(){ 
        setTitle("Lit Sphere Demo, with JOGL"); 
        setSize(640, 480); 

        GLCapabilities glCapabilities = new GLCapabilities(GLProfile.getDefault()); 
        GLCanvas glCanvas = new GLCanvas(glCapabilities); //Creation d'un canevas de dessin3D
        MyGLEventListener glListener = new MyGLEventListener(); 
        glCanvas.addGLEventListener(glListener); 
        add(glCanvas); 

        final FPSAnimator animator = new FPSAnimator(glCanvas, 60); 

        addWindowListener(new WindowAdapter() { 

            @Override 
            public void windowClosing(WindowEvent e) { 
                animator.stop(); 
                System.exit(0); 
            } 

        }); 

        animator.start(); 
    } 

    private static final long serialVersionUID = -1227038124975588633L; 

} 
