package org.indiumstudios.keylib.rendering.colors;

import com.badlogic.gdx.graphics.Color;
import org.lwjgl.system.NativeType;

public interface RGBc {
    @NativeType("GLclampf") float getRed();
    @NativeType("GLclampf") float getGreen();
    @NativeType("GLclampf") float getBlue();
    @SuppressWarnings("unused")
    void setRed(@NativeType("GLclampf") float newRed);
    @SuppressWarnings("unused")
    void setGreen(@NativeType("GLclampf") float newGreen);
    @SuppressWarnings("unused")
    void setBlue(@NativeType("GLclampf") float newBlue);

    Color toGdxColor();
}
