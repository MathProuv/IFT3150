import javafx.scene.canvas.GraphicsContext;

public interface Graph {
    void addNode(Node node);
    void addEdge(Node debut, Node fin);
    void draw(GraphicsContext context);
}
