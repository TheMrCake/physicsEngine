/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.physics2d.util;

import java.awt.geom.Point2D;
import org.joml.Vector2f;

/**
 *
 * @author anton
 */
public class JMath {

    public static void rotate(Vector2f vec, float angleDeg, Vector2f origin) {
        float x = vec.x - origin.x;
        float y = vec.y - origin.y;
        
        float cos = (float) Math.cos(Math.toRadians(angleDeg));
        float sin = (float) Math.sin(Math.toRadians(angleDeg));
        
        float xPrime = (x * cos) - (y * sin);
        float yPrime = (x * sin) + (y * cos);
        
        xPrime += origin.x;
        yPrime += origin.y;
        
        vec.x = xPrime;
        vec.y = yPrime;
    }
    
    public static boolean compare(float x, float y, float epsilon) {
        return Math.abs(x - y) <= epsilon * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean compare(Vector2f vec1, Vector2f vec2, float epsilon) {
        return compare(vec1.x, vec2.x, epsilon) && compare(vec1.y, vec2.y, epsilon);
    }

    public static boolean compare(float x, float y) {
        return Math.abs(x - y) <= Float.MIN_VALUE * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean compare(Vector2f vec1, Vector2f vec2) {
        return compare(vec1.x, vec2.x) && compare(vec1.y, vec2.y);
    }
    
    public static Vector2f createVector(Point2D point) {
        Vector2f vector = new Vector2f((float)point.getX(), (float)point.getY());
        return vector;
    }
    
}
