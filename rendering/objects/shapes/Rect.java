package org.indiumstudios.keylib.rendering.objects.shapes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import org.indiumstudios.keylib.rendering.colors.RGBAc;
import org.jetbrains.annotations.NotNull;

import java.awt.Dimension;

public class Rect extends Shape {
    public Rect(Vector2 position, Dimension size, @NotNull RGBAc color) {
        this.position = position;
        this.size = size;
        this.color = color;
    }

    @Override
    public void draw(@NotNull Batch batch, float parentAlpha) {
        batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color.toGdxColor());
        renderer.rect(position.x, position.y, (float)size.getWidth(), (float)size.getHeight());
        renderer.end();
        batch.begin();
    }

    public void move(@NotNull Vector2 v) {
        this.position.set(this.position.x + v.x, this.position.y + v.y);
    }
}
