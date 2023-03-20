package missilecommand;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;


public class Missile extends Rectangle {
    
    private Color curColor;
    private double theta;
    private int missileNum;
    private Point target;
    
    public Missile(int x, int y, int width, int length, double angle, int mNum, 
                    int targetX, int targetY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        curColor = Color.WHITE;
        theta = angle;
        missileNum = mNum;
        target = new Point(targetX, targetY);
    }
    
    public Color getCurColor() {
        return curColor;
    }
    
    public void setCurColor(Color clr){
        curColor = clr;
    }
    
    public double getAngle() {
        return theta;
    }
    
    public void setAngle(double angle) {
        theta = angle;
    }
    
    public void setTarget(int targetX, int targetY) {
        target = new Point(targetX, targetY);
    }
    
    public Point getTarget() {
        return target;
    }
    
    public int getTargetX(){
        return target.x;
    }
    
    public int getTargetY(){
        return target.y;
    }

}