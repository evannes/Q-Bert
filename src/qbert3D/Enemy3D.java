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
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

/**
 * Created by Elise Haram Vannes on 12.10.2017.
 */
public class Enemy3D {

    public enum Colors {
        YELLOW(Color.YELLOW), PINK(Color.DEEPPINK);
        private Color color;

        Colors(Color color) {
            this.color = color;
        }
    }
    private double radius = 50;
    private Sphere sphere = new Sphere(radius);
    private BoundingBox bBox;
    private DoubleProperty posX;
    private DoubleProperty posY;
    private DoubleProperty posZ;
    private PhongMaterial color;

    private long moveStart;
    private boolean isMoving = false;
    private boolean left = false;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;
    private int direction = 0;  // 0 = null, 1 = left, 2 = up, 3 = right, 4 = down

    public Enemy3D(double posX, double posY, double posZ){
        this.posX = new SimpleDoubleProperty(posX);
        this.posY = new SimpleDoubleProperty(posY);
        this.posZ = new SimpleDoubleProperty(posZ);
        this.bBox = new BoundingBox(posX,posY,posZ,50,50,50);

        color = new PhongMaterial((System.currentTimeMillis()&1) ==0 ? Colors.YELLOW.color : Colors.PINK.color);
        color.setSpecularColor(Color.WHITE);

        sphere.setMaterial(color);
        sphere.setTranslateX(posX);
        sphere.setTranslateY(posY);
        sphere.setTranslateZ(posZ);
    }

    public void move(){
        if(Math.random() >= 0.5){
            moveRight();
        } else {
            moveDown();
        }
    }

    public void moveRight(){    // hvis den faller utenfor brettet må det registreres så den fjernes fra arraylisten
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

        tl.setOnFinished(new EventHandler<ActionEvent>() {
                             @Override
                             public void handle(ActionEvent event) {
                                 isMoving = false;
                             }
                         }
        );
        /*posX.set(posX.get()+140);
        posY.set(posY.get()+90);
        sphere.setTranslateX(posX.get());
        sphere.setTranslateY(posY.get());*/
    }

    public void moveDown(){
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

                }
        );
        /*posY.set(posY.get()+90);
        posZ.set(posZ.get()-140);
        sphere.setTranslateY(posY.get());
        sphere.setTranslateZ(posZ.get());*/
    }

    public BoundingBox getBoundingBox(){
        return new BoundingBox(getPosX().getValue(),getPosY().getValue(),getPosZ().getValue(),
                getRadius(),getRadius(),getRadius());
    }

    private void updateBoundingBox(){

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

    public void setDown(boolean down) { this.down = down; }

    public int getDirection(){ return direction; }

    public void setDirection(int direction) { this.direction = direction; }

    public DoubleProperty getPosX(){
        return posX;
    }

    public void setPosX(DoubleProperty posX){
        this.posX = posX;
    }

    public DoubleProperty getPosY(){
        return posY;
    }

    public void setPosY(DoubleProperty posY){
        this.posY = posY;
    }

    public DoubleProperty getPosZ(){
        return posZ;
    }

    public void setPosZ(DoubleProperty posZ){
        this.posZ = posZ;
    }

    public Sphere getSphere(){
        return sphere;
    }

    public double getRadius(){
        return this.radius;
    }
}
