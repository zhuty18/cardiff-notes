//import java.awt.event.InputEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
import java.nio.FloatBuffer;

import javax.swing.JFrame;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.event.InputEvent;
import com.jogamp.newt.event.MouseListener;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import static com.jogamp.opengl.GL3.*;

public class VC02 {

	private GLWindow window; //Define a window
	final FPSAnimator animator=new FPSAnimator(60, true);
	final Renderer renderer = new Renderer();

	public VC02() {
		// Get OpenGL version 3 profile, and
		// enable the canvas use version 3
		GLProfile glp = GLProfile.get(GLProfile.GL3);
		GLCapabilities caps = new GLCapabilities(glp);
		window = GLWindow.create(caps);

		//Set the canvas to listen GLEvents from renderer
		window.addGLEventListener(renderer);
		//Set the canvas to listen mouse events from renderer
		window.addMouseListener(renderer);
		//window.addMouseMotionListener(renderer);

		// Animator act on canvas
		animator.add(window);

		window.setTitle("CG02");
		window.setSize(500,500);
		window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
		window.setVisible(true);

		animator.start();

	}

	public static void main(String[] args) {
		new VC02();
	}

	class Renderer implements GLEventListener, MouseListener {

		// Define a Transformation instance
		// Transformation matrix is initialised as Identity; 
		private Transform T = new Transform();

		//VAOs and VBOs parameters
		private int idPoint=0, numVAOs = 1;
		private int idBuffer=0, numVBOs = 1;
		private int[] VAOs = new int[numVAOs];
		private int[] VBOs = new int[numVBOs];

		//Model parameters
		private int numVertices = 36;
		private int vPosition;
		private int vColor;

		//Transformation parameters
		private int ModelView;
		private int Projection; 
		private float scale = 0.5f;
		private float scale_now = scale;
		private float tx = 0;
		private float ty = 0;
		private float rx = 0;
		private float ry = 0;
		
		//Mouse position
		private int xMouse = 0; 
		private int yMouse = 0;
		
		@Override
		public void display(GLAutoDrawable drawable) {
			GL3 gl = drawable.getGL().getGL3(); // Get the GL pipeline object this 
			
			gl.glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

			gl.glPointSize(5);                                                                                                                                                                                                                                                                                                                                                                                                  
			gl.glLineWidth(5);                                                                                                                                                                                                                                                                                                                                                                                                  

			// Setting polygon rendering mode
//			gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL); //default
//			gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
//			gl.glPolygonMode(GL_FRONT_AND_BACK, GL_POINT);

//			gl.glBindVertexArray(VAOs[idPoint]); //not necessary here

			// Load Identity matrix. This is necessary because function
			// display() will be called repeatedly, and each time it is 
			// called T is changed
			T.initialize();  

			//Mouse control interactive
			T.scale(scale, scale, scale);
			T.rotateX(rx);
			T.rotateY(ry);
			T.translate(tx, ty, 0);

			//set up the camera
//			T.lookAt(0, 0, 0, 0, 0, -100, 0, 1, 0); //default parameters
			T.lookAt(0, 0, 2, 0, 0, 0, 0, 1, 0);
			
			// Send model_view to shader. Here true for transpose 
			//means converting the row-major matrix to column major one,
			//which is required for pre-multiplication matrix 
		    gl.glUniformMatrix4fv( ModelView, 1, true, T.getTransformv(), 0 );			
			gl.glDrawArrays(GL_TRIANGLES, 0, numVertices);			
		}

		@Override
		public void dispose(GLAutoDrawable drawable) {
			System.exit(0);
		}

