import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Robber extends Perso {
    public Robber(Node pos){
        super(pos, false);
        this.dessin = new Image("images/robber_paint1.png",this.sizeImage,this.sizeImage,true,false);
        this.color = Color.GREY;
        this.color = this.color.brighter();
    }
}
