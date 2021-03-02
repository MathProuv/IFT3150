import javafx.scene.canvas.GraphicsContext;

public interface Graph {
    boolean contains(Node node);
    boolean contains(Edge edge);
    Node addNode(Node node);
    void addEdge(Edge edge);
    void addEdge(Node node1, Node Node2);
    boolean removeNode(Node node);
    boolean removeEdge(Edge edge);
    boolean removeEdge(Node node1, Node node2);
    void draw(GraphicsContext context);
}
