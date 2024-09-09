package entity;

import main.GamePanel;
import main.keyHandler;

import java.awt.*;

public class Player extends Entity{

    GamePanel gp;
    keyHandler keyH;


    public Player(GamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaults();
    }

    public void setDefaults(){
        posX = 100;
        posY = 100;
        speed =3;
    }


    public void update(){
        if(keyH.upPressed){
            posY -= speed;
        }
        else if(keyH.leftPressed){
            posX -= speed;
        }
        else if(keyH.downPressed){
            posY += speed;
        }
        else if(keyH.rightPressed){
            posX += speed;
        }
    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.white);
        g2.fillRect(posX, posY, gp.tileSize , gp.tileSize);

    }

}
