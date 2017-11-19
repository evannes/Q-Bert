package qbert3D;

import eliseGL.FileHandling;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

/**
 * Created by Elise Haram Vannes on 12.10.2017.
 */
public class Player3D {

    //private Box[][] boxes = new Box[7][7];
    private BrickBoard3D board;
    private double radius = 50;
    private Sphere sphere = new Sphere(radius);
    private BoundingBox bBox;
    private DoubleProperty posX;
    private DoubleProperty posY;
    private DoubleProperty posZ;
    private PhongMaterial playerColor = new PhongMaterial(Color.LIGHTGREEN);//CORAL);
    private Material material;

    private int lives = 3;
    private long moveStart;
    private boolean isMoving = false;
    private boolean left = false;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;
    private int direction = 0;  // 0 = null, 1 = left, 2 = up, 3 = right, 4 = down
    private boolean isFalling = false;

    public Player3D(double posX, double posY, double posZ){
        this.posX = new SimpleDoubleProperty(posX);
        this.posY = new SimpleDoubleProperty(posY);
        this.posZ = new SimpleDoubleProperty(posZ);
        this.bBox = new BoundingBox(posX,posY,posZ,50,50,50);

        Image diffuseMap = FileHandling.initImage("C:\\Users\\Bruker\\IdeaProjects\\Q-bert\\src\\img\\rar.png");
        Image bumpMap = FileHandling.initImage("C:\\Users\\Bruker\\IdeaProjects\\Q-bert\\src\\img\\hex.jpg");
        PhongMaterial mat = new PhongMaterial();
        mat.setBumpMap(bumpMap);

        mat.setDiffuseColor(Color.TURQUOISE);
        mat.setSpecularColor(Color.CORNSILK);
        sphere.setMaterial(mat);
        material = new PhongMaterial();

        sphere.setMaterial(playerColor);
        sphere.setTranslateX(posX);
        sphere.setTranslateY(posY);
        sphere.setTranslateZ(posZ);
    }

    public boolean updatePlayer(long time){
        if(!isMoving){
            return move(time);
        }
        else {
            stopMoving(time);
            return false;
        }
    }

    public boolean move(long time){
        if(!isMoving) {
            if (left && direction == 1) {
                isMoving = true;

                Timeline tl = new Timeline();

                KeyValue keyX = new KeyValue(sphere.translateXProperty(), sphere.getTranslateX() - 140);
                KeyFrame frameX = new KeyFrame(Duration.millis(500), keyX);

                KeyValue keyY = new KeyValue(sphere.translateYProperty(), sphere.getTranslateY() - 90, Interpolator.TANGENT(Duration.millis(500), 100, Duration.millis(500), -100));
                KeyFrame frameY = new KeyFrame(Duration.millis(500), keyY);

                tl.getKeyFrames().addAll(frameX, frameY);
                tl.play();

                posX.set(posX.get() - 140);
                posY.set(posY.get() - 90);
                moveStart = time;
                tl.setOnFinished(new EventHandler<ActionEvent>() {
                                     @Override
                                     public void handle(ActionEvent event) {
                                         isMoving = false;
                                         if(!board.simpleCheckCollision(getBoundingBox())){
                                             fallDown();
                                         }
                                     }
                                 }
                );
                return true;
            } else if (up && direction == 2) {

                isMoving = true;
                Timeline tl = new Timeline();

                KeyValue keyX = new KeyValue(sphere.translateZProperty(), sphere.getTranslateZ() + 140);
                KeyFrame frameX = new KeyFrame(Duration.millis(500), keyX);

                KeyValue keyY = new KeyValue(sphere.translateYProperty(), sphere.getTranslateY() - 90, Interpolator.TANGENT(Duration.millis(500), 100, Duration.millis(500), -100));
                KeyFrame frameY = new KeyFrame(Duration.millis(500), keyY);

                tl.getKeyFrames().addAll(frameX, frameY);
                tl.play();

                posY.set(posY.get() - 90);
                posZ.set(posZ.get() + 140);

                tl.setOnFinished(new EventHandler<ActionEvent>() {
                                     @Override
                                     public void handle(ActionEvent event) {
                                         isMoving = false;
                                         if(!board.simpleCheckCollision(getBoundingBox())){
                                             fallDown();
                                         }
                                     }
                                 }
                );
                moveStart = time;
                return true;
            } else if (right && direction == 3) {

                isMoving = true;
                Timeline tl = new Timeline();

                KeyValue keyX = new KeyValue(sphere.translateXProperty(), sphere.getTranslateX() + 140);
                KeyFrame frameX = new KeyFrame(Duration.millis(500), keyX);

                KeyValue keyY = new KeyValue(sphere.translateYProperty(), sphere.getTranslateY() + 90, Interpolator.TANGENT(Duration.millis(500), 100, Duration.millis(500), -100));
                KeyFrame frameY = new KeyFrame(Duration.millis(500), keyY);

                tl.getKeyFrames().addAll(frameX, frameY);
                tl.play();

                posX.set(posX.get() + 140);
                posY.set(posY.get() + 90);
                moveStart = time;

                tl.setOnFinished(new EventHandler<ActionEvent>() {
                                     @Override
                                     public void handle(ActionEvent event) {
                                         isMoving = false;
                                         if(!board.simpleCheckCollision(getBoundingBox())){
                                             fallDown();
                                         }
                                     }
                                 }
                );
                return true;
            } else if (down && direction == 4) {

                isMoving = true;
                Timeline tl = new Timeline();

                KeyValue keyY = new KeyValue(sphere.translateYProperty(), sphere.getTranslateY() + 90, Interpolator.TANGENT(Duration.millis(500), 10, Duration.millis(500), -10));
                KeyFrame frameX = new KeyFrame(Duration.millis(500), keyY);

                KeyValue keyZ = new KeyValue(sphere.translateZProperty(), sphere.getTranslateZ() - 140);
                KeyFrame frameY = new KeyFrame(Duration.millis(500), keyZ);

                tl.getKeyFrames().addAll(frameX, frameY);
                tl.play();

                posY.set(posY.get() + 90);
                posZ.set(posZ.get() - 140);
                tl.setOnFinished((ActionEvent event) -> {
                                         isMoving = false;
                                        if(!board.simpleCheckCollision(getBoundingBox())){
                                            fallDown();
                                        }
                                 }
                );
                moveStart = time;

                return true;
            }
        }
        return false;
    }

