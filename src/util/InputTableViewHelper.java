package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Rich Dionne
 * @project BaMSensorSetup
 * @package util
 * @date 9/21/2018
 */
public class InputTableViewHelper {

    public static ObservableList<AnalogInput> getInputList(RemoteDevice device) {
        return FXCollections.observableArrayList(device.getAnalogInputs());
    }

    public static TableColumn<AnalogInput, Integer> getInputNumberColumn() {
        TableColumn<AnalogInput, Integer> inputNumberColumn = new TableColumn<>("Input");
        PropertyValueFactory<AnalogInput, Integer> inputNumberCellFactory = new PropertyValueFactory<>("inputNumber");
        inputNumberColumn.setCellValueFactory(inputNumberCellFactory);
        return inputNumberColumn;
    }

    public static TableColumn<AnalogInput, Integer> getMinValueColumn() {
        TableColumn<AnalogInput, Integer> minValueColumn = new TableColumn<>("Minimum Value");
        PropertyValueFactory<AnalogInput, Integer> minValueCellFactory = new PropertyValueFactory<>("minValue");
        minValueColumn.setCellValueFactory(minValueCellFactory);
        return minValueColumn;
    }

    public static TableColumn<AnalogInput, Integer> getMaxValueColumn() {
        TableColumn<AnalogInput, Integer> maxValueColumn = new TableColumn<>("Maximum Value");
        PropertyValueFactory<AnalogInput, Integer> maxValueCellFactory = new PropertyValueFactory<>("maxValue");
        maxValueColumn.setCellValueFactory(maxValueCellFactory);
        return maxValueColumn;
    }

    public static TableColumn<AnalogInput, String> getFilterWeightColumn() {
        TableColumn<AnalogInput, String> filterWeightColumn = new TableColumn<>("Filter Weight");
        PropertyValueFactory<AnalogInput, String> filterWeightCellFactory = new PropertyValueFactory<>("filterWeightString");
        filterWeightColumn.setCellValueFactory(filterWeightCellFactory);
        filterWeightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        filterWeightColumn.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setFilterWeight(Integer.parseInt(t.getNewValue()));
                    // Call method to send calibration OSC message to this device.
                    // Listen for OSC message with updated calibration data.
                }
        );
        return filterWeightColumn;
    }

    public static TableColumn<AnalogInput, Void> getCalibrateMinValueColumn() {
        TableColumn colBtn = new TableColumn("Calibrate");

        Callback<TableColumn<AnalogInput, Void>, TableCell<AnalogInput, Void>> cellFactory = new Callback<TableColumn<AnalogInput, Void>, TableCell<AnalogInput, Void>>() {
            @Override
            public TableCell<AnalogInput, Void> call(final TableColumn<AnalogInput, Void> param) {
                final TableCell<AnalogInput, Void> cell = new TableCell<AnalogInput, Void>() {

                    private final Button minBtn = new Button("Minimum");

                    {
                        minBtn.setOnAction((ActionEvent event) -> {
                            AnalogInput data = getTableView().getItems().get(getIndex());
                            System.out.println("Minimum for input " + data.getInputNumber());
                            // Call method to send calibration OSC message to this device.
                            // Listen for OSC message with updated calibration data.
                        });
                    }

                    private final Button maxBtn = new Button("Maximum");

                    {
                        maxBtn.setOnAction((ActionEvent event) -> {
                            AnalogInput data = getTableView().getItems().get(getIndex());
                            System.out.println("Maximum for input " + data.getInputNumber());
                            // Call method to send calibration OSC message to this device.
                            // Listen for OSC message with updated calibration data.
                        });
                    }

                    HBox btnPane = new HBox(minBtn, maxBtn);

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnPane);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        return colBtn;
    }

}
