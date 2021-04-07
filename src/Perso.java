import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Perso implements Personnage{
    protected Node pos;
    private final boolean gentil; // true=Cop, false=Robber

    protected Image dessin;
    protected final int sizeCircle = 20;
    protected final int sizeImage = 2*sizeCircle;
    protected Color color;

    public Perso(Node pos, boolean gentil){
        this.pos = pos;
        this.gentil = gentil;
        this.dessin = null;
    }

    @Override
    public void move(Node newPos) {
        this.pos = newPos;
    }

    public void draw(GraphicsContext context){
        context.setFill(this.color);
        //context.fillOval(this.pos.getX()-this.sizeCircle/2,this.pos.getY()-this.sizeCircle/2,this.sizeCircle,this.sizeCircle);
        context.drawImage(this.dessin,this.pos.getX()-this.sizeImage/2,this.pos.getY()-this.sizeImage/2);
    }

    @Override
    public String toString(){
        String res = this.gentil ? "Cop " : "Robber ";
        return res + this.pos;
    }

    public boolean isGentil() {
        return gentil;
    }
}
