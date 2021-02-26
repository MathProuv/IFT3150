import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Vue extends Application {
    private final int width = 800, height = 600;
    private final double rapport = width/height;
    private final int widthCanvas = Math.min(width,height), heightCanvas = widthCanvas;
    private Stage primaryStage;
    private Controleur controleur = new Controleur(this);

    private final Canvas canvas = new Canvas(widthCanvas,heightCanvas);
    private final GraphicsContext context = canvas.getGraphicsContext2D();

    // Pour ajouter des positions, on garde en mémoire la dernière (cf drag)
    private Node node1;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Cops & Robbers");
        primaryStage.setResizable(false);
        primaryStage.setScene(creerScene());
        Image icone = new Image("/images/police-man-caught-robber.jpg");
        primaryStage.getIcons().add(icone);
        primaryStage.show();
    }

    private Scene creerScene() {
        StackPane root = new StackPane();
        Scene scene = new Scene(root,width,height);

        VBox options = new VBox();
        CheckBox createButton = new CheckBox("Modification du graphe");
        createButton.setSelected(true); //default checked

        options.getChildren().add(createButton);

        root.getChildren().addAll(options,canvas);
        StackPane.setAlignment(options, Pos.CENTER_LEFT);
        StackPane.setAlignment(canvas, Pos.CENTER_RIGHT);

        context.setFill(Color.WHITE);
        context.fillRect(0,0,widthCanvas,heightCanvas);

        canvas.setOnMouseClicked(click -> {
            if (createButton.isSelected()) {
                double x = click.getX(), y = click.getY();
                controleur.addNode(new Node((int)x,(int)y));
            }
            this.controleur.draw(context);
        });

        canvas.setOnDragDetected(drag -> {
            System.out.println("drag detected");
        });

        canvas.setOnMouseDragEntered(drag -> {
            if (createButton.isSelected()){
                this.node1 = new Node((int)drag.getX(), (int)drag.getY());
                this.controleur.addNode(node1);
                System.out.println("on entre");
            }
        });
        canvas.setOnMouseDragExited(drag -> {
            if (createButton.isSelected()){
                double x2 = drag.getX(), y2 = drag.getY();
                Node node2 = new Node((int)x2, (int)y2);
                Edge edge = new Edge(this.node1,node2);
                this.controleur.addEdge(edge);
            }
            this.controleur.draw(context);
            System.out.println("on sort");
        });



        return scene;
    }
}
