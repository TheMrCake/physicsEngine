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
public class ForceRegistration {
    ForceGenerator fg;
    Rigidbody2D rb;
    
    public ForceRegistration(ForceGenerator fg, Rigidbody2D rb) {
        this.fg = fg;
        this.rb = rb;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == null) return false;
        if(other.getClass() != ForceRegistration.class) return false;
        
        ForceRegistration fr = (ForceRegistration)other;
        return fr.rb == this.rb && fr.fg == this.fg;
    }
}
