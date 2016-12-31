package snake;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Thomas Van Poucke on 27/12/2016.
 */
public class Controller {

    private static Stage stage=null;
    private static final int SIZE=900;
    private static String snakeImgUrl="file:resources/greenSnake.png";
    private static String foodImgUrl="file:resources/cherries.png";

    public static Stage getStage() {
        return stage;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public static String getSnakeImgUrl() {
        return snakeImgUrl;
    }

    public static void setSnakeImgUrl(String snakeImgUrl) {
        Controller.snakeImgUrl = snakeImgUrl;
    }

    public static String getFoodImgUrl() {
        return foodImgUrl;
    }

    public static void setFoodImgUrl(String foodImgUrl) {
        Controller.foodImgUrl = foodImgUrl;
    }

    public static void startApplication(){
        stage=new Stage();
        stage.setTitle( "Snake: The Game" );
        stage.getIcons().add(new Image("file:resources/snakepixel.gif"));


        Parent root= null;
        try {
            root = FXMLLoader.load(Direction.UP.getClass().getResource("welcome.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,SIZE,SIZE);
        stage.setScene(scene);
        stage.show();

    }

    public static void startGame(){
        Parent root= null;
        try {
            root = FXMLLoader.load(Direction.UP.getClass().getResource("ingame.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,SIZE,SIZE);

        stage.setScene(scene);
        stage.show();
        //Request focus so root element listens to key pressed events
        scene.getRoot().requestFocus();
    }
}