		@Override
		public void init(GLAutoDrawable drawable) {
			GL3 gl = drawable.getGL().getGL3(); // Get the GL pipeline object this 

			gl.glGenVertexArrays(numVAOs,VAOs,0);
			gl.glBindVertexArray(VAOs[idPoint]);
						
			float [] vertexArray = { //define triangles in anti-clockwise direction 
					// front face
					 1,  1,  1,    // triangle 1
					-1,  1,  1,
					-1, -1,  1,
					 1,  1,  1,    // triangle 2
					-1, -1,  1,
					 1, -1,  1,
					// back face
					 1,  1, -1,    // triangle 1
					-1, -1, -1,
					-1,  1, -1,
					 1,  1, -1,    // triangle 2
					 1, -1, -1,
					-1, -1, -1,
					// left face
					-1,  1,  1,    // triangle 1
					-1,  1, -1,
					-1, -1,  1,
					-1,  1, -1,    // triangle 2
					-1, -1, -1,
					-1, -1,  1,
					// right face
					 1,  1,  1,    // triangle 1
					 1, -1,  1,
					 1,  1, -1,
					 1,  1, -1,    // triangle 2
					 1, -1,  1,
					 1, -1, -1,
					// top face
					 1,  1,  1,    // triangle 1
					 1,  1, -1,
					-1,  1,  1,
					-1,  1,  1,    // triangle 2
					 1,  1, -1,
					-1,  1, -1,
					// bottom face
					 1, -1,  1,    // triangle 1
					-1, -1,  1,
					 1, -1, -1,
					-1, -1,  1,    // triangle 2
					-1, -1, -1,
					 1, -1, -1
					
			};
			


			//same color on each side
			float [] colorArray = {
					1, 0, 0,  //Front color
					1, 0, 0,
					1, 0, 0,
					1, 0, 0,
					1, 0, 0,
					1, 0, 0,
					0, 1, 0,  //Back color
					0, 1, 0,
					0, 1, 0,
					0, 1, 0,
					0, 1, 0,
					0, 1, 0,
					0, 0, 1,  //Left color
					0, 0, 1,
					0, 0, 1,
					0, 0, 1,
					0, 0, 1,
					0, 0, 1,
					1, 1, 0,  //Right color
					1, 1, 0,
					1, 1, 0,
					1, 1, 0,
					1, 1, 0,
					1, 1, 0,
					1, 0, 1,  //Top color
					1, 0, 1,
					1, 0, 1,
					1, 0, 1,
					1, 0, 1,
					1, 0, 1,
					0, 1, 1,  //Bottom color
					0, 1, 1,
					0, 1, 1,
					0, 1, 1,
					0, 1, 1,
					0, 1, 1,
					
			};

			FloatBuffer vertices = FloatBuffer.wrap(vertexArray);
			FloatBuffer colors = FloatBuffer.wrap(colorArray);
			
			gl.glGenBuffers(numVBOs, VBOs,0);
			gl.glBindBuffer(GL_ARRAY_BUFFER, VBOs[idBuffer]);

		    // Create an empty buffer with the size we need 
			// and a null pointer for the data values
			// pay attention to *Float.SIZE/8, 
			// which means the size unit is byte
			long vertexSize = vertexArray.length*(Float.SIZE/8);
			long colorSize = colorArray.length*(Float.SIZE/8);
			gl.glBufferData(GL_ARRAY_BUFFER, vertexSize +colorSize, 
					null, GL_STATIC_DRAW);
		    
			// Load the real data separately.  
			//We put the colors right after the vertex coordinates,
		    // so, the offset for colors is the size of vertices in bytes
		    gl.glBufferSubData( GL_ARRAY_BUFFER, 0, vertexSize, vertices );
		    gl.glBufferSubData( GL_ARRAY_BUFFER, vertexSize, colorSize, colors );

		    ShaderProg shaderproc = new ShaderProg(gl, "TransProj.vert", "TransProj.frag");
			int program = shaderproc.getProgram();
			gl.glUseProgram(program);
			
		   // Initialize the vertex position attribute in the vertex shader    
		    vPosition = gl.glGetAttribLocation( program, "vPosition" );
			gl.glEnableVertexAttribArray(vPosition);
			gl.glVertexAttribPointer(vPosition, 3, GL_FLOAT, false, 0, 0L);

		    // Initialize the vertex color attribute in the vertex shader.
		    // The offset is the same as in the glBufferSubData, i.e., vertexSize
			// It is the starting point of the color data
		    vColor = gl.glGetAttribLocation( program, "vColor" );
			gl.glEnableVertexAttribArray(vColor);
		    gl.glVertexAttribPointer(vColor, 3, GL_FLOAT, false, 0, vertexSize);

		    //Get connected with the ModelView matrix in the vertex shader
		    ModelView = gl.glGetUniformLocation(program, "ModelView");
		    Projection = gl.glGetUniformLocation(program, "Projection");

		    // This is necessary. Otherwise, the The color on back face may display 
//		    gl.glDepthFunc(GL_LESS);
		    gl.glEnable(GL_DEPTH_TEST);
		}
		
