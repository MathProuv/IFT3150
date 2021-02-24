public class Controleur {
    private Graph graph;
    private Vue vue;

    private int nbCops;
    private int nbRobbers = 1;

    public Controleur(){
        this.graph = new GraphImpl();
        this.vue = new Vue();
    }
}
