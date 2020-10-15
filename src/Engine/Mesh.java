package Engine;

import Utility.MathUtils.*;
import org.lwjgl.system.MemoryStack;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Mesh {
    int floatSize = 4;
    int VAO;
    int vertexVBO;
    int textureVBO;
    int EBO;
    int vertexShader;
    int fragmentShader;
    int shaderProgram;
    int uniTransform;
    Shaders shaders;
    Texture texture;

    public Mesh(float[] vertices, float[] texCoords, int[] indices) {
        //Object Initialization
        shaders = new Shaders();
        texture = new Texture();
        //VAO
        VAO = glGenVertexArrays();
        glBindVertexArray(VAO);

        //vertexVBO
        try (MemoryStack verticesStack = MemoryStack.stackPush()) {
            FloatBuffer verticesBuffer = verticesStack.mallocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();
            vertexVBO = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vertexVBO);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            //vertices
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * floatSize, 0);
            glEnableVertexAttribArray(0);
            //colors
            glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * floatSize, 3 * floatSize);
            glEnableVertexAttribArray(1);
        }

        //EBO
        try (MemoryStack indicesStack = MemoryStack.stackPush()) {
            IntBuffer indicesBuffer = indicesStack.mallocInt(indices.length);
            indicesBuffer.put(indices).flip();
            EBO = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        }

        //Textures
        try {
            texture.createTexture("resources/Textures/grass_block.png");
            int texID = texture.getTexture();
            glBindTexture(GL_TEXTURE_2D, texID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (MemoryStack textureStack = MemoryStack.stackPush()) {
            FloatBuffer textureBuffer = textureStack.mallocFloat(texCoords.length);
            textureBuffer.put(texCoords).flip();
            textureVBO = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, textureVBO);
            glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
            //textures
            glVertexAttribPointer(2, 2, GL_FLOAT, false, 2 * floatSize, 0);
            glEnableVertexAttribArray(2);
        }

        //Shaders
        vertexShader = shaders.compileVertex("resources/Shaders/vertex.vs");
        fragmentShader = shaders.compileFragment("resources/Shaders/fragment.fs");
        shaderProgram = shaders.createProgram(vertexShader, fragmentShader);
    }

    public void draw() {
        glUseProgram(shaderProgram);
        glBindVertexArray(VAO);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
    }

    public void end() {
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }
}
