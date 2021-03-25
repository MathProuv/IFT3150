import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controleur {
    private Graph graph;
    private Vue vue;

    private final ArrayList<Perso> persos = new ArrayList<>();

    public Controleur(Vue vue){
        this.graph = new GraphImpl();
        this.vue = vue;
    }

    public void draw(GraphicsContext context){
        context.setFill(Color.WHITE);
        context.fillRect(0,0,vue.widthCanvas,vue.heightCanvas);
        this.graph.draw(context);
        for (Perso perso : this.persos) {
            perso.draw(context);
        }
    }

    public void addNode(Node node){
        this.graph.addNode(node);
    }

    public void removeNode(Node node){
        Node nodeReel = this.graph.getNodeFromPos(node);
        if (node == nodeReel){ //new node

        } else {
            this.graph.removeNode(nodeReel);
        }
    }

    public void moveNode(Node node, int xNew, int yNew){
        Node nodeReel = this.graph.getNodeFromPos(node);
        if (node == nodeReel){ //new node

        } else {
            nodeReel.move(xNew, yNew);
        }
    }

    public void addEdge(Node node1, Node node2){
        this.graph.addEdge(node1, node2);
    }

    public void addPerso(Node pos, boolean gentil){
        Node posReel = this.graph.getNodeFromPos(pos);
        try{
            if (posReel == pos) {
                throw new Exception("cette position n'existe pas");
            }
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        Perso perso;
        if (gentil){
            perso = new Cop(posReel);
        } else {
            perso = new Robber(posReel);
        }
        this.persos.add(perso);
    }
    public void addCop(Node pos){
        this.addPerso(pos, true);
    }
    public void addRobber(Node pos){
        this.addPerso(pos, false);
    }
}
