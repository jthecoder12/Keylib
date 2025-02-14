package org.indiumstudios.keylib.rendering.colors;

import org.lwjgl.system.NativeType;

public interface RGBAc extends RGBc {
    @NativeType("GLclampf") float getAlpha();
    @SuppressWarnings("unused")
    void setAlpha(@NativeType("GLclampf") float newAlpha);
}
