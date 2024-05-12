package main;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;


public class UI {

    GameBoard gb;

    public UI(GameBoard gb){
        this.gb = gb;
    }

    public void draw(Graphics2D g2){
        // Set the color of the rectangle
        g2.setColor(Color.WHITE);
        
        // Set the stroke (outline) of the rectangle
        g2.setStroke(new BasicStroke(2));
        
        // Draw or fill the rectangle
        g2.drawRect(14*gb.finalTileSize, 12*gb.finalTileSize, 50, 50);
    } 
}
