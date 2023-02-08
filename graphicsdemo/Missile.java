
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicsdemo;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author instructor
 */
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

