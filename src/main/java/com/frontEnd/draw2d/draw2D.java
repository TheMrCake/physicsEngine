/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontEnd.draw2d;

import com.physics2d.primitives.*;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author anton
 */
public class draw2D {
    private static final float meter = 100f; // One vector2f unit = 50 pixels
    private Dimension size;
    
    public draw2D(Dimension s) {
        size = s;
    }
    
    public void drawCircle(Graphics g, Circle c) {
        int width = (int) (2*meter*c.getRadius());
        int positionX = (int) (c.getCenter().x*meter);
        int positionY = (int) (size.height-(c.getCenter().y*meter));
        System.out.println(width + " " + positionX + " " + positionY);
        System.out.println("circle: " + c.getRadius()*2*meter + " " + positionX + " " + positionY);
        g.drawOval(positionX, positionY, width, width);
    }

    public void drawBox2D(Graphics g, Box2D box2d) {
        int width;
        // int x = (int) (box2d.get);
        int y;
        // g.drawRect(, width, width, width);
    }

    public void drawAABB(Graphics g, AABB AABB) {
        int width = (int) (AABB.getMax().x - AABB.getMin().x);
        int height = (int) (AABB.getMax().y - AABB.getMin().y);
        g.drawRect((int) AABB.getMin().x, (int) AABB.getMax().y, width, height);
    }
    
}
