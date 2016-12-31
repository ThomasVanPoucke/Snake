package snake;

import com.sun.deploy.util.StringUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

/**
 * Created by Thomas Van Poucke on 27/12/2016.
 */
public class WelcomeController {
    @FXML
    private TextField chooseSnakeText;
    @FXML
    private TextField chooseFoodText;

    @FXML
    public void initialize() {
    }


    public void selectSnake(){
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Select an image to decorate your snake...");
        File snakeFile = fileChooser.showOpenDialog(Controller.getStage());
        try {
            String snakeUrl=snakeFile.toURI().toURL().toString();
            Controller.setSnakeImgUrl(snakeUrl);
            chooseSnakeText.setText(snakeUrl.replace("file:/",""));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void selectFood(){
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Select an image to decorate your food ...");
        File foodFile = fileChooser.showOpenDialog(Controller.getStage());

        try {
            String foodUrl=foodFile.toURI().toURL().toString();
            Controller.setFoodImgUrl(foodUrl);
            chooseFoodText.setText(foodUrl.replace("file:/",""));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        Controller.startGame();

    }


}
