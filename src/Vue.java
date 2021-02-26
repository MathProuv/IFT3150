import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Vue extends Application {
    private final int width = 640, height = 480;
    private final int widthCanvas = (int) (0.8*width), heightCanvas = (int) (0.8*height);
    private Stage primaryStage;

    private Canvas canvas = new Canvas(widthCanvas,heightCanvas);
    private GraphicsContext context = canvas.getGraphicsContext2D();

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Cops & Robbers");
        primaryStage.setScene(creerScene());
        Image icone = new Image("/images/police-man-caught-robber.jpg");
        primaryStage.getIcons().add(icone);
        primaryStage.show();
    }

    private Scene creerScene() {
        HBox root = new HBox();
        Scene scene = new Scene(root,width,height);

        VBox options = new VBox();
        CheckBox createButton = new CheckBox("Modification du graphe");
        createButton.setSelected(true); //default checked
        boolean createMode = createButton.isSelected();

        options.getChildren().add(createButton);

        root.getChildren().add(options);
        root.getChildren().add(canvas);

        context.setFill(Color.rgb(255, 0, 0, 0.7));
        context.drawImage(new Image("images/police-man-caught-robber.jpg",widthCanvas,heightCanvas,true,false),0,0);

        return scene;
    }
}
