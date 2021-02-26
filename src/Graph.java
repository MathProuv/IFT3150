import javafx.scene.canvas.GraphicsContext;

public interface Graph {
    boolean contains(Node node);
    boolean contains(Edge edge);
    void addNode(Node node);
    void addEdge(Edge edge);
    void addEdge(Node node1, Node Node2);
    void rmNode(Node node);
    void rmEdge(Edge edge);
    void rmEdge(Node node1, Node node2);
    void draw(GraphicsContext context);
}
