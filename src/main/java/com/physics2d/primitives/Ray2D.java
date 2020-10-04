/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.physics2d.primitives;

import org.joml.Vector2f;

/**
 *
 * @author anton
 */
public class Ray2D {
    private Vector2f origin;
    private Vector2f direction;

    public Ray2D(Vector2f origin, Vector2f direction) {
        this.origin = origin;
        this.direction = direction;
        this.direction.normalize();
    }

    public Vector2f getOrigin() {
        return this.origin;
    }

    public Vector2f getDirection() {
        return this.direction;
    }
}
