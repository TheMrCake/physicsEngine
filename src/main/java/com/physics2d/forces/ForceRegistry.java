/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.physics2d.forces;

import com.physics2d.rigidbody.Rigidbody2D;
import java.util.ArrayList;

/**
 *
 * @author anton
 */
public class ForceRegistry {
    private ArrayList<ForceRegistration> registry;
    
    public ForceRegistry() {
        this.registry = new ArrayList<>();
    }
    
    public void add(Rigidbody2D rb, ForceGenerator fg) {
        ForceRegistration fr = new ForceRegistration(fg, rb);
        registry.add(fr);
    }
    
    public void remove(Rigidbody2D rb, ForceGenerator fg) {
        ForceRegistration fr = new ForceRegistration(fg, rb);
        registry.remove(fr);
    }
    
    public void clear() {
        registry.clear();
    }
    
    public void updateForces(float dt) {
        for (ForceRegistration fr: registry) {
            fr.fg.updateForce(fr.rb, dt);
        }
    }
    
    public void zeroForces() {
        for (ForceRegistration fr : registry) {
            // TODO: IMPLEMENT ME
            
            // fr.rb.zeroForces();
        }
    }
}
