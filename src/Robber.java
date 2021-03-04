import javafx.scene.image.Image;

public class Robber extends Perso {
    public Robber(Node pos){
        super(pos, true);
        this.dessin = new Image("images/robber-carre.jpg",this.sizeImage,this.sizeImage,true,false);
    }
}
