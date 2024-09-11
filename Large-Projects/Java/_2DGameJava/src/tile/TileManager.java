package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tileSprites;

    // CONSTANTS
    final String tilesPath = "res/Tiles";

    public TileManager(GamePanel gp){
        this.gp = gp;

        getTileSprites();

    }

    public void update(){

    }
    public void draw(Graphics2D g2){

//        g2.drawImage(tileSprites[0].tile, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tileSprites[1].tile, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tileSprites[2].tile, 96, 0, gp.tileSize, gp.tileSize, null);

        int col =0, row =0, x =0, y =0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            g2.drawImage(tileSprites[0].tile, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col =0;
                x =0;
                row++;
                y+= gp.tileSize;
            }

        }

    }

    public void getTileSprites(){
        String[] tileList = {"grass", "wall", "water", "sand", "earth", "tree"};
        File path = new File(tilesPath);

        File[] tilesPath = path.listFiles();

        assert tilesPath != null;
        tileSprites = new Tile[tilesPath.length];

        for(int i = 0; i < tilesPath.length; i++){
            BufferedImage tempBuffer = null;

            String filePath = tilesPath[i].getPath();
            String tileName = tileList[i];
//            System.out.println(filePath);

            // Search for the matching file in the folder
            for (File file : tilesPath) {
                if (file.getName().contains(tileName)) {
                    System.out.println(file.getName());
                    try {
                        // Read the image file and assign it to the tile array
                        tempBuffer = ImageIO.read(file);

                        if (tempBuffer != null) {
                            tileSprites[i] = new Tile();
                            tileSprites[i].tile = tempBuffer;
                            System.out.println("Loaded: " + file.getName());
                        }
                    } catch (IOException e) {
                        System.out.println("Failed to load image: " + file.getPath());
                        e.printStackTrace();
                    }
                    break; // Exit loop once the matching file is found
                }
            }

            // Check if the sprite was not found or loaded
            if (tempBuffer == null) {
                System.out.println("Missing or failed to load tile: " + tileName);
                tileSprites[i] = new Tile(); // Create a default tile to avoid null references
            }

        }
    }
}
