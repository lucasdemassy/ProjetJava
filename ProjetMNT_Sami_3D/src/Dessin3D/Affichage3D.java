package Dessin3D;

//installer lalibrairie openGL
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;


	public class Affichage3D implements GLEventListener{

		@Override
		public void init(GLAutoDrawable drawable) {
			GL2 gl = drawable.getGL().getGL2();

			gl.glClearColor(0.8f, 0.5f, 0.0f, 1.0f);

			gl.glEnable(GL2.GL_DEPTH_TEST);
			gl.glClearDepth(1.0f);

			gl.glShadeModel(GL2.GL_SMOOTH);
			gl.glEnable(GL2.GL_LIGHTING);

			gl.glEnable(GL2.GL_LIGHT0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, light_0_ambient, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light_0_diffuse, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, light_0_specular, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_0_position, 0);

			gl.glEnable(GL2.GL_COLOR_MATERIAL);
			gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
			gl.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 90);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, material_specular, 0);
		}

		@Override
		public void dispose(GLAutoDrawable drawable) {
			// TODO Auto-generated method stub

		}

		@Override
		public void display(GLAutoDrawable drawable) {
			GL2 gl = drawable.getGL().getGL2();
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
			gl.glLoadIdentity();

			glu.gluLookAt(
					camera[0], camera[1], camera[2],
					0.0f, 0.0f, 0.0f,
					0.0f, 1.0f, 0.0f
			);

			gl.glRotatef(angle, 0f, 1f, 0f);

			gl.glColor3f(1.0f, 0.0f, 0.0f);
			glut.glutSolidSphere(1.0f, 20, 20);

			angle += 0.1f;
			angle %= 360f;
		}

		@Override
		public void reshape(GLAutoDrawable drawable, int x, int y, int width,
				int height) {
			GL2 gl = drawable.getGL().getGL2();
			gl.glViewport(x, y, width, height);

			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glLoadIdentity();
			glu.gluPerspective(60.0f, (float) width/height, 0.1f, 10.0f);

			gl.glMatrixMode(GL2.GL_MODELVIEW);
		}

		private GLU glu = new GLU();
		private GLUT glut = new GLUT();

		private float camera [] = {0f, 0f, 5f};

		private float [] light_0_ambient = {0.01f, 0.01f, 0.01f, 0.01f};
		private float [] light_0_diffuse = {1.0f, 1.0f, 1.0f, 1.0f};
		private float [] light_0_specular = {1.0f,1.0f, 1.0f, 1.0f};
		private float [] light_0_position = {100f, 0f, 10f, 1f};

		private float [] material_specular = {1.0f, 1.0f, 1.0f, 1.0f};

		private float angle = 0f;
	
}
