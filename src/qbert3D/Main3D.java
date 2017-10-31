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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main3D extends Application {

    private Group group = new Group();
    private Group rotationGrop;
    private PerspectiveCamera camera = new PerspectiveCamera(true);
    Game3D game3D;
    Player3D player;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Camera camera;
        player = new Player3D(340,350,637);
        game3D = new Game3D(group,player);
        group.setRotationAxis(Rotate.Y_AXIS);
        group.setRotate(45);

        SubScene subScene = new SubScene(group,1300,850,true,SceneAntialiasing.BALANCED);

        VBox vBox = new VBox(10);
        vBox.setPrefWidth(100);
        vBox.setAlignment(Pos.CENTER);

        Label playerLives = new Label("Lives: 3");
        playerLives.setFont(Font.font ("Verdana", 23));
        playerLives.setTextFill(Color.BEIGE);
        vBox.getChildren().add(playerLives);

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
        camera.setTranslateY(-200);

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

