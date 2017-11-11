package qbert3D;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.shape.Box;

/**
 * Created by Elise Haram Vannes on 12.10.2017.
 */
public class Game3D {

    private BrickBoard3D brickBoard;
    private Box[][] boxes = new Box[7][7];
    private Player3D player;
    private AnimationTimer animation;
    private boolean moved;

    public Game3D(Group group, Player3D player){
        this.brickBoard = new BrickBoard3D();
        this.player = player;

        boxes = brickBoard.getBoxes();
        group.getChildren().add(player.getSphere());
        int numBoxes = 7;

        for(int i = 0; i < boxes.length; i++){

            for(int j = 0; j < boxes.length; j++) {
                if(j < numBoxes) {
                    group.getChildren().add(boxes[i][j]);
                }
            }
            numBoxes--;
        }

        animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moved = player.updatePlayer(now);
                if(moved){
                    brickBoard.checkCollision(player.getPosX().getValue(),player.getPosY().getValue(),player.getPosZ().getValue(),
                            player.getRadius(),player.getRadius(),player.getRadius());
                }
            }
        };

        startGame();
    }

    public void startGame(){
        animation.start();
    }


}
