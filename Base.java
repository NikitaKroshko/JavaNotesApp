import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
                deleteButton.setOnAction(er ->
                    listBox.getChildren().remove(innerBox)
                );
                innerBox.getChildren().addAll(innerText, deleteButton);
                listBox.getChildren().add(0, innerBox);
                text.clear();
                listPane.setVvalue(0);
            }
        });

        Label titleLabel = new Label("Default Title");
        titleLabel.setFont(new Font(20));
        titleLabel.setAlignment(Pos.CENTER);
        VBox.setVgrow(titleLabel, Priority.ALWAYS);

        VBox titleContainer = new VBox();
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getChildren().add(titleLabel);

        Button changeTitleButton = new Button("Change Title");
        changeTitleButton.setOnAction(e -> {
            Stage titleStage = new Stage();
            VBox titleBox = new VBox(10);
            titleBox.setAlignment(Pos.CENTER);
            TextField titleField = new TextField();
            titleField.setPromptText("Enter new title...");
            Button setTitleButton = new Button("Set Title");
            setTitleButton.setOnAction(ev -> {
                String newTitle = titleField.getText().trim();
                if (!newTitle.isEmpty()) {
                    titleLabel.setText(newTitle);
                    titleStage.close();
                }
            });
            titleBox.getChildren().addAll(titleField, setTitleButton);
            Scene titleScene = new Scene(titleBox, 300, 150);
            titleStage.setScene(titleScene);
            titleStage.show();
        });

        topBox.getChildren().addAll(text, addButton, changeTitleButton);
        listPane.setContent(listBox);
        listPane.setFitToWidth(true);

        root.getChildren().addAll(titleContainer, topBox, listPane);

        return root;
    }
}
