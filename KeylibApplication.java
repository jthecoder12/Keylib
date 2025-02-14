package org.indiumstudios.keylib;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.indiumstudios.keylib.SceneManagment.Scene;
import org.indiumstudios.keylib.SceneManagment.SceneManager;
import org.indiumstudios.keylib.rendering.UI.ImGuiUI;

import static org.lwjgl.util.nfd.NativeFileDialog.NFD_Init;
import static org.lwjgl.util.nfd.NativeFileDialog.NFD_Quit;

public abstract class KeylibApplication extends ApplicationAdapter {
    /**
     * This tells whether the camera is centered or not.
     */
    protected boolean centerCamera = true;

    @Override
    public void resize(int width, int height) {
        SceneManager.resizeScene(width, height, centerCamera);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SceneManager.renderScene();

        extraRendering();
    }

    @Override
    public void create() {
        NFD_Init();
        extraCreate();
    }

    /**
     * Disposes everything created by Keylib.
     */
    protected void disposeAll() {
        for(Scene scene : SceneManager.getScenes()) scene.destroyScene();
        ImGuiUI.dispose();
        NFD_Quit();
    }

    /**
     * Any extra rendering that may be needed.
     */
    protected void extraRendering() {

    }

    /**
     * Any extra startup related things that may be needed.
     */
    protected void extraCreate() {

    }
}
