package snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Thomas Van Poucke on 23/12/2016.
 */
public class Food {
    private int x=0;
    private int y=0;
    private GraphicsContext graphicsContext =null;
    private int pixelSize=20;
    private String imageUrl =null;

    public Food(GraphicsContext graphicsContext, String imageUrl, int pixelSize,Snake snake) {
        this.graphicsContext = graphicsContext;
        this.pixelSize = pixelSize;
        this.imageUrl = imageUrl;



        Random random = new Random();
        //food may not be on current position of snake
        //find random location for food that is not located on snake
        int x=0;
        int y=0;
        Function<Snake,Function<Integer,Function<Integer,Boolean>>> onSnake=s->(xFood->(yFood->s.getBodyParts().stream().anyMatch(snakeDot->snakeDot.getX()==xFood && snakeDot.getY()==yFood)));
        do {
            x = random.nextInt(15);
            y = random.nextInt(15);
        }while(onSnake.apply(snake).apply(x).apply(y).booleanValue());
        setX(x);
        setY(y);

        draw();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void draw() {
        graphicsContext.drawImage( new Image(imageUrl,pixelSize,pixelSize,true,true), x*pixelSize, y*pixelSize);
    }
}
