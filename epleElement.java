import java.awt.*;
import java.util.Random;

public class epleElement extends ModelGraph {
    Random random = new Random();

    int randX = Round2(((random.nextInt(380)+20)));

    int randY = Round2(((random.nextInt(380)+40)));

    public epleElement(){


        posX = randX + 1;
        posY = randY + 1;
        width = 16;
        height = 16;
        color = Color.red;
    }
    public static int Round2(int b){
        return b-(b%20);
    }
}
