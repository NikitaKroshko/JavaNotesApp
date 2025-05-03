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
          HBox labelWrapper = new HBox(20);
        Label label = new Label("Notes app!");
        label.setFont(new Font("Ariel", 20));
        labelWrapper.getChildren().add(label);
        labelWrapper.setAlignment(Pos.CENTER);
        root.getChildren().add(labelWrapper);
        
        VBox main = Base.top();

        root.getChildren().addAll(main);

        stage.setScene(scene);

        stage.show(); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}
