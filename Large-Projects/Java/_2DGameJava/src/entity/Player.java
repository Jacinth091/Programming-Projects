package entity;

import main.GamePanel;
import main.keyHandler;
import tile.Tile;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;

    TileManager tm;
    keyHandler keyH;

    // Constants
    final int spriteAnimSpd  =12;
    final String playerSpritesPath = "res/Player";

    public int screenPosX;
    public int screenPosY;

    public Player(GamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenPosX = gp.screenWidth/2 - (gp.tileSize/2);
        screenPosY = gp.screenHeight/2  - (gp.tileSize/2);


        // Collider

        boxCollider = new Rectangle(8, 16, gp.tileSize -16, gp.tileSize-16);


        // Setting Default Values
        setDefaults();
        // Player Sprites
        getPlayerSprites();
    }

    public void setDefaults(){
        worldPosX = gp.tileSize * 23;
        worldPosY = gp.tileSize *21;
        speed = 3;
        direction = "down";
    }

    // ------------------------------------------ MAIN METHODS ------------------------------------------
    public void update(){

        if(isKeyPressed()){

            checkPlayerKeyPress();

            collisionOn = false;
            gp.collCheck.checkTile(this);

            spriteAnimationManager();
        }
    }

    public void draw(Graphics2D g2){

//        g2.setColor(Color.white);
//        g2.fillRect(posX, posY, gp.tileSize , gp.tileSize);

        g2.drawImage(playerSprite(), screenPosX, screenPosY, gp.tileSize, gp.tileSize, null);



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

        File path = new File(playerSpritesPath); // Getting the path of all player sprites
        File[] allSprites = path.listFiles(); // Listing individual files inside the folder path

        assert allSprites != null;
        walkingSprites = new BufferedImage[allSprites.length]; // assigning the length of player walking sprites array

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
            worldPosY -= speed;
        }
        else if(keyH.leftPressed){
            direction = "left";
            worldPosX -= speed;
        }
        else if(keyH.downPressed){
            direction = "down";
            worldPosY += speed;
        }
        else if(keyH.rightPressed){
            direction = "right";
            worldPosX += speed;
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
