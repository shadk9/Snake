

import java.awt.*;

public abstract class ModelGraph {

    int posX,posY;
    int height, width;
    Color color;
    Direction dirr;

    public void DrawElement(Graphics g){

        g.setColor(color);
        g.fillRect(posX,posY,width,height);

    }
    public void DrawEple(Graphics g){
        g.setColor(color);
        g.fillOval(posX,posY,width,height);
    }

    public boolean kolisjonS(ModelGraph other){
        Rectangle r1 = new Rectangle(this.posX, this.posY, this.width, this.height);
        Rectangle r2 = new Rectangle(other.posX, other.posY,other.width,other.height);
        return r1.intersects(r2);
    }

}
