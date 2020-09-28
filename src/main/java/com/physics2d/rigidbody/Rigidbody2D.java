package com.physics2d.rigidbody;

import org.joml.Vector2f;

public class Rigidbody2D {
    private Vector2f position = new Vector2f();
    private float rotation = 0.0f;

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2f getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }
}
