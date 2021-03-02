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
    private final int width = 800, height = 600; // fenêtre
    public final int widthCanvas = Math.min(width,height), heightCanvas = widthCanvas;
    private final Controleur controleur = new Controleur(this);
    private Stage primaryStage;

    private final Canvas canvas = new Canvas(widthCanvas,heightCanvas);
    private final GraphicsContext context = canvas.getGraphicsContext2D();

    // on garde en mémoire la dernière position de l'entrée dans un click
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


        canvas.setOnMousePressed(click -> {
            if (createButton.isSelected()){
                this.node1 = new Node((int) click.getX(), (int) click.getY());
                this.controleur.addNode(node1);
                this.controleur.draw(context);
            }
        });
        canvas.setOnMouseReleased(click -> {
            if (createButton.isSelected()) {
                Node node2 = new Node((int) click.getX(), (int) click.getY());
                if (!this.node1.isEqual(node2)) {
                    this.controleur.addEdge(this.node1, node2);
                }
                this.controleur.draw(context);
            }
        });

        return scene;
    }
}
