package Projekt.src.bgr;

import java.awt.image.BufferedImage;

public class Tile {
    
    public BufferedImage image;
    public boolean collision = false;

}

// private void drawSpriteFrame(Image source, Graphics2D g2d, int x, int y,
//                      int columns, int frame, int width, int height)
// {
//     int frameX = (frame % columns) * width;
//     int frameY = (frame / columns) * height;
//     g2d.drawImage(source, x, y, x+width, y+height,
//                   frameX, frameY, frameX+width, frameY+height, this);
// }
