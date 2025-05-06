import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage stage) {
        VBox root = new VBox(30);
        root.setStyle("-fx-background-color: #f0f8ff;");
        Scene scene = new Scene(root, 800, 650);

        HBox labelWrapper = new HBox();
        Label label = new Label("Notes app!");
        label.setFont(new Font("Arial", 24));
        label.setTextFill(Color.web("#4682b4"));
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
        navBar.setStyle("-fx-background-color: #d1e7f5;");
        navBarScrollPane.setContent(navBar);
        root.getChildren().add(navBarScrollPane);

        VBox contentWrapper = new VBox();
        root.getChildren().add(contentWrapper);

        VBox firstPage = Base.top();
        contentWrapper.getChildren().add(firstPage);

        Button firstPageButton = new Button("Pg 1");
        firstPageButton.setStyle(
            "-fx-background-color: #b0d4e3; -fx-text-fill: #2c5d73;"
        );
        firstPageButton.setOnAction(event ->
            contentWrapper.getChildren().setAll(firstPage)
        );
        navBar.getChildren().add(firstPageButton);

        Button addPageButton = new Button("+");
        addPageButton.setStyle(
            "-fx-background-color: #b0d4e3; -fx-text-fill: #2c5d73;"
        );
        navBar.getChildren().add(1, addPageButton);

        addPageButton.setOnAction(e -> {
            VBox newPage = Base.top();
            Button pageButton = new Button(
                "Pg " + (navBar.getChildren().size())
            );
            pageButton.setStyle(
                "-fx-background-color: #b0d4e3; -fx-text-fill: #2c5d73;"
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

        stage.setScene(scene);
        stage.setTitle("Notes App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
