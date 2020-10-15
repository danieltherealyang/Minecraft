package Engine;

import Utility.FileUtils;

import static org.lwjgl.opengl.GL20.*;

public class Shaders {
    public int compileVertex(String filePath) {
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        String vertexSource = FileUtils.readFile(filePath);
        glShaderSource(vertexShader, vertexSource);
        glCompileShader(vertexShader);
        checkCompileStatus(vertexShader);

        return vertexShader;
    }

    public int compileFragment(String filePath) {
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        String fragmentSource = FileUtils.readFile(filePath);
        glShaderSource(fragmentShader, fragmentSource);
        glCompileShader(fragmentShader);
        checkCompileStatus(fragmentShader);

        return fragmentShader;
    }

    public int createProgram(int vertex, int fragment) {
        int shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertex);
        glAttachShader(shaderProgram, fragment);
        glLinkProgram(shaderProgram);
        checkLinkStatus(shaderProgram);

        return shaderProgram;
    }

    protected void checkCompileStatus(int shader) {
        int status = glGetShaderi(shader, GL_COMPILE_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(shader));
        }
    }

    protected void checkLinkStatus(int program) {
        int status = glGetProgrami(program, GL_LINK_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetProgramInfoLog(program));
        }
    }
}
