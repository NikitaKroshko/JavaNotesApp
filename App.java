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
        HBox topBox = new HBox(50);
        ScrollPane listPane = new ScrollPane();
        VBox listBox = new VBox(50);
        HBox labelWrapper = new HBox(20);
        Label label = new Label("Notes app!");
        labelWrapper.getChildren().add(label);
        labelWrapper.setAlignment(Pos.CENTER);
        root.getChildren().add(labelWrapper);
        

        TextField text = new TextField("");
        Button addButton = new Button("Add note to list");
        addButton.setOnAction(e -> {
            String userText = text.getCharacters().toString();
            if (!userText.isEmpty()) {
                HBox innerBox = new HBox(10);
                Label innerText = new Label(userText);
                Button deleteButton = new Button("X");
                deleteButton.setOnAction(er -> {
                    listBox.getChildren().remove(innerBox);
                });
                innerBox.getChildren().addAll(innerText, deleteButton);
                text.clear();

                listBox.getChildren().addFirst(innerBox);
                listPane.layout();
                listPane.setVvalue(0);
            }
        });

        topBox.setAlignment(Pos.CENTER);

        topBox.getChildren().addAll(text, addButton);

        listPane.setContent(listBox);

        root.getChildren().addAll(topBox, listPane);

        stage.setScene(scene);

        stage.show();    }

    public static void main(String[] args) {
        launch(args);
    }
}
