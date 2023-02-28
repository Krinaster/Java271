package missilecommand;

import java.awt.Color;
import java.awt.Rectangle;


public class Missile extends Rectangle {
    
    private Color curColor;
    private double theta;
    private int missileNum;
    private int target;
    
    public Missile(int x, int y, int width, int length, double angle, int mNum, 
                    int targetNum) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        curColor = Color.WHITE;
        theta = angle;
        missileNum = mNum;
        target = targetNum;
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
    
    public void setTarget(int targetNum) {
        target = targetNum;
    }
    
    public int getTarget() {
        return target;
    }

}