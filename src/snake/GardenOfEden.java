package snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Created by Thomas Van Poucke on 23/12/2016.
 */
public class GardenOfEden {
    //SIDE of garden is the value for the width and height of the garden
    private int SIZE=300;
    private int pixelSize=300/15;
    private GraphicsContext graphicsContext=null;
    private Snake snake=null;
    private Food food=null;
    private GameController gameController =null;

    public GardenOfEden(int SIZE, GraphicsContext graphicsContext, String snakeImageUrl, String foodImageUrl, GameController gameController) {
        this.SIZE = SIZE;
        this.graphicsContext = graphicsContext;
        this.pixelSize = SIZE/15;
        this.gameController = gameController;

        //clear play ground
        graphicsContext.clearRect(0,0,SIZE,SIZE);
        //set up new play ground
        SnakeDot head=new SnakeDot(graphicsContext,snakeImageUrl,pixelSize);
        this.snake = new Snake(head,pixelSize);
        this.food=new Food(graphicsContext,foodImageUrl,pixelSize,snake);


        snake.eat();
        snake.eat();
        snake.eat();
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public Snake getSnake() {
        return snake;
    }

    public void nextFrame(){
        //check if snake found food
        int nextX=snake.getHead().calculateNextXPosition(snake.getDirection());
        int nextY=snake.getHead().calculateNextYPosition(snake.getDirection());
        int foodX=food.getX();
        int foodY=food.getY();

        if(isCollision(nextX,nextY)){
            //Collision of snake with boundaries of the game or with its own body
            //Game ends
            gameController.stopGame();
            showScore();
        }else if(nextX==foodX && nextY==foodY){
            snake.eat();
            //Snake has eaten, new food must appear
            food=new Food(graphicsContext,food.getImageUrl(),pixelSize,snake);
            //Level up, speed up game
            gameController.speedUp();
        }else{
            snake.move();
        }
    }

    public boolean isCollision(int nextX,int nextY){
        if(nextX<0||nextY<0||nextX>=15||nextY>=15){
            //collisions with game boundaries
            return true;
        }else{
            //check if collision with own snake body
            ArrayList<SnakeDot> bodyParts=snake.getBodyParts();
            //tail will move, so don't check for collision with last body part of snake
            for(int i=0;i<bodyParts.size()-1;i++){
                SnakeDot bodyPart=bodyParts.get(i);
                if(bodyPart.getX()==nextX && bodyPart.getY()==nextY){
                    return true;
                }
            }
            //no collision
            return false;
        }
    }

    public void showScore(){
        graphicsContext.clearRect(5*pixelSize,5*pixelSize,5*pixelSize,5*pixelSize);
        graphicsContext.moveTo(5*pixelSize,5*pixelSize);
        graphicsContext.setFont(new Font(64));
        graphicsContext.fillText("Your score is "+getSnake().getBodyParts().size()+".",5*pixelSize,5*pixelSize);
        graphicsContext.setFont(new Font(32));
        graphicsContext.fillText("\nPress <ENTER> to try again.",5*pixelSize,5*pixelSize);
    }

}
