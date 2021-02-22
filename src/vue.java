import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class vue extends Application {
    private Stage primaryStage;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Cops & Robbers");
        primaryStage.setScene(creerSceneMenu());
        primaryStage.show();
    }

    private Scene creerSceneMenu() {
        VBox rootMenu = new VBox();
        Scene sceneMenu = new Scene(rootMenu);

        return sceneMenu;
    }
}
