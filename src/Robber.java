import javafx.scene.image.Image;

public class Robber extends Perso {
    public Robber(Node pos){
        super(pos, true);
        this.dessin = new Image("images/robber-carre.jpg",15,15,true,false);
    }
}
