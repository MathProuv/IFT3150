import javafx.scene.canvas.GraphicsContext;

public class Controleur {
    private Graph graph;
    private Vue vue;

    private int nbCops;
    private int nbRobbers = 1;

    public Controleur(Vue vue){
        this.graph = new GraphImpl();
        this.vue = vue;
    }

    public void draw(GraphicsContext context){
        this.graph.draw(context);
    }

    public void addNode(Node node){
        this.graph.addNode(node);
    }

    public void addEdge(Edge edge){
        this.graph.addEdge(edge);
    }
}
