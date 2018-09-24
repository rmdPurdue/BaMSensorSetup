package mv;

import Dialogs.DeviceDialog;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import util.DeviceTableViewHelper;
import util.RemoteDevice;

import java.net.UnknownHostException;
import java.util.Vector;

public class View {
    public Scene scene;
    public Label statusDisplay = new Label("");
    public Label networkStatusDisplay = new Label("");
    private Vector<RemoteDevice> devices = new Vector<>();

    public View() throws UnknownHostException {
        BorderPane window = new BorderPane();
        window.setTop(buildHeader());
        window.setLeft(buildLeft());
        window.setCenter(buildDisplay());
        window.setBottom(buildFooter());

        scene = new Scene(window, 800, 600);
        scene.getStylesheets().add("Stylesheets/appStyleSheet.css");
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

    VBox buildLeft() {
        Label ipAddressLabel = new Label("IP Address: xxx.xxx.xxx.xxx");
        Label ipStatusLabel = new Label("Connected?");

        ipAddressLabel.setFont(new Font("Arial", 16));
        ipAddressLabel.setPadding(new Insets(10,10,10,10));

        ipStatusLabel.setFont(new Font("Arial", 16));
        ipStatusLabel.setPadding(new Insets(10,10,10,10));

        VBox leftSide = new VBox();
        leftSide.setSpacing(10);
        leftSide.setPadding(new Insets(10,10,10,10));
        leftSide.getChildren().addAll(ipAddressLabel, ipStatusLabel);

        return leftSide;
    }

    GridPane buildDisplay() throws UnknownHostException {
        TableView<RemoteDevice> deviceTableView = new TableView<>();

        deviceTableView.setRowFactory(tv -> {
            TableRow<RemoteDevice> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    RemoteDevice deviceData = row.getItem();
                    System.out.println(deviceData.getDeviceName());
                    DeviceDialog.deviceDialog(deviceData);
                }
            });
            return row;
        });

        if(!devices.isEmpty()) {
            deviceTableView.getItems().addAll(DeviceTableViewHelper.getDeviceList(devices));
            deviceTableView.getColumns().addAll(
                    DeviceTableViewHelper.getIPAddressColumn(),
                    DeviceTableViewHelper.getMacAddressColumn(),
                    DeviceTableViewHelper.getNameColumn());
        }

        deviceTableView.setPlaceholder(new Label("No devices found on network."));
//        RemoteDevice[] tempDevices = { new RemoteDevice() };

        /*
        // Set up Cue Stack Table
        deviceTableView.setRowFactory(rowFactory -> {
            TableRow<RemoteDevice> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    RemoteDevice rowData = row.getItem();
                    tempDevices[0] = editDeviceDialog(rowData);
                    deviceTableView.refresh();
                }
            });
            return row;
        });
        deviceTableView.setEditable(true);
*/

        GridPane body = new GridPane();
        body.getChildren().addAll(deviceTableView);
        body.setPadding(new Insets(10,10,10,10));

        return body;
    }

    HBox buildFooter() {
        HBox footer = new HBox();
        footer.setSpacing(10);
        footer.getChildren().addAll(statusDisplay, networkStatusDisplay);
        return footer;
    }

    private RemoteDevice editDeviceDialog(RemoteDevice device) {
        return device;
    }

    public void setDevices(Vector<RemoteDevice> devices) {
        this.devices = devices;
    }



}
