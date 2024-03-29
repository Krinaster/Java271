package missilecommand;

import java.awt.Color;
import java.awt.Polygon;

/**
 *
 * @author student
 */
public class Base extends Polygon {
    
    private Color baseColor;
    private int segmentLength;
    
    /**
     *
     * @param width
     * @param y
     */
    public Base(int width, int y){
        
        baseColor = Color.CYAN;
        
        int x = 0,
            segmentLength = width/29, 
            baseHeight = y-100, 
            raisedHeight = y-200;
        
        
        npoints = 16;
        xpoints = new int[npoints];
        ypoints = new int[npoints];
        
        xpoints[0] = 0;
        ypoints[0] = y;
        
        xpoints[1] = 0;
        ypoints[1] = baseHeight;
        
        xpoints[2] = 1*segmentLength;
        ypoints[2] = baseHeight;
        
        xpoints[3] = 2*segmentLength;
        ypoints[3] = raisedHeight;
        
        xpoints[4] = 5*segmentLength;
        ypoints[4] = raisedHeight;
    
        xpoints[5] = 6*segmentLength;
        ypoints[5] = baseHeight;
        
        xpoints[6] = 12*segmentLength;
        ypoints[6] = baseHeight;
        
        xpoints[7] = 13*segmentLength;
        ypoints[7] = raisedHeight;
        
        xpoints[8] = 16*segmentLength;
        ypoints[8] = raisedHeight;
        
        xpoints[9] = 17*segmentLength;
        ypoints[9] = baseHeight;
        
        xpoints[10] = 23*segmentLength;
        ypoints[10] = baseHeight;
        
        xpoints[11] = 24*segmentLength;
        ypoints[11] = raisedHeight;
        
        xpoints[12] = 27*segmentLength;
        ypoints[12] = raisedHeight;
        
        xpoints[13] = 28*segmentLength;
        ypoints[13] = baseHeight;
        
        xpoints[14] = 30*segmentLength;
        ypoints[14] = baseHeight;
        
        xpoints[15] = width;
        ypoints[15] = y;
    }

    /**
     *
     * @return
     */
    public Color getBaseColor(){
        return baseColor;
    }
    
    /**
     *
     * @return
     */
    public int valley1LeftBound(){
        return xpoints[5];
    }
    
    /**
     *
     * @return
     */
    public int valley1RightBound(){
        return xpoints[6];
    }
    
    /**
     *
     * @return
     */
    public int valley2LeftBound(){
        return xpoints[9];
    }
    
    /**
     *
     * @return
     */
    public int valley2RightBound(){
        return xpoints[10];
    }
    
    /**
     *
     * @return
     */
    public int valleyHeight(){
        return ypoints[9];
    }
    
    /**
     *
     * @return
     */
    public int hillLength(){
        return xpoints[4]-xpoints[3];
    }
    
    /**
     *
     * @return
     */
    public int hillHeight(){
        return 100;
    }
    
    /**
     *
     * @return
     */
    public int hill1Center(){
        return xpoints[3];
    }
    
    /**
     *
     * @return
     */
    public int hill2Center(){
        return xpoints[7];
    }
    
    /**
     *
     * @return
     */
    public int hill3Center(){
        return xpoints[11];
    }
} // End of Base