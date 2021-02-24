import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Perso implements Personnage{
    protected Node pos;
    private boolean role; // true=Cop, false=Robber
    protected Image dessin;

    public Perso(Node pos, boolean roleGentil){
        this.pos = pos;
        this.role = roleGentil;
    }

    @Override
    public void move(Node newPos) {
        this.pos = newPos;
    }

    public void draw(GraphicsContext context){
        ImageView dess = new ImageView(this.dessin);

    }
}
