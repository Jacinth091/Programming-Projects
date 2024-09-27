package main;
import javax.swing.JPanel;
import java.awt.*;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    final int origTileSize = 16; // 16x16 tile
    final int scale =3;

    final public int tileSize = origTileSize * scale; // 48x48 tile size displayed on screen

    // 4 x 3 screen size
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;


    // Screen Width and Height Settings
    public final int screenWidth = tileSize * maxScreenCol; // 48 * 16 = 768 screen width
    public final int screenHeight = tileSize * maxScreenRow; // 48 * 12 = 576 screen height


    // Game Thread
    Thread gameThread;

    // Key Event Handler
    keyHandler keyH = new keyHandler();

    // Player Class
    Player player = new Player(this, keyH);

    // TileManager Class - MAP Manager
    TileManager tileManager = new TileManager(this, "/Map/testMap.txt");

    // Game FPS
    int FPS =60;

    // Constants
    final long nanoTime = 1000000000;


    // Testing for player pos

//    int posX = 100;
//    int posY =100;
//
//    int plySpd = 3;

    

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {


        double drawInterval = nanoTime/FPS;
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime ) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1){
                // UPDATE: Update information (Movement, Health, etc)
                update();

                // DRAW: Draw or Re-Draw the screen with the updated information
                repaint();

                delta--;
            }
        }
    }

    public void update(){

        player.update();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);

        player.draw(g2);

        g2.dispose();


    }
}
