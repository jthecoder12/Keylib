package org.indiumstudios.keylib.rendering.colors;

import com.badlogic.gdx.graphics.Color;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.NativeType;

public final class RGB implements RGBc {
    private float red, green, blue;

    /**
     * An RGB color.
     * @param red Red.
     * @param green Green.
     * @param blue Blue.
     */
    public RGB(@NativeType("GLclampf") float red, @NativeType("GLclampf") float green, @NativeType("GLclampf") float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Gets the value of red.
     * @return Returns the value of red.
     */
    @Override
    public @NativeType("GLclampf") float getRed() {
        return red;
    }

    /**
     * Gets the value of green.
     * @return Returns the value of green.
     */
    @Override
    public @NativeType("GLclampf") float getGreen() {
        return green;
    }

    /**
     * Gets the value of blue.
     * @return Returns the value of blue.
     */
    @Override
    public @NativeType("GLclampf") float getBlue() {
        return blue;
    }

    /**
     * Sets the value of red.
     * @param newRed The value to set.
     */
    @Override
    public void setRed(@NativeType("GLclampf") float newRed) {
        red = newRed;
    }

    /**
     * Sets the value of green.
     * @param newGreen The value to set.
     */
    @Override
    public void setGreen(@NativeType("GLclampf") float newGreen) {
        green = newGreen;
    }

    /**
     * Sets the value of blue.
     * @param newBlue The value to set.
     */
    @Override
    public void setBlue(@NativeType("GLclampf") float newBlue) {
        blue = newBlue;
    }

    @Contract(" -> new")
    @Override
    public @NotNull Color toGdxColor() {
        return new Color(red, green, blue, 255);
    }
}