		@Override
		public void reshape(GLAutoDrawable drawable, int x, int y, int w,
				int h) {

			GL3 gl = drawable.getGL().getGL3(); // Get the GL pipeline object this 

			gl.glViewport(x, y, w, h);
//			gl.glViewport(x+w/2, y+h/2, w/2, h/2);

			T.initialize();

			//projection
//			T.ortho(-1, 1, -1, 1, -1, 1);  //Default
			// to avoid shape distortion because of reshaping the viewport
			// the viewport aspect should be the same as the projection aspect
			if(h<1){h=1;}
			if(w<1){w=1;}			
			float a = (float) w/ h;   //aspect 
///*
			// start of orthographic projection 
			if (w < h) {
//				T.ortho(-1, 1, -1/a, 1/a, -1, 1);
				T.frustum(-1, 1, -1/a, 1/a, 0.5f, 1000);
			}
			else{
//				T.ortho(-1*a, 1*a, -1, 1, -1, 1);
				T.frustum(-1*a, 1*a, -1, 1, 0.5f, 1000);
			}
			// end of orthographic projection 
//*/

			T.perspective(60, a, 0.1f, 1000);
			
			// Convert right-hand to left-hand coordinate system
			T.reverseZ();
		    gl.glUniformMatrix4fv( Projection, 1, true, T.getTransformv(), 0 );			

		}
		@Override
		public void mouseClicked(com.jogamp.newt.event.MouseEvent mouseEvent) {

		}

		@Override
		public void mouseEntered(com.jogamp.newt.event.MouseEvent mouseEvent) {

		}

		@Override
		public void mouseExited(com.jogamp.newt.event.MouseEvent mouseEvent) {

		}

		@Override
		public void mousePressed(com.jogamp.newt.event.MouseEvent mouseEvent) {

		}

		@Override
		public void mouseReleased(com.jogamp.newt.event.MouseEvent mouseEvent) {
			scale_now=scale;
		}

		@Override
		public void mouseMoved(com.jogamp.newt.event.MouseEvent mouseEvent) {
			xMouse = mouseEvent.getX();
			yMouse = mouseEvent.getY();

		}

		@Override
		public void mouseDragged(com.jogamp.newt.event.MouseEvent mouseEvent) {
			int x = mouseEvent.getX();
			int y = mouseEvent.getY();

			//left button down, move the object
			if((mouseEvent.getModifiers() & InputEvent.BUTTON1_MASK) != 0){
				// Fill in code here, so that the object moves
				// in the same direction as the mouse motion.
				tx=(float)((x-xMouse)*0.005);
				ty=(float) -((y-yMouse)*0.005);
			}

			//right button down, rotate the object
			if((mouseEvent.getModifiers() & InputEvent.BUTTON3_MASK) != 0){
				// To mimic using the mouse to rotate the object, we want that
				// when the mouse moves in horizontal (x) direction,
				// the object will rotate around the vertical (y) axis
				ry += (x-xMouse); // the rotation angle around the y axis

				// Add code here to calculate the rotation angle around the x axis
				rx-=(y-yMouse);

				xMouse = x;
				yMouse = y;
			}

			//middle button down, scale the object
			if((mouseEvent.getModifiers() & InputEvent.BUTTON2_MASK) != 0){
				//Add code here so that the object will scale down (shrink)
				// when the mouse moves up (y increases),
				// and it will scale up (expand) when the mouse moves down.
				scale=scale_now+(float)((y-yMouse)*0.001);
			}

		}

		@Override
		public void mouseWheelMoved(com.jogamp.newt.event.MouseEvent mouseEvent) {

		}
	}
}
