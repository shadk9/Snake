import java.awt.*;

public class snakeElement extends ModelGraph {

    public snakeElement(){

        posX = 200;
        posY = 200;
        width = 18;
        height = 18;
        color = Color.GREEN;

    }

    public int getPosX() {return posX;}
    public int getPosY() {return posY;}
    public void setPosX(int x) {posX = x;}
    public void setPosY(int y) {posY = y;}


}
