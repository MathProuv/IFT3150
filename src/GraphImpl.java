import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;

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
    public boolean containsNode(Node node) {
        return this.getNodeFromPos(node) != null;
    }

    @Override
    public Node getNodeFromPos(Node pos) {
        if (pos == null) {
            return null;
        }
        for (Node nodeIn : nodes) {
            if (pos.isEqual(nodeIn)){
                return nodeIn;
            }
        }
        //System.out.println("on n'a pas trouvé le node");
        return null;
    }

    @Override
    public void addNode(Node node){
        if (!this.containsNode(node)){ // un node n'existait pas encore ici
            this.nodes.add(node);
            this.neighbors.put(node, new ArrayList<>());
        }
    }
    @Override
    public boolean isNeighbors(Node node1, Node node2){
        return this.neighbors.get(node1).contains(node2);
    }

    @Override
    public boolean containsEdge(Node node1, Node node2) {
        return this.neighbors.get(node1).contains(node2);
    }

    @Override
    public void addEdge(Node node1, Node node2){
        Node node1Reel = this.getNodeFromPos(node1);
        if (node1Reel == null){ //pas encore présent
            this.addNode(node1);
            node1Reel = node1;
        }
        Node node2Reel = this.getNodeFromPos(node2);
        if (node2Reel == null){ //pas encore présent
            this.addNode(node2);
            node2Reel = node2;
        }

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
    public boolean removeEdge(Node node1, Node node2) {
        return this.edges.remove(new Edge(node1,node2));
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
