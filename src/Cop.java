import javafx.scene.image.Image;

public class Cop extends Perso {

    public Cop(Node pos){
        super(pos, false);
        this.dessin = new Image("images/cop-carre.png",15,15,true,false);
    }
}
