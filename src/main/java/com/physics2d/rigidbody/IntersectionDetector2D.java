package com.physics2d.rigidbody;

import com.physics2d.primitives.AABB;
import com.physics2d.primitives.Box2D;
import com.physics2d.primitives.Circle;
import com.physics2d.util.JMath;
import static com.physics2d.util.JMath.createVector;
import java.awt.geom.Line2D;
import org.joml.Vector2f;

public class IntersectionDetector2D {
    // Point vs. Primitve Tests
    public static boolean pointOnLine(Vector2f point, Line2D line) {
        float dy = (float) (line.getP2().getY() - line.getP1().getY());
        float dx = (float) (line.getP2().getX() - line.getP1().getX());
        
        float m = dy/dx;
        
        float b = (float) (line.getP2().getY() - (m * line.getP2().getX()));

        // Check the equation
        return point.y == m*point.x + b;
    }
    
    public static boolean pointInCircle(Vector2f point, Circle circle) {
        Vector2f circleCenter = circle.getCenter();
        Vector2f centerToPoint = new Vector2f(point).sub(circleCenter);
        
        return centerToPoint.lengthSquared() < circle.getRadius() * circle.getRadius();
    }
    
    public static boolean pointInAABB(Vector2f point, AABB box) {
        Vector2f min = box.getMin();
        Vector2f max = box.getMax();
        
        return point.x <= max.x && min.x <= point.x && 
                point.y <= max.y && min.y <= point.y;
    }
    
    public static boolean pointInBox2D(Vector2f point, Box2D box) {
        // Translate the point into local space
        Vector2f pointLocalBoxSpace = new Vector2f(point);
        JMath.rotate(pointLocalBoxSpace, box.getRigidbody().getRotation(), 
                box.getRigidbody().getPosition());
        
        Vector2f min = box.getMin();
        Vector2f max = box.getMax();

        return pointLocalBoxSpace.x <= max.x && min.x <= pointLocalBoxSpace.x
                && pointLocalBoxSpace.y <= max.y && min.y <= pointLocalBoxSpace.y;
    }
    // Line vs. Primitive Tests
    
    public static boolean lineAndCircle(Line2D line, Circle circle) {
        if (pointInCircle(createVector(line.getP1()), circle) || 
                pointInCircle(createVector(line.getP2()), circle)) {
            return true;
        }
        
        Vector2f ab = new Vector2f(createVector(line.getP2())).sub(createVector(line.getP1()));
        
        // Project point (circle position) onto ab (line segment)
        // parameterized position t
        Vector2f circleCenter = circle.getCenter();
        Vector2f centerToLineStart = new Vector2f(circleCenter).sub(createVector(line.getP1()));
        float t = centerToLineStart.dot(ab) / ab.dot(ab);
        
        if (t < 0.0 || t > 1.0) {
            return false;
        }
        
        Vector2f closestPoint = new Vector2f(createVector(line.getP1())).add(ab.mul(t));
        
        return pointInCircle(closestPoint, circle);
    }
}