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
import javafx.scene.text.Font;

public class App extends Application {

    public void start(Stage stage) {
        VBox root = new VBox(100);
        Scene scene = new Scene(root, 800, 650);
        Label title = new Label("Notes app!");
        title.setFont(new Font("Arial", 24));
        HBox titleWrapper = new HBox();
        titleWrapper.getChildren().add(title);
        titleWrapper.setAlignment(Pos.CENTER);
        root.getChildren().add(titleWrapper);

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
