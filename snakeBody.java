import java.awt.*;

public class snakeBody extends ModelGraph {


    public snakeBody(){

        posX = -20;
        posY = -20;
        width = 18;
        height = 18;
        color = Color.WHITE;
        dirr = Direction.RIGHT;
    }


    public int getPosX() {return posX;}
    public int getPosY() {return posY;}
    public Direction getDirr(){return dirr;}
    public void setPosX(int x) {posX = x;}
    public void setPosY(int y) {posY = y;}
    public void setDirr(Direction nd) {dirr = nd;}

}
