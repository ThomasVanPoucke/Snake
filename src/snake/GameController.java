package snake;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.net.ssl.SSLEngineResult;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
/**
 * Created by Thomas Van Poucke on 23/12/2016.
 */
public class GameController {
    private GardenOfEden gardenOfEden=null;
    @FXML
    private Canvas canvas;
    private Timeline timeline=null;
    private boolean pause=false;
    private double speed=0.8;
    @FXML
    private int size=900;

    @FXML
    public void initialize(){
        //we need size in instance variable because it is used in the FXML file.
        this.size=Controller.getSIZE();

        startGame();
    }

    @FXML
    private void handleKeyPressedAction(KeyEvent event){
        KeyCode keyCode = event.getCode();
        Snake snake = gardenOfEden.getSnake();
        switch(keyCode){
            case LEFT:
                if(!pause && timeline.getStatus()== Animation.Status.RUNNING)
                    snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                if(!pause && timeline.getStatus()== Animation.Status.RUNNING)
                    snake.setDirection(Direction.RIGHT);
                break;
            case DOWN:
                if(!pause && timeline.getStatus()== Animation.Status.RUNNING)
                    snake.setDirection(Direction.DOWN);
                break;
            case UP:
                if(!pause && timeline.getStatus()== Animation.Status.RUNNING)
                    snake.setDirection(Direction.UP);
                break;
            case SPACE:
                if(!pause && timeline.getStatus()== Animation.Status.RUNNING)
                    speedUp();
                break;
            case P:
                if(!pause)
                    timeline.pause();
                else
                    timeline.play();
                pause=!pause;
                break;
            case ENTER:
                //reset game
                if(!pause && timeline.getStatus()== Animation.Status.STOPPED)
                    startGame();
        }
    }

    public void speedUp(){
        //speed up until maximal speed of 0.33 seconds per movement
        double newSpeed=0.85*speed;
        if(newSpeed>=0.33)
            speed=newSpeed;
        else
            speed=0.33;
        timeline.stop();
        timeline.getKeyFrames().remove(0);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(speed),ae -> gardenOfEden.nextFrame()));
        timeline.play();
    }

    public void startGame(){
        gardenOfEden=new GardenOfEden(size,canvas.getGraphicsContext2D(),Controller.getSnakeImgUrl(),Controller.getFoodImgUrl(),this);
        speed=0.8;
        timeline = new Timeline(new KeyFrame(
                Duration.seconds(speed),
                ae -> gardenOfEden.nextFrame()));
        timeline.setCycleCount(Animation.INDEFINITE);

         timeline.play();
    }

    public void stopGame(){
        timeline.stop();
    }
}
