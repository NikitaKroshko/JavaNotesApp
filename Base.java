import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.geometry.Pos; 
import javafx.scene.text.Font;

public class Base {
    public static VBox top() {
        VBox root = new VBox(20);

        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER);

        ScrollPane listPane = new ScrollPane();
        VBox listBox = new VBox(10);

        TextField text = new TextField();
        text.setPromptText("Enter your note here...");
        Button addButton = new Button("Add note to list");

        addButton.setOnAction(e -> {
            String userText = text.getText().trim();
            if (!userText.isEmpty()) {
                HBox innerBox = new HBox(10);
                Label innerText = new Label(userText);
                Button deleteButton = new Button("X");
                deleteButton.setOnAction(er -> listBox.getChildren().remove(innerBox));
                innerBox.getChildren().addAll(innerText, deleteButton);
                listBox.getChildren().add(0, innerBox); 
                text.clear();
                listPane.setVvalue(0); 
            }
        });

        topBox.getChildren().addAll(text, addButton);
        listPane.setContent(listBox);
        listPane.setFitToWidth(true);

        root.getChildren().addAll(topBox, listPane);

        return root;
    }
}
