package missilecommand;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Missile extends Rectangle {
    
    private Color curColor;
    private double theta;
    private int targetCity;
    private Point target;
    ArrayList<Rectangle> trail;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param length
     * @param angle
     * @param tCity
     * @param targetX
     * @param targetY
     */
    public Missile(int x, int y, int width, int length, double angle, int tCity, 
                    int targetX, int targetY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        curColor = Color.WHITE;
        theta = angle;
        this.targetCity = tCity;
        target = new Point(targetX, targetY);
        trail = new ArrayList<>();

    }
    
    /**
     *
     * @return
     */
    public Color getCurColor() {
        return curColor;
    }
    
    /**
     *
     * @param clr
     */
    public void setCurColor(Color clr){
        curColor = clr;
    }
    
    /**
     *
     * @return
     */
    public double getAngle() {
        return theta;
    }
    
    /**
     *
     * @param angle
     */
    public void setAngle(double angle) {
        theta = angle;
    }
    
    /**
     *
     * @param targetX
     * @param targetY
     */
    public void setTarget(int targetX, int targetY) {
        target = new Point(targetX, targetY);
    }
    
    /**
     *
     * @return
     */
    public Point getTarget() {
        return target;
    }
    
    /**
     *
     * @return
     */
    public int getTargetX(){
        return target.x;
    }
    
    /**
     *
     * @return
     */
    public int getTargetY(){
        return target.y;
    }

    /**
     *
     * @return
     */
    public int getTargetCity(){
        return this.targetCity;
    }


}