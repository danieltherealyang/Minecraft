package Engine;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Timer {
    private int fps;
    private int fpsCount;
    private int ups;
    private int upsCount;
    double lastFrame;
    double timeElapsed;
    public float getDelta() {
        double time = getTime();
        float delta = (float) (time - lastFrame);
        lastFrame = time;
        timeElapsed += delta;
        return delta;
    }

    public double getTime() {
        return glfwGetTime();
    }

    public void updateFPS() {
        fpsCount++;
    }

    public void updateUPS() {
        upsCount++;
    }

    public void update() {
        if (timeElapsed > 1) {
            fps = fpsCount;
            fpsCount = 0;
            ups = upsCount;
            upsCount = 0;
            timeElapsed -= 1;
        }
    }
}
