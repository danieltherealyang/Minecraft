import Engine.GameEngine;

public class Main {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine("Minecraft", 600, 600);
        engine.run();
    }
}
