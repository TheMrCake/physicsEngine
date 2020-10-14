package com.physics2d.primitives;

import com.physics2d.rigidbody.Rigidbody2D;
import org.joml.Vector2f;

public class Circle {
    private float radius;
    private Rigidbody2D body = null;

    public Circle(float radius, Vector2f pos) {
        this.radius = radius;
        body = new Rigidbody2D(pos, 0);
    }
    
    public Circle(float radius, int x, int y) {
        this.radius = radius;
        body = new Rigidbody2D(new Vector2f(x, y), 0);
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
    
    public void setBody(Rigidbody2D bod) {
        this.body = bod;
    }
}
