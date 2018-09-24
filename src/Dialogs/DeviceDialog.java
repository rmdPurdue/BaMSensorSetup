package Dialogs;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import util.AnalogInput;
import util.InputTableViewHelper;
import util.RemoteDevice;

import java.util.Optional;

/**
 * @author Rich Dionne
 * @project BaMSensorSetup
 * @package Dialogs
 * @date 9/22/2018
 */
public class DeviceDialog {

    public static void deviceDialog(RemoteDevice device) {
        Dialog<RemoteDevice> configureDeviceDialog = new Dialog<>();
        configureDeviceDialog.setTitle("Configure Device");
        configureDeviceDialog.setHeaderText("Configure this device.");
        configureDeviceDialog.setResizable(true);
        ButtonType okButtonType = ButtonType.OK;
        configureDeviceDialog.getDialogPane().getButtonTypes().add(okButtonType);
        configureDeviceDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        TextField input1FilterWeight = new TextField();
        TextField input2FilterWeight = new TextField();
        TextField input3FilterWeight = new TextField();
        TextField input4FilterWeight = new TextField();
        TextField input5FilterWeight = new TextField();
        TextField input6FilterWeight = new TextField();

        TableView<AnalogInput> inputTable = new TableView<>();
        inputTable.setEditable(true);
        inputTable.setRowFactory(tv -> {
            TableRow<AnalogInput> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    AnalogInput inputData = row.getItem();
                    System.out.println(inputData.getMinValue());
                }
            });
            return row;
        });

        inputTable.getItems().addAll(InputTableViewHelper.getInputList(device));
        inputTable.getColumns().addAll(
                InputTableViewHelper.getInputNumberColumn(),
                InputTableViewHelper.getMinValueColumn(),
                InputTableViewHelper.getMaxValueColumn(),
                InputTableViewHelper.getFilterWeightColumn(),
                InputTableViewHelper.getCalibrateMinValueColumn());

        inputTable.setPlaceholder(new Label("No input settings for this device."));

        GridPane grid = new GridPane();
/*        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Configure " + device.getDeviceName() + "."),0,0);
        grid.add(new Label("Input 1"),0,1);

        grid.add(new Label("Minimum Reading"),0,2);
        grid.add(new Label("Current value:"), 1,2);
        grid.add(new Label(String.valueOf(device.getAnalogInput(0).getMinValue())),2,2);
        grid.add(new Label("New value:"),3,2);
        grid.add(new Label("Button to run new average."),4,2);

        grid.add(new Label("Maximum Reading"), 0,3);
        grid.add(new Label("Current value:"), 1,3);
        grid.add(new Label(String.valueOf(device.getAnalogInput(0).getMaxValue())),2,3);
        grid.add(new Label("New value:"),3,3);
        grid.add(new Label("Button to run new average."),4,3);

        grid.add(new Label("FilterWeight"), 0,4);
        grid.add(new Label("Current value:"), 1,4);
        grid.add(new Label(String.valueOf(device.getAnalogInput(0).getFilterWeight())),2,4);
        grid.add(new Label("New value:"),3,4);
        grid.add(input1FilterWeight,4,4);
*/

        grid.add(inputTable,0,1);
        configureDeviceDialog.getDialogPane().setContent(grid);

        Optional<RemoteDevice> result = configureDeviceDialog.showAndWait();
    }
}
