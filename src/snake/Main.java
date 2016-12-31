package snake;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Thomas Van Poucke on 23/12/2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller.startApplication();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
