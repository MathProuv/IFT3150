import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controleur {
    private final Graph graph;
    private final Vue vue;

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

    public void moveNode(Node node, int xNew, int yNew){
        Node nodeReel = this.graph.getNodeFromPos(node);
        if (nodeReel != null){ // pas un new node
            nodeReel.move(xNew, yNew);
        }
    }

    public void removeNode(Node node){
        Node nodeReel = this.graph.getNodeFromPos(node);
        this.graph.removeNode(nodeReel);
    }

    public void addEdge(Node node1, Node node2){
        this.graph.addEdge(node1, node2);
    }

    public void addPerso(Node pos, boolean gentil){
        Node posReel = this.graph.getNodeFromPos(pos);
        try{
            if (posReel == null) {
                throw new Exception("cette position n'existe pas");
            } else if (this.getPerso(posReel) != null){
                throw new Exception("il y a déjà un perso ici");
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

    public Perso getPerso(Node pos){
        Node posReel = this.graph.getNodeFromPos(pos);
        if (posReel == null) {
            return null;
        }
        for (Perso perso : this.persos) {
            if (perso.pos == posReel){
                return perso;
            }
        }
        return null;
    }

    public void movePerso(Node pos, Node newPos){
        Node posReel = this.graph.getNodeFromPos(pos);
        Node newPosReel = this.graph.getNodeFromPos(newPos);
        Perso perso = this.getPerso(posReel);
        try {
            if (posReel == newPosReel) {
                throw new Exception("on ne bouge pas");
            } else if (perso == null){
                throw new Exception("on n'a rien à bouger");
            } else if (newPosReel == null) { // || posReel == null) { //déjà catché dans perso == null
                throw new Exception("cette position n'existe pas");
            } else if (!this.graph.isNeighbors(posReel,newPosReel)){
                throw new Exception("ces noeuds ne sont pas connectés");
            }
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        Perso perso2 = this.getPerso(newPosReel);
        perso.move(newPosReel);

        if (perso2 != null && perso.isGentil() != perso2.isGentil()){
            this.perdu();
        }
    }

    public void removePerso(Node pos){
        Perso perso = this.getPerso(pos);
        this.persos.remove(perso);
    }

    public void perdu(){
        this.vue.changeSceneToPerdu();
    }

    public void soutPerso(){
        for (Perso perso : this.persos) {
            System.out.println(perso);
        }
        System.out.println();
    }
}
