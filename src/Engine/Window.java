package Engine;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.GL_MIRRORED_REPEAT;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    long window;
    String title;
    int width;
    int height;
    boolean fullScreen;
    GLFWVidMode vidMode;
    GLFWFramebufferSizeCallback framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
        @Override
        public void invoke(long window, int w, int h) {
            if (!fullScreen) {
                width = w;
                height = h;
            }
            //glViewport(0,0, w, h);
        }
    };
    GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                glfwSetWindowShouldClose(window, true);
            }

            if (key == GLFW_KEY_F11 && action == GLFW_PRESS) {
                toggleFullscreen();
            }
        }
    };

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        glfwInit();
        vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        fullScreen = false;
    }

    public void createWindow() {
        setWindowHints();

        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create GLFW window");
        }

        glfwMakeContextCurrent(window);
        setCallbacks();
        GL.createCapabilities();
        //glViewport(0,0, width, height);
        activateSettings();
    }

    public void toggleFullscreen() {
        fullScreen = !fullScreen;
        if (!fullScreen) {
            glfwSetWindowMonitor(window, NULL, 0, 0, width, height, GLFW_REFRESH_RATE);
            glfwSetWindowPos(window,
                    (vidMode.width() - width) / 2,
                    (vidMode.height() - height) / 2
            );
        } else {
            glfwSetWindowMonitor(window, glfwGetPrimaryMonitor(), 0, 0, vidMode.width(), vidMode.height(), GLFW_REFRESH_RATE);
        }
    }

    public void activateSettings() {
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        //glEnable(GL_DEPTH_TEST);
    }

    public void setWindowHints() {
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
    }

    public void setCallbacks() {
        glfwSetFramebufferSizeCallback(window, framebufferSizeCallback);
        glfwSetKeyCallback(window, keyCallback);
    }

    public long getWindow() {
        return window;
    }

    public void end() {
        glfwTerminate();
    }
}
