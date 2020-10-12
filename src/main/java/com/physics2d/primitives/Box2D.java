package com.physics2d.primitives;

import com.physics2d.rigidbody.Rigidbody2D;
import com.physics2d.util.JMath;
import org.joml.Vector2f;

public class Box2D {
    private Vector2f size = new Vector2f();
    private Vector2f halfSize;
    private Rigidbody2D rigidbody = null;

    public Box2D() {
        this.halfSize = new Vector2f(size.mul(0.5f));
    }

    public Box2D(Vector2f min, Vector2f max) {
        this.halfSize = new Vector2f(size).mul(0.5f);
        this.size = new Vector2f(max).sub(min);
    }

    public Vector2f getLocalMin() {
        return new Vector2f(this.rigidbody.getPosition()).sub(this.halfSize);
    }

    public Vector2f getLocalMax() {
        return new Vector2f(this.rigidbody.getPosition()).add(this.halfSize);
    }
    
    public Vector2f getHalfSize() {
        return this.halfSize;
    }

    public Vector2f[] getVertices() {
        Vector2f min = getLocalMin();
        Vector2f max = getLocalMax();

        Vector2f[] vertices = {
            new Vector2f(min.x, min.y), new Vector2f(min.x, max.y),
            new Vector2f(max.x, min.y), new Vector2f(max.x, max.y)
        };

        if (rigidbody.getRotation() != 0.0f) {
            for (Vector2f vert : vertices) {
                // TODO: IMPLEMENT ME
                // Rotates point(Vector2f) about center(Vecotr2f) by rotation(float in degrees)
                JMath.rotate(vert, this.rigidbody.getRotation(), this.rigidbody.getPosition());
            }
        }

        return vertices;
    }
    
    public Rigidbody2D getRigidbody() {
        return rigidbody;
    }
}
