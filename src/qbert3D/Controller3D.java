package qbert3D;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller3D implements Initializable{

    @FXML
    SubScene subScene;

    @FXML
    Slider rotator;

    private Pane group = new Pane();
    private Group rotationGrop;
    private PerspectiveCamera camera = new PerspectiveCamera(true);
    Game3D game3D;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Box box = new Box(50,50,50);
        box.setTranslateX(100);
        box.setTranslateY(0);
        box.setTranslateZ(200); // 100-120
        box.setMaterial(new PhongMaterial(Color.SALMON));
        group.getChildren().add(box);*/
        /*
        subScene.setCamera(camera);
        group.getChildren().add(camera);
        subScene.setRoot(group);

        camera.setRotate(35);
        camera.setTranslateZ(-50);
        camera.setTranslateY(20);
        group.setTranslateZ(0);*/

        //group.setRotationAxis(new Point3D(0,100,0));
        //group.setRotate(-30);
        //group.getChildren().add(camera);

        /*camera.setTranslateX(0); // 100
        camera.setTranslateY(0); // 100
        camera.setTranslateZ(-200); // 0
        //camera.setRotationAxis(new Point3D(0,100,0)); // 0, 100, 0
        //camera.setRotate(-30); // -10
        camera.setFieldOfView(150); // 100*/

        /*group.setStyle("-fx-background-color: slateblue;");
        Camera camera;
        game3D = new Game3D(group,new Player3D(3,4,5),);
        group.setRotationAxis(Rotate.Y_AXIS);
        group.setRotate(45);

        System.out.println(group.getChildren().toString());

        subScene.setCamera(camera = new PerspectiveCamera());
        subScene.setFill(Color.BLACK);
        subScene.setRoot(group);
        camera.setRotationAxis(Rotate.X_AXIS);
        camera.setRotate(-35);
        camera.setTranslateY(-200);

        rotator.valueProperty().addListener(
                (observable, oldValue, newValue) ->
                {
                    group.setRotationAxis(Rotate.Y_AXIS);
                    group.setRotate((double)newValue);
                });*/

    }
}

/*
    - bruke return a < b ? 1 : 2; typer uttrykk
    - bruke foreach-løkke når det er mulig istedenfor vanlig for-løkke
    - benytte interfaces og lambda-uttrykk
    - rekursjon?
    - abstrakte klasser, arv
    - generiske typer?
    - 2D og 3D-versjon
 */