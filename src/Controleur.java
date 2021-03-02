import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        context.setFill(Color.WHITE);
        context.fillRect(0,0,vue.widthCanvas,vue.heightCanvas);
        this.graph.draw(context);
    }

    public void addNode(Node node){
        this.graph.addNode(node);
    }

    public void addEdge(Node node1, Node node2){
        this.graph.addEdge(node1, node2);
    }

    public void rmNode(Node node){
        this.graph.removeNode(node);
    }

    public void rmEdge(Edge edge){
        this.graph.removeEdge(edge);
    }
}
