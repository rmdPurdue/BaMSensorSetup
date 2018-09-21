package mv;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class View {
    public Scene scene;

    public Label statusDisplay = new Label("");
    public Label networkStatusDisplay = new Label("");

    public View() {
        BorderPane window = new BorderPane();
        window.setTop(buildHeader());
        window.setCenter(buildDisplay());
        window.setBottom(buildFooter());

        scene = new Scene(window);
    }

    VBox buildHeader() {
        Label title = new Label("Bodies as Music Device Configuration");
        title.setFont(new Font("Arial", 24));
        title.setPadding(new Insets(5,1,5,20));

        VBox header = new VBox();
        header.setSpacing(10);
        header.setPadding(new Insets(5,0,20,0));
        header.getChildren().addAll(title);
        return header;
    }

    GridPane buildDisplay() {
        return new GridPane();
    }

    HBox buildFooter() {
        HBox footer = new HBox();
        footer.setSpacing(10);
        footer.getChildren().addAll(statusDisplay, networkStatusDisplay);
        return footer;
    }



}
