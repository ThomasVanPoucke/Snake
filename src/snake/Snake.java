package snake;

import java.util.ArrayList;

/**
 * Created by Thomas Van Poucke on 23/12/2016.
 */
public class Snake {
    //on index 0 head, ...
    private ArrayList<SnakeDot> bodyParts = new ArrayList<SnakeDot>();
    private int pixelSize = 20;
    private Direction direction = Direction.RIGHT;

    public Snake(SnakeDot head,int pixelSize){
        this.pixelSize = pixelSize;
        bodyParts.add(head);
    }

    //Direction of snake is direction of head
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {

        if((direction==Direction.LEFT && getDirection()==Direction.RIGHT) ||
                (direction==Direction.RIGHT && getDirection()==Direction.LEFT) ||
                (direction==Direction.DOWN && getDirection()==Direction.UP) ||
                (direction==Direction.UP && getDirection()==Direction.DOWN)){
            //Snake cannot go backward
        }else{
            this.direction=direction;
        }

    }

    public ArrayList<SnakeDot> getBodyParts() {
        return bodyParts;
    }

    public void move(){
        Direction oldDirectionPartBefore=null;
        for(int i=0;i<bodyParts.size();i++) {
            SnakeDot bodyPart=bodyParts.get(i);
            //move head in its current direction, all the others follow the lead
            if(i==0){
                oldDirectionPartBefore=getHead().getDirection();
                getHead().setDirection(direction);
                bodyPart.move();
            }else{
                //old direction must be stored for direction next body part of snake
                Direction oldDirectionPartBeforeTemp = bodyPart.getDirection();
                bodyPart.setDirection(oldDirectionPartBefore);
                oldDirectionPartBefore = oldDirectionPartBeforeTemp;
                bodyPart.move();
            }
        }

    }

    public void eat(){
        //get position last part
        SnakeDot tail = bodyParts.get(bodyParts.size()-1);
        SnakeDot newTail = new SnakeDot(tail.getGraphicsContext(),tail.getImageUrl(),pixelSize);
        newTail.setX(tail.getX());
        newTail.setY(tail.getY());
        newTail.setDirection(tail.getDirection());
        move();
        bodyParts.add(newTail);
        newTail.draw();
    }

    public SnakeDot getHead(){
        return bodyParts.get(0);
    }

}
