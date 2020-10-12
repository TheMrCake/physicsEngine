package com.physics2d.primitives;

import com.physics2d.rigidbody.Rigidbody2D;
import org.joml.Vector2f;

public class Circle {
    private float radius;
    private Rigidbody2D body = null;

    public Circle(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }
    
    public Vector2f getCenter() {
        return body.getPosition();
    }
    
    public void setRadius(float r) {
        this.radius = r;
    }
}
