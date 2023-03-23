
package missilecommand;

import java.awt.geom.Ellipse2D;

public class Explosion extends Ellipse2D.Double {
    
    int sizeCount = 0;
    
    public Explosion(int x, int y, int radius){
        this.height = radius;
        this.width = radius;
        this.x = x;
        this.y = y;
        
        
    }
    
    // Need to associate this method with a timer so that the explosion grows
    // with time rather than all incrementing
    public void grow(){
        // x and y need to decrease at half the rate
        // of the height and width increase
        int growthRate = 4;
        
        this.height += growthRate;
        this.width += growthRate;
        this.x -= growthRate/2;
        this.y -= growthRate/2;
        
        sizeCount += growthRate;
    }
    
    public void shrink(){
        int shrinkRate = 4;
        
        this.height -= shrinkRate;
        this.width -= shrinkRate;
        this.x += shrinkRate/2;
        this.y += shrinkRate/2;
    }

}
