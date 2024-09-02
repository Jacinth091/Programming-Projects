package Rem;


import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene{

    private String vertexShaderSrc = "#version 330 core\n" +
            "\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main(){\n" +
            "\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "}";
    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main(){\n" +
            "    color = fColor;\n" +
            "}";
    private int vertexID, fragmentID, shaderProgram;
    private int vaoID, vboID, eboID;

    private float[] vertexArray = {
            // position                     // color
             0.5f, -0.5f, 0.0f,               1.0f, 0.0f, 0.0f, 1.0f,  // Bottom Right
            -0.5f,  0.5f, 0.0f,               0.0f, 1.0f, 0.0f, 1.0f,  // Top Left
             0.5f,  0.5f, 0.0f,               0.5f, 0.0f, 1.9f, 1.0f,  // Top Right
            -0.5f, -0.5f, 0.0f,               1.0f, 1.0f, 0.5f, 1.0f,  // Bottom Left

    };

    private int[] elementArray ={


            2, 1, 0, // Top Right Triangle
            0, 1, 3 // Bottom Left Triangle
    };

    public LevelEditorScene(){

    }
    @Override
    public void init(){

        // Compile and Link Shaders


        // Load and Compile Vertex SHader
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        // Pass the shader source to gpu
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);

        // Check for errors in compilation
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'default.glsl'\n\tVertex Shader compilation failed!");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }


        // Load and Compile Vertex SHader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        // Pass the shader source to gpu
        glShaderSource(fragmentID, fragmentShaderSrc);
        glCompileShader(fragmentID);

        // Check for errors in compilation
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'default.glsl'\n\tFragment Shader compilation failed!");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

        // Link Shaders and Check for Errors
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        // Check for Linking Errors

        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if(success == GL_FALSE){
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'default.glsl'\n\tLinking of Shaders failed!");
            System.out.println(glGetProgramInfoLog(shaderProgram, len));
            assert false : "";
        }

        // Generate VAO, VBO, and EBO buffer objects,  and send to the gpu

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // Create a floatBuffer of indices
        FloatBuffer vertexbuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexbuffer.put(vertexArray).flip();

        // Create a VBO upload the vertex buffer
        vboID= glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vboID );
        glBufferData(GL_ARRAY_BUFFER, vertexbuffer, GL_STATIC_DRAW);


        // Create the indices upload

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        // Create EBO upload

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // add the attribute vertex pointers

        int positionSize = 3;
        int colorSize = 4;
        int floatSizeInBytes =4;
        int vertexSizeInBytes = (positionSize + colorSize) * floatSizeInBytes;

        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeInBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeInBytes, positionSize * floatSizeInBytes);
        glEnableVertexAttribArray(1);

    }
    @Override
    public void update(double dt) {
        // Bind the shader program

        glUseProgram(shaderProgram);

        // Bind the VAO that we're using

        glBindVertexArray(vaoID);

        // Enable the vertex attrib pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        // Unbind everything == free some memory?
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        glUseProgram(0);

    }
}
