package Rem;


import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.opengl.GL;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Window {

    private int height, width;
    private String title;

    private long glfwWindow;
    private static Window window = null;

    private float r,g,b,a;

    private boolean fadeToBlack = false;
    private Window(){
        this.height = 1080;
        this.width = 1920;
        this.title = "Mario Proj";
        r = 1;
        g = 1;
        b =1;
        a =1;
    }

    public static Window get(){
        if(Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    public void run(){
        System.out.println("Hello LWJGL " + Version.getVersion() + "!.");

        init();
        loop();

        // Free the memory after the window has closed
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();


    }

    public void init(){
        // Setup an error callback. The default implementation
        // will print the error message in System.err

        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }


        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);


        // Create Window

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL){
            throw new IllegalStateException("Failed to create the GLFW Window.");
        }

        // SEt up Listener Callbacks
        // for mouse position
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);

        // for mouse buttons
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);

        // for mouse scroll
        glfwSetScrollCallback(glfwWindow, MouseListener::scrollCallback);

        // for Key Inputs
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        // OpenGL Context current

        glfwMakeContextCurrent(glfwWindow);
        // Enable V- sync
        glfwSwapInterval(1);
        // show Window/ make the window visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

    }

    public void loop(){
        while(!glfwWindowShouldClose(glfwWindow)){
            // Poll Events

            glfwPollEvents();

            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (fadeToBlack) {

                r = Math.max(r - 0.01f, 0);
                b = Math.max(b - 0.01f, 0);
                g = Math.max(g - 0.01f, 0);
                a = Math.max(a - 0.01f, 0);
            }

            if(KeyListener.getIsKeyPressed(GLFW_KEY_SPACE)){
                System.out.println("Space Key is PRESSED!!!!");
                fadeToBlack = true;
            }

            glfwSwapBuffers(glfwWindow);
        }
    }
}
