package com.physics2d.primitives;

// Axis Aligned Bounding Box

import com.physics2d.rigidbody.Rigidbody2D;
import org.joml.Vector2f;
import org.joml.Vector2fc;

public class AABB extends Shape2D {
    private Vector2f center = new Vector2f();
    private Vector2f size = new Vector2f();
    private Rigidbody2D rigidbody = null;
    private Vector2f halfSize;

    public AABB() {
        this.halfSize = new Vector2f(size.mul(0.5f));
    }

    public AABB(Vector2f min, Vector2f max) {
        this.halfSize = new Vector2f(size).mul(0.5f);
        this.size = new Vector2f(max).sub(min);
    }

    public Vector2f getMin() {
        return new Vector2f(this.rigidbody.getPosition()).sub(this.halfSize);
    }

    public Vector2f getMax() {
        return new Vector2f(this.rigidbody.getPosition()).add(this.halfSize);
    } 
    
    @Override
    public int getShape() {
        return 2;
    }
    
    public void setRigidbody(Rigidbody2D rb) {
        this.rigidbody = rb;
    }
    
    public void setSize(Vector2f size) {
        this.size.set(size);
        this.halfSize.set(size.x / 2.0f, size.y / 2.0f);
    }
}
