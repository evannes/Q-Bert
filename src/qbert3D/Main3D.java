package qbert3D;

/**
 * Created by Elise Haram Vannes on 12.10.2017.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main3D extends Application {

    private Pane group = new Pane();
    private Group rotationGrop;
    private PerspectiveCamera camera = new PerspectiveCamera(true);
    Game3D game3D;
    Player3D player;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Camera camera;

        VBox vBox = new VBox(10);
        vBox.setPrefWidth(100);
        vBox.setAlignment(Pos.CENTER);

        Label playerLives = new Label("Lives: 3");
        playerLives.setFont(Font.font ("Verdana", 23));
        playerLives.setTextFill(Color.BEIGE);
        vBox.getChildren().add(playerLives);

        player = new Player3D(340,350,-180);
        game3D = new Game3D(group,player,playerLives);
        group.setRotationAxis(Rotate.Y_AXIS);
        group.setRotate(45);
        group.setStyle("-fx-background-color: black;");
        SubScene subScene = new SubScene(group,1300,850,true,SceneAntialiasing.BALANCED);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #000000");
        borderPane.setCenter(subScene);
        borderPane.setLeft(vBox);

        Scene scene = new Scene(borderPane,1400,850,true,SceneAntialiasing.BALANCED);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case LEFT:
                        player.setLeft(true);
                        player.setDirection(1);
                        break;
                    case UP:
                        player.setUp(true);
                        player.setDirection(2);
                        break;
                    case RIGHT:
                        player.setRight(true);
                        player.setDirection(3);
                        break;
                    case DOWN:
                        player.setDown(true);
                        player.setDirection(4);
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case LEFT:
                        player.setLeft(false);
                        break;
                    case UP:
                        player.setUp(false);
                        break;
                    case RIGHT:
                        player.setRight(false);
                        break;
                    case DOWN:
                        player.setDown(false);
                }
            }
        });

        subScene.setCamera(camera = new PerspectiveCamera());
        subScene.setFill(Color.BLACK);
        subScene.setRoot(group);
        camera.setRotationAxis(Rotate.X_AXIS);
        camera.setRotate(-35);
        camera.setTranslateX(-370);
        camera.setTranslateY(-100);
        camera.setTranslateZ(-400);

        /*AmbientLight light=new AmbientLight(Color.AQUA);
        light.setTranslateX(-180);
        light.setTranslateY(-90);
        light.setTranslateZ(-120);
        light.getScope().addAll(group);*/

        // dette lyset gir samme effekt som det ene levelet i q-bert med kun flater
        /*PointLight light2=new PointLight(Color.AQUA);
        light2.setTranslateX(180);
        light2.setTranslateY(190);
        light2.setTranslateZ(180);
        light2.getScope().addAll(group);
        group.getChildren().add(light2);*/

        //Parent root = FXMLLoader.load(getClass().getResource("game3D.fxml"));
        primaryStage.setTitle("Q-Bert");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