    public void fallDown(){
        KeyValue keyY = new KeyValue(sphere.translateYProperty(), sphere.getTranslateY() + 400);
        KeyFrame frameX = new KeyFrame(Duration.millis(400), keyY);
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(frameX);
        tl.play();
    }

    public void stopMoving(long time){
        if(time - moveStart > 200000000){
            // stopMoving
            //isMoving = false;
        }
    }

    public BoundingBox getBoundingBox(){
        return new BoundingBox(sphere.getTranslateX(),sphere.getTranslateY(),sphere.getTranslateZ(),
                radius,radius,radius);
    }
    // evt kun ta inn instansen av BrickBoard, så kalle på dens kollisjonsmetode
    /*public boolean checkCollision(){
        int numBoxes = 7;

        for(int i = 0; i < boxes.length; i++) {

            for (int j = 0; j < boxes.length; j++) {
                if (j < numBoxes) {
                    Box box = boxes[i][j];
                    BoundingBox bounds = new BoundingBox(box.getTranslateX() - 50, box.getTranslateY() - 50, box.getTranslateZ() - 50, box.getWidth(), box.getHeight(), box.getDepth());
                    if (bounds.intersects(posX.doubleValue(), posY.doubleValue(), posZ.doubleValue(), radius, radius, radius)) {
                        return true;
                    }
                }
            }
            numBoxes--;
        }
        return false;
    }*/

    public Sphere getSphere(){
        return sphere;
    }

    public boolean isFalling(){
        return isFalling;
    }

    public void setBrickBoard(BrickBoard3D board){
    this.board = board;
    }

    public boolean getIsMoving(){
        return isMoving;
    }

    public void setIsMoving(boolean isMoving){
        this.isMoving = isMoving;
    }

    public boolean getLeft(){ return left; }

    public void setLeft(boolean left) { this.left = left; }

    public boolean getUp(){ return up; }

    public void setUp(boolean up) { this.up = up; }

    public boolean getRight(){ return right; }

    public void setRight(boolean right) { this.right = right; }

    public boolean getDown(){ return down; }

    public void setDown(boolean down){
    this.down = down;}

    public DoubleProperty getPosX(){
        return posX;
    }

    public DoubleProperty getPosY(){
        return posY;
    }

    public DoubleProperty getPosZ(){
        return posZ;
    }

    public double getRadius(){
        return radius;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public int getLives(){
        return lives;
    }

    public void decrementLives(){
        lives--;
    }
}