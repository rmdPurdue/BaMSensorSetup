import javafx.application.Application;
import javafx.stage.Stage;
import mv.Model;
import mv.View;

import java.io.IOException;


public class main extends Application {
    private Model model;
    private View view;
    private Stage stage;

    public static void main(String[] args) { Application.launch(args); }

    @Override
    public void start(Stage stage) throws Exception {
        model = new Model();
        view = new View();
        this.stage = stage;

        this.stage.setTitle("Bodies as Music Device Configuration");
        this.stage.sizeToScene();
        this.stage.setScene(view.scene);
        this.stage.setResizable(false);
        this.stage.show();

        model.discoverDevices();

    }


}
