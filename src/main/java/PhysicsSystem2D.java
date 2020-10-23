
import com.physics2d.forces.ForceRegistry;
import com.physics2d.forces.Gravity2D;
import com.physics2d.primitives.Collider2D;
import com.physics2d.rigidbody.CollisionManifold;
import com.physics2d.rigidbody.Collisions;
import com.physics2d.rigidbody.Rigidbody2D;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector2f;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anton
 */
public class PhysicsSystem2D {
    private ForceRegistry forceRegistry;
    private Gravity2D gravity;
    
    private List<Rigidbody2D> rigidbodies;
    private List<Rigidbody2D> bodies1;
    private List<Rigidbody2D> bodies2;
    private List<CollisionManifold> collisions;
    
    private float fixedUpdate;
    private int impulseIterations = 6;
    
    public PhysicsSystem2D(float fixedUpdateDt, Vector2f gravity) {
        this.forceRegistry = new ForceRegistry();
        this.gravity = new Gravity2D(gravity);
        
        this.rigidbodies = new ArrayList<>();
        this.bodies1 = new ArrayList<>();
        this.bodies2 = new ArrayList<>();
        this.collisions = new ArrayList<>();
        
        this.fixedUpdate = fixedUpdateDt;
    }
    
    public void update(float dt) {
        // TODO: IMPLEMENT ME!
        fixedUpdate();
    }
    
    public void fixedUpdate() {
        bodies1.clear();
        bodies2.clear();
        collisions.clear();
        
        // Find any collisions
        int size = rigidbodies.size();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if ( i == j) continue;
                
                CollisionManifold result = new CollisionManifold();
                
                Rigidbody2D r1 = rigidbodies.get(i);
                Rigidbody2D r2 = rigidbodies.get(j);
                
                Collider2D c1 = r1.getCollider();
                Collider2D c2 = r2.getCollider();
                
                if (c1 != null && c2 != null && !(r1.hasInfiniteMass() && r2.hasInfiniteMass())) {
                    result = Collisions.findCollisionFeatures(c1, c2);
                }
                
                if (result != null && result.isColliding()) {
                    bodies1.add(r1);
                    bodies2.add(r2);
                    collisions.add(result);
                }
            }
        }
        // Update the forces
        forceRegistry.updateForces(fixedUpdate);
        
        // Resolve collisions
        // iterate a certain amount of times to get an approximate solution
        for (int k = 0; k < impulseIterations; k++) {
            for (int i = 0; i < collisions.size(); i++) {
                int jSize = collisions.get(i).getContactPoints().size();
                for (int j = 0; j < jSize; j++) {
                    Rigidbody2D r1 = bodies1.get(i);
                    Rigidbody2D r2 = bodies2.get(i);
                    applyImpulse(r1, r2, collisions.get(i));
                }
            }
        }
        
        // Update the velocities of all rigidbodies
        for(int i=0; i < rigidbodies.size(); i++) {
            rigidbodies.get(i).physicsUpdate(fixedUpdate);
        }
    }
    
    private void applyImpulse(Rigidbody2D a, Rigidbody2D b, CollisionManifold m) {
        // linear velocity
        float invMass1 = a.getInverseMass();
        float invMass2 = b.getInverseMass();
        float invMassSum = invMass1 + invMass2;
        if (invMassSum == 0f) {
            return;
        }
        
        // Relative velocity
        Vector2f relativeVel = new Vector2f(b.getLinearVelocity()).sub(a.getLinearVelocity());
        Vector2f relativeNormal = new Vector2f(m.getNormal().normalize());
        
        // moving away from each other? Do nothing
        if (relativeVel.dot(relativeNormal) > 0.0f) {
            return;
        }
        
        float e = Math.min (a.getCor(), b.getCor());
        float numerator = (-(1.0f + e) * relativeVel.dot(relativeNormal));
        float j = numerator / invMassSum;
        
        if (m.getContactPoints().size() > 0 && j != 0.0f) {
            j /= (float) m.getContactPoints().size();
        }
        
        Vector2f impulse = new Vector2f(relativeNormal).mul(j);
        a.setLinearVelocity(new Vector2f(a.getLinearVelocity()).add(new Vector2f(impulse).mul(invMass1).mul(-1f)));
        b.setLinearVelocity(new Vector2f(b.getLinearVelocity()).add(new Vector2f(impulse).mul(invMass2).mul(1f)));
    }
    
    public void addRigidbody(Rigidbody2D body, boolean addGravity) {
        this.rigidbodies.add(body);
        if (addGravity)
            this.forceRegistry.add(body, gravity);
    }
}
