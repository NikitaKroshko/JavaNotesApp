import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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

        ScrollPane navBarScrollPane = new ScrollPane();
        navBarScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        navBarScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        navBarScrollPane.setFitToHeight(true);
        navBarScrollPane.setFitToWidth(false);

        HBox navBar = new HBox(10);
        navBar.setAlignment(Pos.CENTER);
        navBarScrollPane.setContent(navBar);
        root.getChildren().add(navBarScrollPane);

        VBox contentWrapper = new VBox();
        root.getChildren().add(contentWrapper);

        VBox firstPage = Base.top();
        contentWrapper.getChildren().add(firstPage);

        Button firstPageButton = new Button("Pg 1");
        firstPageButton.setOnAction(event ->
            contentWrapper.getChildren().setAll(firstPage)
        );
        navBar.getChildren().add(firstPageButton);

        Button addPageButton = new Button("+");
        navBar.getChildren().add(1, addPageButton);

        addPageButton.setOnAction(e -> {
            VBox newPage = Base.top();
            TextArea textArea = new TextArea();
            textArea.setPromptText("Enter your notes here...");
            newPage.getChildren().add(textArea);
            Button pageButton = new Button(
                "Pg " + (navBar.getChildren().size())
            );
            pageButton.setOnAction(event -> {
                if (!contentWrapper.getChildren().contains(newPage)) {
                    contentWrapper.getChildren().setAll(newPage);
                }
            });
            navBar
                .getChildren()
                .add(navBar.getChildren().size() - 1, pageButton);
        });

        Button saveButton = new Button("Save");
        navBar.getChildren().add(saveButton);

        saveButton.setOnAction(e -> {
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < contentWrapper.getChildren().size(); i++) {
                VBox page = (VBox) contentWrapper.getChildren().get(i);
                page
                    .getChildren()
                    .forEach(node -> {
                        if (node instanceof TextArea) {
                            content
                                .append("- ")
                                .append(((TextArea) node).getText())
                                .append("\n");
                        }
                    });
                content.append("---\n");
            }
            try (
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("notes.jtxt")
                )
            ) {
                writer.write(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        stage.setScene(scene);
        stage.setTitle("Notes App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
