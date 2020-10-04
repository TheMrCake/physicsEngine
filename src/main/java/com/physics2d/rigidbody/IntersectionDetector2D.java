package com.physics2d.rigidbody;

import com.physics2d.primitives.AABB;
import com.physics2d.primitives.Box2D;
import com.physics2d.primitives.Circle;
import com.physics2d.primitives.Ray2D;
import com.physics2d.primitives.RaycastResult;
import com.physics2d.util.JMath;
import static com.physics2d.util.JMath.createPoint2D;
import static com.physics2d.util.JMath.createVector;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import static java.lang.Math.min;
import org.joml.Vector2f;

public class IntersectionDetector2D {
    // Point vs. Primitve Tests
    public static boolean pointOnLine(Vector2f point, Line2D line) {
        float dy = (float) (line.getP2().getY() - line.getP1().getY());
        float dx = (float) (line.getP2().getX() - line.getP1().getX());
        
        if (dx == 0f) {
            return JMath.compare(point.x, (float)line.getP1().getX());
        }
        
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
    
    public static boolean lineAndAABB(Line2D line, AABB box) {
        if (pointInAABB(createVector(line.getP1()), box) || pointInAABB(createVector(line.getP2()), box)) {
            return true;
        }
        
        Vector2f unitVector = new Vector2f(createVector(line.getP2()).sub(createVector(line.getP1())));
        
        unitVector.normalize();
        unitVector.x = (unitVector.x != 0) ? 1.0f / unitVector.x : 0f;
        unitVector.y = (unitVector.y != 0) ? 1.0f / unitVector.y : 0f;
        
        Vector2f min = box.getMin();
        min.sub(createVector(line.getP1())).mul(unitVector);
        Vector2f max = box.getMax();
        max.sub(createVector(line.getP1())).mul(unitVector);
        
        float tmin = Math.max(Math.min(min.x, max.x), Math.min(min.y, max.y));
        float tmax = Math.min(Math.max(min.x, max.x), Math.max(min.y, max.y));
        
        if (tmax < 0 || tmin > tmax) {
            return false;
        }
        
        float t = (tmin < 0f) ? tmax : tmin;
        return t > 0f && t * t < Point2D.distanceSq(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }
    
    public static boolean lineAndBox2D(Line2D line, Box2D box) {
        float theta = -box.getRigidbody().getRotation();
        Vector2f center = box.getRigidbody().getPosition();
        Vector2f localStart = createVector(line.getP1());
        Vector2f localEnd =  createVector(line.getP2());
        JMath.rotate(localStart, theta, center);
        JMath.rotate(localEnd, theta, center);
        
        Line2D localLine = new Line2D.Float(createPoint2D(localStart), createPoint2D(localEnd));
        AABB aabb = new AABB(box.getMin(), box.getMax());
        
        return lineAndAABB(localLine, aabb);
    }
    
    // Raycasts
    
    public static boolean raycast(Circle circle, Ray2D ray, RaycastResult result) {
        RaycastResult.reset(result);

        Vector2f originToCircle = new Vector2f(circle.getCenter()).sub(ray.getOrigin());
        float radiusSquared = circle.getRadius() * circle.getRadius();
        float originToCircleLengthSquared = originToCircle.lengthSquared();

        // Project the vector from the ray origin onto the direction of the ray
        float a = originToCircle.dot(ray.getDirection());
        float bSq = originToCircleLengthSquared - (a * a);
        if (radiusSquared - bSq < 0.0f) {
            return false;
        }

        float f = (float)Math.sqrt(radiusSquared - bSq);
        float t = 0;
        if (originToCircleLengthSquared < radiusSquared) {
            // Ray starts inside the circle
            t = a + f;
        } else {
            t = a - f;
        }

        if (result != null) {
            Vector2f point = new Vector2f(ray.getOrigin()).add(ray.getDirection().mul(t));
            Vector2f normal = new Vector2f(point).sub(circle.getCenter());
            normal.normalize();

            result.init(point, normal, t, true);
        }

        return true;
    }
}