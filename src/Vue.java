import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Vue extends Application {
    private final int width = 800, height = 600; // fenêtre
    public final int widthCanvas = Math.min(width,height), heightCanvas = widthCanvas;
    private final Controleur controleur = new Controleur(this);
    private Stage primaryStage;

    // on garde en mémoire la dernière position de l'entrée dans un click
    private Node node1;
    private boolean modifGraph;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Cops & Robbers - Menu");
        primaryStage.setResizable(false);
        primaryStage.setScene(creerSceneModif());
        Image icone = new Image("/images/police-man-caught-robber.jpg");
        primaryStage.getIcons().add(icone);
        primaryStage.show();
    }

    private Scene creerSceneModif() {
        primaryStage.setTitle("Cops & Robbers - Création du graphe");
        this.modifGraph = true;
        StackPane root = new StackPane();
        Scene scene = new Scene(root,width,height);

        VBox options = new VBox();
        CheckBox createButton = new CheckBox("Modification du graphe");
        createButton.setSelected(true); //default checked

        Button playButton = new Button("Jouer");
        playButton.setOnMouseClicked(click -> {
            this.modifGraph = false;;
            this.primaryStage.setScene(this.creerSceneInit());
        });

        options.getChildren().addAll(createButton, playButton);

        Canvas canvas = new Canvas(widthCanvas,heightCanvas);
        GraphicsContext context = canvas.getGraphicsContext2D();
        this.controleur.draw(context);

        root.getChildren().addAll(options,canvas);
        StackPane.setAlignment(options, Pos.CENTER_LEFT);
        StackPane.setAlignment(canvas, Pos.CENTER_RIGHT);

        canvas.setOnMousePressed(click -> {
            this.node1 = new Node((int) click.getX(), (int) click.getY());
            this.controleur.addNode(node1);
            this.controleur.draw(context);
        });
        canvas.setOnMouseReleased(click -> {
            Node node2 = new Node((int) click.getX(), (int) click.getY());
            if (!this.node1.isEqual(node2)) {
                this.controleur.addEdge(this.node1, node2);
            }
            this.controleur.draw(context);
        });

        return scene;
    }

    private Scene creerSceneInit() {
        primaryStage.setTitle("Cops & Robbers - Initialisation des personnages");
        StackPane root = new StackPane();
        Scene scene = new Scene(root,width,height);

        VBox options = new VBox();

        // Back to modification du graphe
        Button backButton = new Button("Modification du graphe");
        backButton.setOnMouseClicked(click -> {
            this.primaryStage.setScene(this.creerSceneModif());
        });

        // Jeu
        Button playButton = new Button("Je suis prêt.e");
        playButton.setOnMouseClicked(click -> {
            this.primaryStage.setScene(this.creerSceneJeu());
        });

        Text perso = new Text();
        perso.setText("Add perso");
        CheckBox persoGentil = new CheckBox("Si oui Cop, si non Robber");


        options.getChildren().addAll(backButton, perso, persoGentil, playButton);

        Canvas canvas = new Canvas(widthCanvas,heightCanvas);
        GraphicsContext context = canvas.getGraphicsContext2D();
        this.controleur.draw(context);

        canvas.setOnMouseClicked(click -> {
            Node pos = new Node((int) click.getX(), (int) click.getY());
            this.controleur.addPerso(pos, persoGentil.isSelected());
            this.controleur.draw(context);
        });

        root.getChildren().addAll(options,canvas);
        StackPane.setAlignment(options, Pos.CENTER_LEFT);
        StackPane.setAlignment(canvas, Pos.CENTER_RIGHT);

        return scene;
    }

    public Scene creerSceneJeu() {
        primaryStage.setTitle("Cops & Robbers");
        StackPane root = new StackPane();
        Scene scene = new Scene(root,width,height);

        VBox options = new VBox();

        // Back to modification du graphe
        Button backButton = new Button("Modification du graphe");
        backButton.setOnMouseClicked(click -> {
            this.primaryStage.setScene(this.creerSceneModif());
        });

        options.getChildren().addAll(backButton);

        Canvas canvas = new Canvas(widthCanvas,heightCanvas);
        GraphicsContext context = canvas.getGraphicsContext2D();
        this.controleur.draw(context);

        root.getChildren().addAll(options,canvas);
        StackPane.setAlignment(options, Pos.CENTER_LEFT);
        StackPane.setAlignment(canvas, Pos.CENTER_RIGHT);

        return scene;
    }
}
