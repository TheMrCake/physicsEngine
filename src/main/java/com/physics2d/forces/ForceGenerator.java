/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.physics2d.forces;

import com.physics2d.rigidbody.Rigidbody2D;

/**
 *
 * @author anton
 */
public interface ForceGenerator {
    void updateForce(Rigidbody2D body, float dt);
}
