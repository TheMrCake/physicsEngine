/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontEnd.draw2d;

import com.physics2d.primitives.Box2D;
import com.physics2d.primitives.Circle;
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
        
        g.drawOval(width, width, positionX, positionY);
    }

    public static void drawBox2D(Graphics g, Box2D box2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void drawAABB(Graphics g, int AABB) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
