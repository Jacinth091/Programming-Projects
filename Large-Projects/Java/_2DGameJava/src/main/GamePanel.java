package main;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    final int origTileSize = 16; // 16x16 tile
    final int scale =3;

    final int tileSize = origTileSize * scale; // 48x48 tile size displayed on screen

    // 4 x 3 screen size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;


    // Screen Width and Height Settings
    final int screenWidth = tileSize * maxScreenCol; // 48 * 16 = 768 screen width
    final int screenHeight = tileSize * maxScreenRow; // 48 * 12 = 576 screen height


    // Game Thread
    Thread gameThread;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        while(gameThread != null){

            System.out.println("Game Loop is Working!!");


            // UPDATE: Update information (Movement, Health, etc)
            update();

            // DRAW: Draw or Re-Draw the screen with the updated information

            repaint();


        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
