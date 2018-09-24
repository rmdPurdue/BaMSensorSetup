import javafx.application.Application;
import javafx.stage.Stage;
import mv.Model;
import mv.View;
import util.RemoteDevice;

import java.io.IOException;
import java.util.Vector;


public class main extends Application {
    private Model model;
    private View view;
    private Stage stage;

    public static void main(String[] args) { Application.launch(args); }

    @Override
    public void start(Stage stage) throws Exception {
        this.model = new Model();
        this.model.discoverDevices();

        this.view = new View();
        this.stage = stage;

        hookupConnections();

        this.stage.setTitle("Bodies as Music Device Configuration");
        this.stage.sizeToScene();
        this.stage.setScene(view.scene);
        this.stage.setResizable(false);
        this.stage.show();
    }

    private void hookupConnections() {
        view.setDevices(model.getDevices());
    }


}
