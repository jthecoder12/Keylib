package org.indiumstudios.keylib.rendering.objects.shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.indiumstudios.keylib.rendering.colors.RGBAc;

import java.awt.Dimension;

abstract class Shape extends Actor {
    protected ShapeRenderer renderer = new ShapeRenderer();
    protected Vector2 position;
    protected Dimension size;
    protected RGBAc color;

    /**
     * Do not use this function, it will do nothing. Instead, use getPosition() or move().
     */
    @Deprecated
    @Override
    public void setX(float x) {}

    /**
     * Do not use this function, it will do nothing. Instead, use getPosition() or move().
     */
    @Deprecated
    @Override
    public void setY(float y) {}

    /**
     * Do not use this function, it will return 0. Instead, use getPosition().
     * @return 0
     */
    @Deprecated
    @Override
    public float getX() {return 0;}

    /**
     * Do not use this function, it will return 0. Instead, use getPosition().
     * @return 0
     */
    @Deprecated
    @Override
    public float getY() {return 0;}

    /**
     * Do not use this function, it will do nothing. Instead, use getPosition() or move().
     */
    @Deprecated
    @Override
    public void setX(float x, int alignment) {}

    /**
     * Do not use this function, it will do nothing. Instead, use getPosition() or move().
     */
    @Deprecated
    @Override
    public void setY(float y, int alignment) {}

    /**
     * Do not use this function, it will do nothing. Instead, use getPosition() or move().
     */
    @Deprecated
    @Override
    public void setPosition(float x, float y) {}

    /**
     * Do not use this function, it will do nothing. Instead, use getPosition() or move().
     */
    @Deprecated
    @Override
    public void setPosition(float x, float y, int alignment) {}

    /**
     * Do not use this function, it will do nothing. Instead, use getPosition() or move().
     */
    @Deprecated
    @Override
    public void moveBy(float x, float y) {}
}
