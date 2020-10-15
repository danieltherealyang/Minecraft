package Engine;

import static org.lwjgl.glfw.GLFW.*;

public class GameEngine implements Runnable{
    Window window;
    Renderer renderer;
    Timer timer;
    private final float targetUPS = 60f;
    public GameEngine(String title, int width, int height) {
        window = new Window(title, width, height);
        timer = new Timer();
        renderer = new Renderer();
    }

    @Override
    public void run() {
        window.createWindow();
        renderer.createMesh();
        gameLoop();
        end();
    }

    public void gameLoop() {
        while(!glfwWindowShouldClose(window.getWindow())) {
            renderer.render(window);
        }
    }

    public void end() {
        window.end();
        renderer.end();
    }
}
