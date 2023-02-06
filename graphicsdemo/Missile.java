
package graphicsdemo;

import java.awt.Color;
import java.awt.Rectangle;


public class Missile extends Rectangle {
    
    private Color curColor;
    private double theta;
    private int missileNum;
    
    public Missile(int x, int y, int width, int length, double angle, int mNum){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        curColor = Color.WHITE;      
        theta = angle;
        missileNum = mNum;
    }
    
    public Color getCurColor(){
        return curColor;
    }
    

} // End of Missile Class
