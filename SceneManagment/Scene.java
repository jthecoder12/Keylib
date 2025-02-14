package org.indiumstudios.keylib.SceneManagment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import org.indiumstudios.keylib.Debug;
import org.indiumstudios.keylib.Sound.Audio;
import org.indiumstudios.keylib.rendering.UI.ImGuiUI;
import org.indiumstudios.keylib.rendering.colors.RGBAc;
import org.indiumstudios.keylib.rendering.colors.RGBc;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Scene implements Disposable {
    protected ArrayList<Actor> objects = new ArrayList<>();
    protected ArrayList<Audio> music = new ArrayList<>();
    Stage stage;
    private boolean rendering = true;
    private boolean alreadyDisposed = false;

    void setup() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        if(!SceneManager.imGuiInitialized) {
            ImGuiUI.initImGui();
            SceneManager.imGuiInitialized = true;
        }

        extraSetup();

        for(Actor object:objects) {
            stage.addActor(object);
        }
    }

    void render() {
        if(rendering) {
            extraRendering();
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        renderImGui();
    }

    /**
     * Any extra setup that is needed.
     */
    protected void extraSetup() {}

    /**
     * Any extra rendering that is needed.
     */
    protected void extraRendering() {}

    /**
     * All ImGui rendering.
     */
    protected void renderImGui() {}

    /**
     * Sets this scene as the current scene.
     * @param firstScene Whether this is the first scene or not. First scenes must always be set current.
     */
    public void setAsCurrent(boolean firstScene) {
        if(firstScene) SceneManager.firstScene = this;
        else {
            SceneManager.currentScene.stopAllSounds();
            for(Audio music : SceneManager.currentScene.music) music.dispose();
        }

        SceneManager.currentScene = this;
        SceneManager.setupScene();
    }

    /**
     * Stop all the sounds.
     */
    protected void stopAllSounds() {
        for(Audio mus : music) if(mus != null) mus.stop();
    }

    /**
     * Sets the background color.
     * @param color An RGBA color for the background color: Red, Green, Blue, Alpha. Example for white: new RGBA(255, 255, 255 ,255).
     */
    protected void setBGColor(@NotNull RGBAc color) {
        Gdx.gl.glClearColor(color.getRed()/255f, color.getBlue()/255f, color.getGreen()/255f, color.getAlpha()/255f);
    }

    /**
     * Sets the background color.
     * @param color An RGB color for the background color: Red, Green, Blue. Example for white: new RGB(255, 255, 255).
     */
    @SuppressWarnings("unused")
    protected void setBGColor(@NotNull RGBc color) {
        Gdx.gl.glClearColor(color.getRed()/255f, color.getBlue()/255f, color.getGreen()/255f, 1);
    }

    /**
     * Updates the object list.
     */
    protected void updateObjects() {
        ArrayList<Actor> l = new ArrayList<>(objects);
        l.removeAll(Arrays.asList(stage.getActors().toArray()));

        for(Actor actor : l) {
            stage.addActor(actor);
        }
    }

    /**
     * Deletes all objects.
     */
    @SuppressWarnings("all")
    protected void deleteAllObjects() {
        objects = new ArrayList<>();

        for(Actor actor : stage.getActors()) {
            actor.remove();
        }
    }

    /**
     * Sets whether the scene other than ImGui is getting rendered.
     * @param isRendering Whether the scene is getting rendered.
     */
    protected void setIsRendering(boolean isRendering) {
        rendering = isRendering;
    }

    /**
     * Gets the stage's batch.
     * @return The stage's batch.
     */
    protected Batch getBatch() {
        return stage.getBatch();
    }

    /**
     * Dispose the scene.
     */
    @Override
    public void dispose() {
        stopAllSounds();
        for(Audio music : music) if(music != null) music.dispose();
    }

    /**
     * Destroys the scene's stage. Scene should not be used after.
     */
    public void destroyScene() {
        if(!alreadyDisposed) {
            Debug.log(String.format("Disposing the stage on %s", getClass().getCanonicalName()));
            stage.dispose();
        }
        alreadyDisposed = true;
        dispose();
    }
}
