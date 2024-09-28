package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int worldPosX, worldPosY;
    public int speed;

    public String direction;

    public int spriteCounter =0;
    public int spriteNum = 1;

//    public BufferedImage up1, up2, left1, left2, down1,down2, right1, right2;

    public BufferedImage[] walkingSprites;

}
