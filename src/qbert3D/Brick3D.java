package qbert3D;

import javafx.geometry.BoundingBox;
import javafx.scene.shape.Box;

/**
 * Created by Elise Haram Vannes on 22.10.2017.
 */
public class Brick3D {

    private boolean isAlive = true;
    private String color = "#66B6D1";
    private Box box;
    private BoundingBox bBox;
    private int boxSizeX;
    private int boxSizeY;
    private int boxSizeZ;

    public Brick3D(){

    }

    public Box getBox(){ return box; }

    public boolean isAlive(){ return isAlive; }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
}
