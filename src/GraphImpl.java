import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashSet;

public class GraphImpl implements Graph{
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public GraphImpl(){
    }

    @Override
    public void addNode(Node node){
        this.nodes.add(node);
    }

    @Override
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    @Override
    public void addEdge(Node node1, Node node2){
        Edge edge = new Edge(node1, node2);
        this.addEdge(edge);
        this.nodes.add(node1);
        this.nodes.add(node2);
    }

    @Override
    public void rmNode(Node node) {
        this.nodes.remove(node);
    }

    @Override
    public void rmEdge(Edge edge) {
        this.edges.remove(edge);
    }

    @Override
    public void rmEdge(Node node1, Node node2) {
        //TODO
    }

    public void draw(GraphicsContext context){
        for (Node node : nodes) {
            node.draw(context);
        }
        for (Edge edge : edges) {
            edge.draw(context);
        }
    }
}
