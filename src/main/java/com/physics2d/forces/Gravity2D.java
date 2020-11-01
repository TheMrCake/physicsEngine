/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.physics2d.forces;

import com.physics2d.rigidbody.Rigidbody2D;
import org.joml.Vector2f;

/**
 *
 * @author anton
 */
public class Gravity2D implements ForceGenerator {
    private Vector2f gravity;
    
    
    public Gravity2D(Vector2f force) {
        this.gravity = new Vector2f(force);
    }
    
    @Override
    public void updateForce(Rigidbody2D body, float dt) {
        Vector2f g = new Vector2f(gravity).mul(body.getMass());
        body.addForce(new Vector2f(gravity).mul(body.getMass()));
    }
    
}
