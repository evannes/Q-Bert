package qbert3D;

import eliseGL.FileHandling;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

/**
 * Created by Elise Haram Vannes on 12.10.2017.
 */
public class Player3D {

    private double radius = 50;
    private Sphere sphere = new Sphere(radius);
    private BoundingBox bBox;
    private DoubleProperty posX;
    private DoubleProperty posY;
    private DoubleProperty posZ;
    private PhongMaterial playerColor = new PhongMaterial(Color.LIGHTGREEN);//CORAL);
    private Material material;

    private long moveStart;
    private boolean isMoving = false;
    private boolean left = false;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;
    private int direction = 0;  // 0 = null, 1 = left, 2 = up, 3 = right, 4 = down

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
        //sphere.setOpacity(0);
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
    // evt returnere boolean
    public boolean move(long time){
        //System.out.println("right: " +right);
        if(left && direction == 1){
            posX.set(posX.get()-140);
            posY.set(posY.get()-90);
            sphere.setTranslateX(posX.get());
            sphere.setTranslateY(posY.get());

            moveStart = time;
            isMoving = true;
            return true;
        }
        else if(up && direction == 2){
            posY.set(posY.get()-90);
            posZ.set(posZ.get()+140);
            sphere.setTranslateY(posY.get());
            sphere.setTranslateZ(posZ.get());

            moveStart = time;
            isMoving = true;
            return true;
        }
        else if(right && direction == 3){
            posX.set(posX.get()+140);
            posY.set(posY.get()+90);
            sphere.setTranslateX(posX.get());
            sphere.setTranslateY(posY.get());
            moveStart = time;
            isMoving = true;
            return true;
        }
        else if(down && direction == 4){
            posY.set(posY.get()+90);
            posZ.set(posZ.get()-140);
            sphere.setTranslateY(posY.get());
            sphere.setTranslateZ(posZ.get());

            moveStart = time;
            isMoving = true;
            return true;
        }
        return false;
    }

    public void stopMoving(long time){
        if(time - moveStart > 200000000){
            // stopMoving
            isMoving = false;
        }
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
