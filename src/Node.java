import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Node {
    private final int rayon = 10;
    private final Color color = Color.rgb(255, 0, 0, 0.7);

    private String name;
    private int x, y;

    public Node(Integer x, Integer y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Node(Integer x, Integer y){
        this(x, y, null);
    }

    public void move(Integer xNew, Integer yNew){
        this.x = xNew;
        this.y = yNew;
    }

    public void draw(GraphicsContext context){
        context.setFill(this.color);
        context.strokeOval(this.x, this.y, this.rayon, this.rayon);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
