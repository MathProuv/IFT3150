import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Node {
    private final int rayon = 10;
    private final Color color = Color.RED;

    private String name;
    private int x, y;

    public Node(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Node(int x, int y){
        this(x, y, null);
    }

    public void move(int xNew, int yNew){
        this.x = xNew;
        this.y = yNew;
    }

    public void draw(GraphicsContext context){
        context.setStroke(this.color);
        context.strokeOval(this.x-this.rayon, this.y-this.rayon, this.rayon*2, this.rayon*2);
        if (this.name != null){
            context.fillText(this.name, this.x, this.y);
        }
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public boolean isEquals(Node node2){
        double dist2 = Math.pow(this.x - node2.getX(),2) + Math.pow(this.y - node2.getY(),2);
        return dist2 <= Math.pow(this.rayon,2);
    }
}
