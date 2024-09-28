package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;

public class TileManager {

    GamePanel gp;
    public Tile[] tileSprites;
    int tileMapData[][];
//    boolean collisionTileData[][];

    // CONSTANTS
    final String tilesPath = "res/Tiles";

    public TileManager(){

        getTileSprites();
//        loadMapData(mapFile);
        loadCollision();
    }
    public TileManager(GamePanel gp, String mapFile){
        this.gp = gp;
        tileMapData = new int[gp.maxWorldCol][gp.maxWorldRow];
//        collisionTileData = new boolean[gp.maxWorldCol][gp.maxWorldCol];
        getTileSprites();
        loadMapData(mapFile);
        loadCollision();
//        check();

    }

    public void loadMapData(String mapFile){

        try{
            InputStream is = getClass().getResourceAsStream(mapFile);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row =0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    tileMapData[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col =0;
                    row++;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();

        }


    }
    public void draw(Graphics2D g2){

//        g2.drawImage(tileSprites[0].tile, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tileSprites[1].tile, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tileSprites[2].tile, 96, 0, gp.tileSize, gp.tileSize, null);

        int worldRow =0, worldCol =0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileIndex = tileMapData[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldPosX + gp.player.screenPosX;
            int screenY = worldY - gp.player.worldPosY + gp.player.screenPosY;

            if(drawWhenNeed(worldX, worldY)){
                g2.drawImage(tileSprites[tileIndex].tile, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol =0;
                worldRow++;
            }

        }

    }

    public void check(){
        for(int i =0; i < tileSprites.length; i++){
            System.out.printf("%d = %s\n", (i),tileSprites[i].collision);
        }
    }

    public void loadCollision(){

/*        int col = tileMapData.length;
        int row = tileMapData[0].length;

        for(int i =0; i < col; i++){

            for(int j = 0; j < row; j++){
                int tile = tileMapData[i][j];

                if(tile == 1 && tile == 2 && tile && 4){
                    collisionTileData[i][j] = true;
                }
                else{
                    collisionTileData[i][j] = false;

                }


            }
        }
        */
        for(int i =0; i< tileSprites.length; i++){


            if(i == 1 || i == 2 || i ==4){
                tileSprites[i].collision = true;
            }
            else{
                tileSprites[i].collision = false;
            }
        }


    }

    public boolean drawWhenNeed(int worldX, int worldY){

        return  worldX + gp.tileSize > gp.player.worldPosX - gp.player.screenPosX &&
                worldX - gp.tileSize < gp.player.worldPosX + gp.player.screenPosX &&
                worldY + gp.tileSize > gp.player.worldPosY -gp.player.screenPosY &&
                worldY - gp.tileSize < gp.player.worldPosY + gp.player.screenPosY;


    }

    public void getTileSprites(){
        String[] tileList = {"grass", "wall", "water", "earth", "tree", "sand"};


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
