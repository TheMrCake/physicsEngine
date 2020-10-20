/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.physics2d.rigidbody;

import java.util.List;
import org.joml.Vector2f;

/**
 *
 * @author anton
 */
public class CollisionManifold {
    private Vector2f normal;
    private List<Vector2f> contactPoints;
    private float depth;

    public CollisionManifold(Vector2f normal, List<Vector2f> contactPoints, float depth) {
        this.normal = normal;
        this.contactPoints = contactPoints;
        this.depth = depth;
    }

    public Vector2f getNormal() {
        return normal;
    }

    public void setNormal(Vector2f normal) {
        this.normal = normal;
    }

    public List<Vector2f> getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(List<Vector2f> contactPoints) {
        this.contactPoints = contactPoints;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }
    
    
}
