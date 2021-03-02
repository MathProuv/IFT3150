import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GraphImpl implements Graph{
    private final ArrayList<Node> nodes;
    private final ArrayList<Edge> edges;
    private final HashMap<Node,ArrayList<Node>> neighbors;

    public GraphImpl(){
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.neighbors = new HashMap<>();
    }

    @Override
    public boolean contains(Node node) {
        for (Node nodeIn : this.nodes) {
            if (node.isEqual(nodeIn)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Node addNode(Node node){
        boolean isNodeIn = false;
        for (Node nodeIn : this.nodes) {
            if (node.isEqual(nodeIn)){
                node = nodeIn;
                isNodeIn = true;
            }
        }
        if (!isNodeIn){
            this.nodes.add(node);
            this.neighbors.put(node, new ArrayList<Node>());
        }
        return node;
    }

    @Override
    public boolean contains(Edge edge) {
        for (Edge edgeIn : this.edges) {
            if (edge.isEqual(edgeIn)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEdge(Edge edge) {
        if (!this.contains(edge)) {
            Iterator<Node> nodeIter = edge.getNodesIter();
            Node node1 = nodeIter.next();
            Node node2 = nodeIter.next();
            node1 = this.addNode(node1);
            node2 = this.addNode(node2);
            this.neighbors.get(node1).add(node2);
            this.neighbors.get(node2).add(node1); // Graphe non-orienté
            this.edges.add(new Edge(node1,node2));
        }
    }

    @Override
    public void addEdge(Node node1, Node node2){
        Edge edge = new Edge(node1, node2);
        this.addEdge(edge);
    }

    @Override
    public boolean removeNode(Node node) {
        return this.nodes.remove(node);
    }

    @Override
    public boolean removeEdge(Edge edge) {
        return this.edges.remove(edge);
    }

    @Override
    public boolean removeEdge(Node node1, Node node2) {
        return this.removeEdge(new Edge(node1,node2));
    }

    public void draw(GraphicsContext context){
        for (Edge edge : edges) {
            edge.draw(context);
        }
        for (Node node : nodes) {
            node.draw(context);
        }
    }
}
