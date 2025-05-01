import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage stage) {
        VBox root = new VBox(100);
        Scene scene = new Scene(root, 800, 650);
        Label title = new Label("Notes app!");
        root.getChildren().add(title);

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
