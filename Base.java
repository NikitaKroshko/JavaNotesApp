import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Base {

    public static VBox top() {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #e6f7ff;");

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
                saveNotesToFile(listBox);
                text.clear();
                listPane.setVvalue(0);
            }
        });

        Label titleLabel = new Label("Default Title");
        titleLabel.setFont(new Font(20));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setTextFill(Color.DARKBLUE);
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
            titleScene.getRoot().setStyle("-fx-background-color: #e6f7ff;");
            titleStage.setScene(titleScene);
            titleStage.show();
        });

        Button saveButton = new Button("Save Notes");
        saveButton.setOnAction(e -> saveNotesToFile(listBox));

        Button uploadButton = new Button("Upload Notes");
        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Notes File");
            fileChooser
                .getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Text Files", "*.jtxt"));
            Stage stage = (Stage) root.getScene().getWindow();
            var file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    List<String> lines = Files.readAllLines(
                        Paths.get(file.toURI())
                    );
                    listBox.getChildren().clear();
                    for (String line : lines) {
                        HBox innerBox = new HBox(10);
                        Label innerText = new Label(line);
                        Button deleteButton = new Button("X");
                        deleteButton.setOnAction(er ->
                            listBox.getChildren().remove(innerBox)
                        );
                        innerBox.getChildren().addAll(innerText, deleteButton);
                        listBox.getChildren().add(0, innerBox);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        topBox
            .getChildren()
            .addAll(
                text,
                addButton,
                changeTitleButton,
                saveButton,
                uploadButton
            );
        listPane.setContent(listBox);
        listPane.setFitToWidth(true);

        root.getChildren().addAll(titleContainer, topBox, listPane);

        return root;
    }

    private static void saveNotesToFile(VBox listBox) {
        try (
            BufferedWriter writer = new BufferedWriter(
                new FileWriter("jnotes.jtxt")
            )
        ) {
            for (var node : listBox.getChildren()) {
                if (node instanceof HBox) {
                    HBox hBox = (HBox) node;
                    for (var innerNode : hBox.getChildren()) {
                        if (innerNode instanceof Label) {
                            writer.write(((Label) innerNode).getText());
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
