import java.nio.FloatBuffer;

import javax.swing.JFrame;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import static com.jogamp.opengl.GL3.*;

public class Simple implements GLEventListener {
	private static final long serialVersionUID = 1L;
	private GLWindow window; 	; // Declare a canvas
	final FPSAnimator animator=new FPSAnimator(60, true);

	private int idPoint = 0, numVAOs = 1;
	private int idBuffer = 0, numVBOs = 1;
	private int vPosition = 0;

	private final int numVertices = 1;

	private int[] VAOs = new int[numVAOs];
	private int[] VBOs = new int[numVBOs];
	private String[] srcVShader = 
		{ "#version 330 core \n"
	    + "layout(location = 0) in vec4 vPosition;" 
		+ "void main()" 
	    + "{"
		+ "	gl_Position = vPosition;" 
		+ "}" };

	private String[] srcFShader = 
		{ "#version 330 core\n" 
		+ "out vec4 fColor;"
		+ "void main()" 
		+ "{" 
		+ "	fColor = vec4(1.0, 0.0, 0.0, 1.0);" 
		+ "}" };

	
	public Simple() {
        GLProfile glp = GLProfile.get(GLProfile.GL3);
        GLCapabilities caps = new GLCapabilities(glp);
        window = GLWindow.create(caps);
		animator.add(window);

		window.addGLEventListener(this); // Listen for openGL events

		window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE); // Exit when click close
		window.setSize(500, 500); // set the window size
		window.setTitle("Simple Graphics"); // window title
		window.setVisible(true); // Display the frame
		animator.start();
	}

	public static void main(String[] args) {
		new Simple();

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3(); // Get the GL pipeline object
		gl.glClear(GL_COLOR_BUFFER_BIT);
		gl.glPointSize(5);
//	    gl.glLineWidth(5);
//	    gl.glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
		gl.glDrawArrays(GL_POINTS, 0, numVertices);		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		System.exit(0);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3(); // Get the GL pipeline object this

		float[] vertexArray = { 0.0f, 0.0f };
		//wrap the vertex array into a FloatBuffer.
		FloatBuffer vertices = FloatBuffer.wrap(vertexArray); 

		// Generate vertex array objects (VAOs), and
		// Bind a VAO, which means this VAO is initialised.
		// A second binding is needed later to use it
		gl.glGenVertexArrays(numVAOs, VAOs, 0);
		gl.glBindVertexArray(VAOs[idPoint]);

		// Generate vertex buffer objects (VBOs), and
		// Bind a VBO, which means this VBO is initialised.
		// The Data is then pooled into the buffer
		gl.glGenBuffers(numVBOs, VBOs, 0);
		gl.glBindBuffer(GL_ARRAY_BUFFER, VBOs[idBuffer]);
		gl.glBufferData(GL_ARRAY_BUFFER, vertexArray.length * (Float.SIZE / 8),
				vertices, GL_STATIC_DRAW);

		//glVertexAttribPointer
		//(int index, int size, int type,
	 	// boolean normalized, int stride,
		// long offset); 
		gl.glVertexAttribPointer(vPosition, 2, GL_FLOAT, false, 0, 0L);
		gl.glEnableVertexAttribArray(vPosition);

		// Create a shader program
		int program = gl.glCreateProgram();

		// Compile and attach vertex shader into the program
		int shader = gl.glCreateShader(GL_VERTEX_SHADER);
		gl.glShaderSource(shader, 1, srcVShader, null);
		gl.glCompileShader(shader);
		gl.glAttachShader(program, shader);
		gl.glDeleteShader(shader);

		// Compile and attach fragment shader into the program
		shader = gl.glCreateShader(GL_FRAGMENT_SHADER);
		gl.glShaderSource(shader, 1, srcFShader, null);
		gl.glCompileShader(shader);
		gl.glAttachShader(program, shader);
		gl.glDeleteShader(shader);

		// Link and use the shader program
		gl.glLinkProgram(program);
		gl.glUseProgram(program);		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

}
