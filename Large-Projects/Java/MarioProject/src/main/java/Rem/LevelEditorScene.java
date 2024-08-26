package Rem;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene{

    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;

    public LevelEditorScene(){
        System.out.println("Inside LEVEL EDITOR SCENE!!!");

    }
    @Override
    public void update(double dt) {

        System.out.println("" + (1.0f / dt) + "FPS");

        if(!changingScene && KeyListener.getIsKeyPressed(KeyEvent.VK_SPACE)){
            changingScene = true;
        }

        if(changingScene && timeToChangeScene > 0){
            timeToChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;
            System.out.println("R: " + Window.get().r);
            System.out.println("G: " + Window.get().g);
            System.out.println("B: " + Window.get().b);

        }
        else if (changingScene){
            Window.changeScene(1);
        }
    }
}
