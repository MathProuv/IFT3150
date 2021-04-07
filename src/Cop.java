import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Cop extends Perso {

    public Cop(Node pos){
        super(pos, true);
        this.dessin = new Image("images/cop_paint1.png",this.sizeImage,this.sizeImage,true,false);
        this.color = Color.BLUE;
        this.color = this.color.brighter();
    }
}
