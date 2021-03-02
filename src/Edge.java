import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.Iterator;

public class Edge {
    private final Color color = Color.HOTPINK;

    private final HashSet<Node> nodes;

    public Edge(Node node1, Node node2){
        this.nodes = new HashSet<>();
        nodes.add(node1);
        nodes.add(node2);
    }

    public boolean containsNode(Node node){
        Iterator<Node> nodeIter = this.nodes.iterator();
        Node node1 = nodeIter.next();
        Node node2 = nodeIter.next();
        return node.isEqual(node1) || node.isEqual(node2);
    }
    public boolean isEqual(Edge edge){
        Iterator<Node> nodeIter = this.nodes.iterator();
        Node node1 = nodeIter.next();
        Node node2 = nodeIter.next();
        return edge.containsNode(node1) && edge.containsNode(node2) || edge.containsNode(node2) & edge.containsNode(node1);
    }

    public void draw(GraphicsContext context){
        context.setStroke(color);
        Iterator<Node> nodeIter = this.nodes.iterator();
        Node node1 = nodeIter.next();
        Node node2 = nodeIter.next();
        assert !nodeIter.hasNext();
        context.strokeLine(node1.getX(),node1.getY(),node2.getX(),node2.getY());
    }

    public Iterator<Node> getNodesIter() {
        return nodes.iterator();
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
