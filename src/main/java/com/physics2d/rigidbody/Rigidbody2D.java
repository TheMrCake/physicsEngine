package com.physics2d.rigidbody;

import org.joml.Vector2f;

public class Rigidbody2D {
    private Vector2f position = new Vector2f();
    private float rotation = 0.0f;

    private Vector2f linearVelocity = new Vector2f();
    private float angularVelocity = 0.0f;
    private float linearDamping = 0.0f;
    private float angularDamping = 0.0f;
    
    private boolean fixedRotation = false;
    
    public Rigidbody2D(Vector2f pos, float rot) {
        this.position = pos;
        this.rotation = rot;
    }
    
    public void setTransform(Vector2f position, float rotation) {
        this.position.set(position);
        this.rotation = rotation;
    }
    
    public void setTransform(float rotation) {
        this.position.set(position);
        this.rotation = rotation;
    }

    public Vector2f getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }
    
}
