import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Perso implements Personnage{
    protected Node pos;
    private boolean roleGentil; // true=Cop, false=Robber
    protected Image dessin;
    protected final int sizeImage = 20;
    protected Color color;

    public Perso(Node pos, boolean roleGentil){
        this.pos = pos;
        this.roleGentil = roleGentil;
        this.dessin = null;
    }

    @Override
    public void move(Node newPos) {
        this.pos = newPos;
    }

    public void draw(GraphicsContext context){
        context.setFill(this.color);
        context.fillOval(this.pos.getX()-this.sizeImage/2,this.pos.getY()-this.sizeImage/2,this.sizeImage,this.sizeImage);
        //context.drawImage(this.dessin,this.pos.getX()-this.sizeImage/2,this.pos.getY()-this.sizeImage/2);
    }

    @Override
    public String toString(){
        String res = this.roleGentil ? "Cop " : "Robber ";
        return res + this.pos;
    }
}
