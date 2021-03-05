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
        return node == this.getNodeFromPos(node);
    }

    @Override
    public Node getNodeFromPos(Node pos) {
        for (Node nodeIn : nodes) {
            if (pos.isEqual(nodeIn)){
                return nodeIn;
            }
        }
        //System.out.println("on n'a pas trouvé le node");
        return pos;
    }

    @Override
    public void addNode(Node node){
        if (node == this.getNodeFromPos(node)){ // un node existait déjà ici
            this.nodes.add(node);
            this.neighbors.put(node, new ArrayList<>());
        }
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
            this.addEdge(node1, node2);
        }
    }

    @Override
    public void addEdge(Node node1, Node node2){
        Node node1Reel = this.getNodeFromPos(node1);
        if (node1Reel == node1){ this.addNode(node1); }
        Node node2Reel = this.getNodeFromPos(node2);
        if (node2Reel == node2){ this.addNode(node2); }

        this.neighbors.get(node1Reel).add(node2Reel);
        this.neighbors.get(node2Reel).add(node1Reel); // Graphe non-orienté
        this.edges.add(new Edge(node1Reel,node2Reel));
    }

    @Override
    public boolean removeNode(Node node) {
        for (Node nodeIn : this.nodes) {
            this.neighbors.get(nodeIn).remove(node);
        }
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
        for (Node node : nodes) {
            node.draw(context);
            for (Node neighbor : neighbors.get(node)) {
                new Edge(node,neighbor).draw(context);
            }
        }
    }
}
