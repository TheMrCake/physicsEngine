/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontEnd.draw2d;

import com.physics2d.primitives.*;
import java.awt.Graphics;

/**
 *
 * @author anton
 */
public class draw2D {
    
    public static void drawCircle(Graphics g, Circle c) {
        int width = (int) (2*c.getRadius());
        int positionX = (int) (c.getCenter().x);
        int positionY = (int) (c.getCenter().y);
        g.drawOval(positionX, positionY, width, width);
    }

    public static void drawBox2D(Graphics g, Box2D box2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void drawAABB(Graphics g, AABB AABB) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
