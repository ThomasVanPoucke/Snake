package snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by Thomas Van Poucke on 23/12/2016.
 */
public class SnakeDot {
    private int x=0;
    private int y=0;
    private GraphicsContext graphicsContext =null;
    private String imageUrl=null;
    private Direction direction=Direction.RIGHT;
    private int pixelSize=20;


    public SnakeDot(GraphicsContext graphicsContext,String imageUrl, int pixelSize){
        this.imageUrl=imageUrl;
        this.graphicsContext = graphicsContext;
        this.pixelSize=pixelSize;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void move(){
        clear();
        setX(calculateNextXPosition(direction));
        setY(calculateNextYPosition(direction));
        draw();
    }

    public int calculateNextXPosition(Direction direction){
        switch(direction){
            case LEFT:
                return x-1;
            case RIGHT:
                return x+1;
        }
        return x;
    }

    public int calculateNextYPosition(Direction direction){
        switch(direction){
            case UP:
                return y-1;
            case DOWN:
                return y+1;
        }
        return y;
    }

    public void clear(){
        graphicsContext.clearRect(x*pixelSize,y*pixelSize,pixelSize,pixelSize);
    }

    public void draw(){
        graphicsContext.drawImage(new Image(imageUrl,pixelSize,pixelSize,true,true),x*pixelSize,y*pixelSize);
    }


}
