public class Node {
    private String name;
    private int x, y;

    public Node(String name){
        this(0,0,name);
    }

    public Node(Integer x, Integer y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }
}
