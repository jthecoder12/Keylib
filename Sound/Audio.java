package org.indiumstudios.keylib.Sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;

@SuppressWarnings("CallToPrintStackTrace")
public final class Audio implements Disposable {
    public enum SoundType {
        MUSIC,
        SOUND_EFFECT
    }

    private final SoundType type;
    private Music music;
    private Sound sound;
    private long id;
    private boolean isLooping;
    private float volume;

    /**
     * An audio clip whether it is music or a sound effect.
     * @param fileHandle The file handle (ex: Gdx.files.internal).
     * @param type The type of the sound. Music: SoundType.MUSIC, Sound Effect: SoundType.SOUND_EFFECT.
     * @param isLooping If the sound is looping or not.
     * @param volume The volume of the sound. Usually at 0.2 for normal volume
     */
    public Audio(FileHandle fileHandle, SoundType type, boolean isLooping, float volume) {
        this.type = type;

        if(type == SoundType.MUSIC) {
            try {
                music = Gdx.audio.newMusic(fileHandle);
                music.setLooping(isLooping);
                music.setVolume(volume);
            } catch(GdxRuntimeException e) {e.printStackTrace();}
        } else if(type == SoundType.SOUND_EFFECT) {
            try{
                sound = Gdx.audio.newSound(fileHandle);
                this.isLooping = isLooping;
                this.volume = volume;
            } catch(GdxRuntimeException e) {e.printStackTrace();}
        }
    }

    /**
     * Plays the sound.
     */
    public void play() {
        if(type == SoundType.SOUND_EFFECT) {
            try {
                id = sound.play(volume);
                sound.setLooping(id, isLooping);
            } catch (NullPointerException e) {e.printStackTrace();}
        }
        else if(type == SoundType.MUSIC) try {music.play();} catch (NullPointerException e){e.printStackTrace();}
    }

    /**
     * Stops the sound.
     */
    public void stop() {
        if(type == SoundType.SOUND_EFFECT) try {sound.stop(id);} catch (NullPointerException e) {e.printStackTrace();}
        else if(type == SoundType.MUSIC) try {music.stop();} catch (NullPointerException e) {e.printStackTrace();}
    }

    /**
     * Pauses the sound.
     */
    public void pause() {
        if(type == SoundType.SOUND_EFFECT) sound.pause(id);
        else if(type == SoundType.MUSIC) music.pause();
    }

    /**
     * Checks if the music is playing or not.
     * @return Returns if the music is playing or not.
     */
    @SuppressWarnings("unused")
    public boolean isPlaying() {
        return music.isPlaying();
    }

    /**
     * Disposes the sound. Do this when you don't need the sound anymore.
     */
    public void dispose() {
        if(type == SoundType.SOUND_EFFECT) try {sound.dispose();} catch (NullPointerException e) {e.printStackTrace();}
        else try {music.dispose();} catch (NullPointerException e) {e.printStackTrace();}
    }
}
