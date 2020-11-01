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
        int positionX = (int) (c.getCenter().x*100);
        int positionY = (int) (0-(c.getCenter().y*100));
        g.drawOval(positionX, positionY, width, width);
    }

    public static void drawBox2D(Graphics g, Box2D box2d) {
        int width;
        // int x = (int) (box2d.get);
        int y;
        // g.drawRect(, width, width, width);
    }

    public static void drawAABB(Graphics g, AABB AABB) {
        int width = (int) (AABB.getMax().x - AABB.getMin().x);
        int height = (int) (AABB.getMax().y - AABB.getMin().y);
        g.drawRect((int) AABB.getMin().x, (int) AABB.getMax().y, width, height);
    }
    
}
