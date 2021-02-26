import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Iterator;

public class Edge {
    private final int rayon = 10;
    private final Color color = Color.rgb(0, 0, 0, 1);

    private HashSet<Node> nodes;

    public Edge(Node node1, Node node2){
        this.nodes = new HashSet<>();
        nodes.add(node1);
        nodes.add(node2);
    }

    public void draw(GraphicsContext context){
        context.setFill(color);
        Iterator<Node> nodeIter = this.nodes.iterator();
        Node node1 = nodeIter.next();
        Node node2 = nodeIter.next();
        assert !nodeIter.hasNext();
        context.strokeLine(node1.getX(),node1.getY(),node2.getX(),node2.getY());
    }
}
