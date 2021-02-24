import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Vue extends Application {
    private final int width = 640, height = 1024;
    private Stage primaryStage;

    private Canvas canvas = new Canvas(width, height);
    private GraphicsContext context = canvas.getGraphicsContext2D();

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Cops & Robbers");
        primaryStage.setScene(creerSceneMenu());
        primaryStage.show();
    }

    private Scene creerSceneMenu() {
        HBox rootMenu = new HBox();
        Scene sceneMenu = new Scene(rootMenu);
        CheckBox create = new CheckBox("Modification du graphe");
        create.setSelected(true);
        boolean createMode = create.isSelected();


        return sceneMenu;
    }
}
