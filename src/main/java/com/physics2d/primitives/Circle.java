package com.physics2d.primitives;

import com.physics2d.rigidbody.Rigidbody2D;
import org.joml.Vector2f;

public class Circle extends Shape2D{
    private float radius;
    private Rigidbody2D rigidbody = null;

    public Circle(float radius, Vector2f pos) {
        this.radius = radius;
        rigidbody = new Rigidbody2D(pos, 0);
    }
    
    public float getRadius() {
        return radius;
    }
    
    public Vector2f getCenter() {
        return rigidbody.getPosition();
    }
    
    public void setRadius(float r) {
        this.radius = r;
    }
    
    public void setRigidbody(Rigidbody2D bd) {
        this.rigidbody = bd;
    }

    public Rigidbody2D getRigidbody() {
        return rigidbody;
    }
    
    @Override
    public int getShape() {
        return 0;
    }
}
