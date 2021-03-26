import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Vue extends Application {
    private final int width = 800, height = 600; // fenêtre
    public final int widthCanvas = Math.min(width,height), heightCanvas = widthCanvas;
    private final Controleur controleur = new Controleur(this);
    private Stage primaryStage;
    private String title = "Cops & Robbers";

    private Canvas canvas;

    // on garde en mémoire la dernière position de l'entrée dans un click
    private Node node1;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.setScene(creerSceneModif());
        Image icone = new Image("/images/police-man-caught-robber.jpg");
        primaryStage.getIcons().add(icone);
        primaryStage.show();
    }


    Scene creerSceneModif() {
        this.title = "Cops & Robbers - Création du graphe";
        primaryStage.setTitle(this.title);
        StackPane root = new StackPane();
        Scene scene = new Scene(root,width,height);

        VBox options = new VBox();
        RadioButton ajoutButton = new RadioButton("Ajout de noeuds ou d'arêtes");
        RadioButton modifButton = new RadioButton("Modification de noeud");
        RadioButton suppButton = new RadioButton("Suppression de noeud");
        ToggleGroup mode = new ToggleGroup();
        ajoutButton.setToggleGroup(mode);
        modifButton.setToggleGroup(mode);
        suppButton.setToggleGroup(mode);
        ajoutButton.setSelected(true);

        Button playButton = new Button("Positionner les personnages");
        playButton.setOnMouseClicked(click -> this.primaryStage.setScene(this.creerSceneInitPerso()));

        options.getChildren().addAll(ajoutButton, modifButton, suppButton, playButton);

        Canvas canvas = new Canvas(widthCanvas,heightCanvas);
        this.canvas = canvas;
        GraphicsContext context = canvas.getGraphicsContext2D();
        this.controleur.draw(context);

        root.getChildren().addAll(options,canvas);
        StackPane.setAlignment(options, Pos.CENTER_LEFT);
        StackPane.setAlignment(canvas, Pos.CENTER_RIGHT);

        canvas.setOnMousePressed(click -> {
            this.node1 = new Node((int) click.getX(), (int) click.getY());
            if (!modifButton.isSelected() && !suppButton.isSelected()) {//Ajouter un noeud
                this.controleur.addNode(node1);
            }
            this.controleur.draw(context);
        });

        canvas.setOnMouseReleased(click -> {
            Node node2 = new Node((int) click.getX(), (int) click.getY());
            if (modifButton.isSelected()){ //Modifier un noeud
                this.controleur.moveNode(node1,(int) click.getX(), (int) click.getY());
            } else if(suppButton.isSelected()) { //Supprimer un noeud
                this.controleur.removeNode(node1);
            } else { //Ajouter un noeud
                if (!this.node1.isEqual(node2)) {
                    this.controleur.addEdge(this.node1, node2);
                }
            }
            this.controleur.draw(context);
        });

        return scene;
    }


    Scene creerSceneInitPerso() {
        this.title = "Cops & Robbers - Initialisation des personnages";
        primaryStage.setTitle(this.title);
        StackPane root = new StackPane();
        Scene scene = new Scene(root,width,height);

        VBox options = new VBox();

        // Back to modification du graphe
        Button backButton = new Button("Modification du graphe");
        backButton.setOnMouseClicked(click -> this.primaryStage.setScene(this.creerSceneModif()));

        // Jeu
        Button playButton = new Button("Je suis prêt.e");
        playButton.setOnMouseClicked(click -> this.primaryStage.setScene(this.creerSceneJeu()));

        Text perso = new Text();
        perso.setText("Add perso");
        CheckBox persoGentil = new CheckBox("Si oui Cop, si non Robber");


        options.getChildren().addAll(backButton, perso, persoGentil, playButton);

        Canvas canvas = new Canvas(widthCanvas,heightCanvas);
        this.canvas = canvas;
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

    Scene creerSceneJeu() {
        this.title = "Cops & Robbers";
        primaryStage.setTitle(this.title);
        StackPane root = new StackPane();
        Scene scene = new Scene(root,width,height);

        VBox options = new VBox();

        // Back to modification du graphe
        Button backButton = new Button("Modification du graphe");
        backButton.setOnMouseClicked(click -> this.primaryStage.setScene(this.creerSceneModif()));

        options.getChildren().addAll(backButton);

        Canvas canvas = new Canvas(widthCanvas,heightCanvas);
        this.canvas = canvas;
        GraphicsContext context = canvas.getGraphicsContext2D();
        this.controleur.draw(context);

        canvas.setOnMousePressed(click -> this.node1 = new Node((int) click.getX(), (int) click.getY()));
        canvas.setOnMouseReleased(click -> {
            Node node2 = new Node((int) click.getX(), (int) click.getY());
            //Perso perso1 = this.controleur.getPerso(this.node1);
            this.controleur.movePerso(node1,node2);
            this.controleur.draw(context);
        });

        root.getChildren().addAll(options,canvas);
        StackPane.setAlignment(options, Pos.CENTER_LEFT);
        StackPane.setAlignment(canvas, Pos.CENTER_RIGHT);

        return scene;
    }
}
