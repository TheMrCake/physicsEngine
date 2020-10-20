
import com.physics2d.forces.ForceRegistry;
import com.physics2d.forces.Gravity2D;
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
    private List<Rigidbody2D> rigidbodies;
    private Gravity2D gravity;
    private float fixedUpdate;
    
    public PhysicsSystem2D(float fixedUpdateDt, Vector2f gravity) {
        this.forceRegistry = new ForceRegistry();
        this.rigidbodies = new ArrayList<>();
        this.gravity = new Gravity2D(gravity);
        this.fixedUpdate = fixedUpdateDt;
    }
    
    public void update(float dt) {
        // TODO: IMPLEMENT ME!
        fixedUpdate();
    }
    
    public void fixedUpdate() {
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
