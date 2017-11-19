package qbert3D;

import javafx.animation.AnimationTimer;
import javafx.geometry.BoundingBox;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elise Haram Vannes on 12.10.2017.
 */
public class Game3D {

    private Pane group;
    private BrickBoard3D brickBoard;
    private Box[][] boxes = new Box[7][7];
    private List<Enemy3D> enemies = new ArrayList<>();
    private Player3D player;
    private AnimationTimer animation;
    private boolean moved;
    private long lastCreatedEnemy;
    private long lastMovedEnemy = 0;
    private Label playerLives;
    // TODO: Legge inn abstrakt klasse for spill"objekter", player og enemy kan arve fra
    public Game3D(Pane group, Player3D player, Label playerLives){
        this.group = group;
        this.brickBoard = new BrickBoard3D();
        this.player = player;
        this.lastCreatedEnemy = System.currentTimeMillis();
        this.playerLives = playerLives;

        boxes = brickBoard.getBoxes();
        player.setBrickBoard(brickBoard);
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
        brickBoard.checkCollision(player.getBoundingBox());

        animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //System.out.println(player.getIsMoving());
                moved = player.updatePlayer(now);

                brickBoard.checkCollision(player.getBoundingBox());

                if(player.isFalling()) player.fallDown();

                createEnemy(System.currentTimeMillis());
                moveEnemies(System.currentTimeMillis());

                for(Enemy3D e:enemies){
                    if(collides(player,e)){
                        for(Enemy3D en:enemies){
                            group.getChildren().remove(en.getSphere());
                        }
                    }
                }
            }
        };

        startGame();
    }

    public void startGame(){
        animation.start();
    }

    public void createEnemy(long now){
        if(now - lastCreatedEnemy > 3000){
            Enemy3D enemy = new Enemy3D(200,260,-180,brickBoard);
            enemies.add(enemy); // X * 2 ? eller X / 2 ?
            group.getChildren().add(enemy.getSphere());
            lastCreatedEnemy = now;
            System.out.println("new enemy created, number of enemies: " + enemies.size());
        }
    }

    public void moveEnemies(long now){
        if(now - lastMovedEnemy > 500){
            for(Enemy3D e : enemies){
                e.move();
            }
            lastMovedEnemy = now;
        }
    }

    public boolean collides(Player3D player, Enemy3D e){
        BoundingBox box = e.getBoundingBox();
        BoundingBox playerBox = player.getBoundingBox();

        if(box.intersects(playerBox)){
            System.out.println("Enemy intersected player!");
            long now = System.currentTimeMillis();
            player.decrementLives();
            if(player.getLives() < 1){
                playerLives.setText("Lives: " + player.getLives());
                gameOver();
            } else {
                playerLives.setText("Lives: " + player.getLives());
                animation.stop();
                long tid = System.currentTimeMillis();
                while (tid - now < 3000) {
                    // wait
                    //System.out.println(now - tid);
                    tid = System.currentTimeMillis();
                }
                System.out.println("ute av loop");
                animation.start();
            }
            return true;
        }
        return false;
    }

    AnimationTimer at = new AnimationTimer(){
        long lastUpdate = 0;
        @Override
        public void handle(long now) {
            System.out.println(now - lastUpdate);
            if (now - lastUpdate >= 28_000_000) {
                brickBoard.changeBoxColor(new PhongMaterial(Color.RED));
                lastUpdate = now;
                at.stop();
            }
        }
    };

    public void resetGame(){

    }

    public void gameOver(){
        animation.stop();
        long now2 = System.currentTimeMillis();
        // boksene skal flashe
        //while(System.currentTimeMillis() - now2 < 3000){
            long tid = System.currentTimeMillis();
            /*while(System.currentTimeMillis() - now2 < 1000){
                System.out.println(System.currentTimeMillis() - now2);
                //tid = System.currentTimeMillis();
            }*/
            System.out.println("ut av første loop");
            brickBoard.changeBoxColor(new PhongMaterial(Color.YELLOW));

            at.start();
            /*

            System.out.println("byttet farge til rød");
            now2 = System.currentTimeMillis();
            while(System.currentTimeMillis() - now2 < 20000){
                //System.out.println(System.currentTimeMillis() - now2);
            }
            //brickBoard.changeBoxColor(new PhongMaterial(Color.YELLOW));
        brickBoard.changeBoxColor(new PhongMaterial(Color.RED));
        System.out.println("byttet farge til gul");
        //}

        at.start();*/
    }
}
