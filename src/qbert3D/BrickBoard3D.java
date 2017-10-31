package qbert3D;

import eliseGL.FileHandling;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * Created by Elise Haram Vannes on 12.10.2017.
 */
public class BrickBoard3D {

    private Box[][] boxes = new Box[7][7];
    private BoundingBox[][] bBoxes = new BoundingBox[7][7];
    private int boxSizeY = 90;
    private int boxSizeX = 140;
    private int boxSizeZ = 140;
    private int boxX = 200;
    private int boxY;
    private int boxZ = (boxSizeY+1)*7;
    private PhongMaterial aliveColor = new PhongMaterial();
    private PhongMaterial deadColor = new PhongMaterial();
    private PhongMaterial material;

    public BrickBoard3D(){
        setBoxColor(aliveColor,Color.web("#66B6D1"));
        setBoxColor(deadColor,Color.CORNSILK);
        Image diffuseMap = FileHandling.initImage("C:\\Users\\Bruker\\IdeaProjects\\Q-bert\\src\\img\\b.png");
        Image bumpMap = FileHandling.initImage("C:\\Users\\Bruker\\IdeaProjects\\Q-bert\\src\\img\\bricks.jpg");
        material = new PhongMaterial();
        material.setDiffuseColor(Color.LIGHTBLUE); // DIMGRAY/DIMGREY
        material.setSpecularColor(Color.WHITE);
        //material.setBumpMap(bumpMap);
        createBoxes();
    }

    private void createBoxes(){

        for(int i = 0; i < boxes.length; i++){
            boxY = 350 + (i*boxSizeY+1);
            for(int j = 0; j < boxes[0].length; j++){
                Box box = new Box(boxSizeX,boxSizeY,boxSizeZ);

                box.setTranslateX(boxX);
                box.setTranslateY(boxY);
                box.setTranslateZ(boxZ); // 100-120
                box.setMaterial(material);

                boxes[i][j] = box;
                bBoxes[i][i] = new BoundingBox(boxX,boxY,boxZ,boxSizeX,boxSizeY,boxSizeZ);
                boxX += boxSizeX+1;
                boxY += boxSizeY+1;
            }
            boxX = 200;
            boxZ -= boxSizeZ+1;
        }
        //boxX = 300;
        //boxY = boxSize+1;
        //boxZ = boxSize+1;
    }

    public void setBoxColor(PhongMaterial material, Color color){
        material.setDiffuseColor(color);
        material.setSpecularColor(Color.web("#BFEFFF"));
    }

    public Box[][] getBoxes(){
        return boxes;
    }

    public BoundingBox[][] getbBoxes(){ return bBoxes; }
}
