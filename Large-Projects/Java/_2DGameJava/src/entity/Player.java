package entity;

import main.GamePanel;
import main.keyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    keyHandler keyH;

    // Constants
    final int spriteAnimSpd  =12;
    final String playerSpritesPath = "res/Player";


    public Player(GamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;


        // Setting Default Values
        setDefaults();
        // Player Sprites
        getPlayerSprites();
    }

    public void setDefaults(){
        posX = 100;
        posY = 100;
        speed = 3;
        direction = "down";
    }

    // ------------------------------------------ MAIN METHODS ------------------------------------------
    public void update(){

        if(isKeyPressed()){

            checkPlayerKeyPress();

            spriteAnimationManager();
        }
    }

    public void draw(Graphics2D g2){

//        g2.setColor(Color.white);
//        g2.fillRect(posX, posY, gp.tileSize , gp.tileSize);

        g2.drawImage(playerSprite(), posX, posY, gp.tileSize, gp.tileSize, null);



    }

    // ------------------------------------------ METHODS ------------------------------------------
    public BufferedImage playerSprite(){

        BufferedImage sprite = null;

        switch(direction){
            case "down":
                sprite = changeSprite() ? walkingSprites[0] : walkingSprites[1];
                break;
            case "left":
                sprite = changeSprite() ? walkingSprites[2] : walkingSprites[3];
                break;

            case "right":
                sprite = changeSprite() ? walkingSprites[4] : walkingSprites[5];
                break;

            case "up":
                sprite = changeSprite() ? walkingSprites[6] : walkingSprites[7];
                break;
            default:
                System.out.println("There is no assigned sprite to " + direction + " direction");
        }

        return  sprite;
    }

    public void getPlayerSprites(){

        File path = new File(playerSpritesPath);
        File[] allSprites = path.listFiles();

        assert allSprites != null;
        walkingSprites = new BufferedImage[allSprites.length];

        for(int i = 0; i < allSprites.length; i++){
            BufferedImage tempBuffer;
            try{
                String filePath = allSprites[i].getPath();
                System.out.println(filePath);

//                tempBuffer = ImageIO.read(getClass().getResourceAsStream("\"\\"+filePath+"\""));

                tempBuffer = ImageIO.read(allSprites[i]);

                if(tempBuffer != null){
                    walkingSprites[i] = tempBuffer;
                }
                else{
                    System.out.println("Failed to load image: " + filePath);
                }


            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }
    public void spriteAnimationManager(){
        spriteCounter++;

        if(spriteCounter > spriteAnimSpd){
            if(spriteNum ==1){
                spriteNum =2;
            }
            else if(spriteNum ==2){
                spriteNum =1;
            }
            spriteCounter =0;
        }
    }
    public void checkPlayerKeyPress(){
        if(keyH.upPressed){
            direction = "up";
            posY -= speed;
        }
        else if(keyH.leftPressed){
            direction = "left";
            posX -= speed;
        }
        else if(keyH.downPressed){
            direction = "down";
            posY += speed;
        }
        else if(keyH.rightPressed){
            direction = "right";
            posX += speed;
        }

    }
    public boolean isKeyPressed(){
        return keyH.upPressed || keyH.leftPressed || keyH.rightPressed || keyH.downPressed;
    }

    public boolean changeSprite(){
        boolean value = false;
        if(spriteNum ==1){
            value = true;
        }
        else if( spriteNum ==2){
            value = false;
        }
        return value;
    }



}
