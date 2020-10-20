
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
        
        // Resolve collisions
        
        forceRegistry.updateForces(fixedUpdate);
        
        // Update the velocities of all rigidbodies
        for(int i=0; i < rigidbodies.size(); i++) {
            rigidbodies.get(i).physicsUpdate(fixedUpdate);
        }
    }
    
    public void addRigidbody(Rigidbody2D body) {
        this.rigidbodies.add(body);
        this.forceRegistry.add(body, gravity);
    }
}
