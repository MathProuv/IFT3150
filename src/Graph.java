import javafx.scene.canvas.GraphicsContext;

public interface Graph {
    Node getNodeFromPos(Node pos);
    boolean containsNode(Node node);
    void addNode(Node node);
    boolean removeNode(Node node);
    boolean isNeighbors(Node node1, Node node2);

    boolean containsEdge(Node node1, Node Node2);
    void addEdge(Node node1, Node Node2);
    boolean removeEdge(Node node1, Node node2);

    void draw(GraphicsContext context);
}
