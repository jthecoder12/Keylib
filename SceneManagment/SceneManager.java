package org.indiumstudios.keylib.SceneManagment;

import java.util.ArrayList;

@SuppressWarnings("GDXJavaStaticResource")
public final class SceneManager {
    private static final ArrayList<Scene> scenes = new ArrayList<>();

    private SceneManager() {}

    /**
     * The current scene.
     */
    public static Scene currentScene;

    static Scene firstScene;

    static boolean imGuiInitialized = false;

    /**
     * Adds a scene.
     * @param scene The scene you want to add.
     * @return The scene returned is returned for efficiency.
     */
    public static Scene addScene(Scene scene) {
        scenes.add(scene);
        return scene;
    }

    /**
     * Sets up the scene.
     */
    public static void setupScene() {
        currentScene.setup();
    }

    /**
     * Renders the scene.
     */
    public static void renderScene() {
        currentScene.render();
    }

    /**
     * Resize the scene required by libGDX.
     * @param width The width argument of the resize libGDX function.
     * @param height The height argument of the resize libGDX function.
     * @param centerCamera Whether the camera is centered or not.
     */
    public static void resizeScene(int width, int height, boolean centerCamera) {
        currentScene.stage.getViewport().update(width, height, centerCamera);
    }

    /**
     * Retunrs the ArrayList of scenes.
     * @return The scenes ArrayList.
     */
    public static ArrayList<Scene> getScenes() {
        return scenes;
    }
}
