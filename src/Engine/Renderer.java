package Engine;

import Assets.Shapes3D;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    Mesh mesh;
    Shapes3D shapes;
    public void createMesh() {
        shapes = new Shapes3D();
        float[] pos = shapes.getCube(0, 0, 0, 1);
        float[] vertices = {
                // positions          // colors
                //front
                pos[0], pos[1], pos[2], 0f, 0f, 0f, //1
                pos[3], pos[4], pos[5], 0f, 0f, 0f, //2
                pos[6], pos[7], pos[8], 0f, 0f, 0f, //3
                pos[9], pos[10], pos[11], 0f, 0f, 0f, //4

                //back
                pos[12], pos[13], pos[14], 0f, 0f, 0f, //5
                pos[15], pos[16], pos[17], 0f, 0f, 0f, //6
                pos[18], pos[19], pos[20], 0f, 0f, 0f, //7
                pos[21], pos[22], pos[23], 0f, 0f, 0f, //8

                //left
                pos[12], pos[13], pos[14], 0f, 0f, 0f, //5
                pos[15], pos[16], pos[17], 0f, 0f, 0f, //6
                pos[3], pos[4], pos[5], 0f, 0f, 0f, //2
                pos[0], pos[1], pos[2], 0f, 0f, 0f, //1

                //right
                pos[9], pos[10], pos[11], 0f, 0f, 0f, //4
                pos[6], pos[7], pos[8], 0f, 0f, 0f, //3
                pos[18], pos[19], pos[20], 0f, 0f, 0f, //7
                pos[21], pos[22], pos[23], 0f, 0f, 0f, //8

                //top
                pos[12], pos[13], pos[14], 0f, 0f, 0f, //5
                pos[0], pos[1], pos[2], 0f, 0f, 0f, //1
                pos[9], pos[10], pos[11], 0f, 0f, 0f, //4
                pos[21], pos[22], pos[23], 0f, 0f, 0f, //8

                //bottom
                pos[3], pos[4], pos[5], 0f, 0f, 0f, //2
                pos[15], pos[16], pos[17], 0f, 0f, 0f, //6
                pos[18], pos[19], pos[20], 0f, 0f, 0f, //7
                pos[6], pos[7], pos[8], 0f, 0f, 0f, //3
        };

        float[] texCoords = {
                //front
                0.5f, 0.0f, //1
                0.5f, 0.5f, //2
                1.0f, 0.5f, //3
                1.0f, 0.0f, //4

                //back
                1.0f, 0.0f, //5
                1.0f, 0.5f, //6
                0.5f, 0.5f, //7
                0.5f, 0.0f, //8

                //left
                0.5f, 0.0f, //5
                0.5f, 0.5f, //6
                1.0f, 0.5f, //2
                1.0f, 0.0f, //1

                //right
                0.5f, 0.0f, //4
                0.5f, 0.5f, //3
                1.0f, 0.5f, //7
                1.0f, 0.0f, //8

                //top
                0.0f, 0.5f, //5
                0.0f, 1.0f, //1
                0.5f, 1.0f, //4
                0.5f, 0.5f, //8

                //bottom
                0.0f, 0.0f, //2
                0.0f, 0.5f, //6
                0.5f, 0.5f, //7
                0.5f, 0.0f  //3
        };

        int[] indices = {
                //front
                0, 1, 2, 0, 2, 3,
                //back
                4, 5, 6, 4, 6, 7,
                //left
                8, 9, 10, 8, 10, 11,
                //right
                12, 13, 14, 12, 14, 15,
                //top
                16, 17, 18, 16, 18, 19,
                //bottom
                20, 21, 22, 20, 22, 23
        };
        mesh = new Mesh(vertices, texCoords, indices);
    }

    public void render(Window window) {
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);
        mesh.draw();
        glfwSwapBuffers(window.getWindow());
        glfwPollEvents();
    }

    public void end() {
        mesh.end();
    }
}
