import javafx.application.Application;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage stage) {
        VBox root = new VBox(30);
        Scene scene = new Scene(root, 800, 650);

        HBox labelWrapper = new HBox();
        Label label = new Label("Notes app!");
        label.setFont(new Font("Arial", 24));
        labelWrapper.getChildren().add(label);
        labelWrapper.setAlignment(Pos.CENTER);
        root.getChildren().add(labelWrapper);

        HBox navBar = new HBox(10);
        navBar.setAlignment(Pos.CENTER);
        root.getChildren().add(navBar);

        VBox contentWrapper = new VBox();
        root.getChildren().add(contentWrapper);

        VBox[] pages = new VBox[10];
        for (int i = 0; i < 10; i++) {
            pages[i] = Base.top();

            Button pageButton = new Button("Pg " + (i + 1));
            int index = i;
            pageButton.setOnAction(e -> {
                contentWrapper.getChildren().setAll(pages[index]);
            });

            navBar.getChildren().add(pageButton);
        }

        contentWrapper.getChildren().add(pages[0]);

        stage.setScene(scene);
        stage.setTitle("Notes App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
