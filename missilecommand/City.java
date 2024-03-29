package missilecommand;

import java.awt.Color;
import java.awt.Polygon;

/**
 *
 * @author student
 */
public class City extends Polygon {
    
    private int segment;
    private Color cityColor;
    
    /**
     *
     * @param x
     * @param y
     * @param l
     */
    public City(int x, int y, int l) {
        cityColor = Color.MAGENTA;
        
        npoints = 18;
        xpoints = new int[npoints];
        ypoints = new int[npoints];
        segment = l;
        
        xpoints[0] = x;
        ypoints[0] = y + 8*l;
        xpoints[1] = x;
        ypoints[1] = y + 6*l;
        xpoints[2] = x + 2*l;
        ypoints[2] = y + 6*l;
        xpoints[3] = x + 2*l;
        ypoints[3] = y + 2*l;
        xpoints[4] = x + 4*l;
        ypoints[4] = y + 2*l;
        xpoints[5] = x + 4*l;
        ypoints[5] = y;
        xpoints[6] = x + 6*l;
        ypoints[6] = y;
        xpoints[7] = x + 6*l;
        ypoints[7] = y + 2*l;
        xpoints[8] = x + 8*l;
        ypoints[8] = y + 2*l;
        xpoints[9] = x + 8*l;
        ypoints[9] = y + 4*l;
        xpoints[10] = x + 10*l;
        ypoints[10] = y + 4*l;
        xpoints[11] = x + 10*l;
        ypoints[11] = y + l;
        xpoints[12] = x + 12*l;
        ypoints[12] = y + l;
        xpoints[13] = x + 12*l;
        ypoints[13] = y;
        xpoints[14] = x + 14*l;
        ypoints[14] = y;
        xpoints[15] = x + 14*l;
        ypoints[15] = y + 6*l;
        xpoints[16] = x + 16*l;
        ypoints[16] = y + 6*l;
        xpoints[17] = x + 16*l;
        ypoints[17] = y + 8*l;
    }
    
    /**
     *
     * @return
     */
    public int getTop() {
        return ypoints[4];
    }
    
    /**
     *
     * @return
     */
    public int getBottom() {
        return ypoints[17];
    }
    
    /**
     *
     * @return
     */
    public int getRight() {
        return xpoints[16];
    }
    
    /**
     *
     * @return
     */
    public int getLeft() {
        return xpoints[0];
    }
    
    /**
     *
     * @return
     */
    public int getCenterX() {
        return (getRight() + getLeft()) / 2;
    }
    
    /**
     *
     * @return
     */
    public int getCenterY() {
        return (getTop() + getBottom()) / 2;
    }
    
    /**
     *
     * @return
     */
    public Color getCityColor(){
        return cityColor;
    }
    
    /**
     *
     * @param c
     */
    public void setCityColor(Color c){
        cityColor = c;
    }
    
    /**
     *
     * @return
     */
    public int getCityLength(){
        return getRight() - getLeft();
    }
    
    /**
     *
     * @return
     */
    public int getCityHeight(){
        return getTop()-getBottom();
    }

}