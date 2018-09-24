package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

/**
 * @author Rich Dionne
 * @project BaMSensorSetup
 * @package util
 * @date 9/21/2018
 */
public class DeviceTableViewHelper {

    public static ObservableList<RemoteDevice> getDeviceList(Vector<RemoteDevice> devices) {
/*        RemoteDevice device1 = new RemoteDevice(InetAddress.getByName("10.101.1.4"), "f8f50259af", "knee1");
        RemoteDevice device2 = new RemoteDevice(InetAddress.getByName("10.101.1.5"), "f8f005297A", "knee2");

        device1.addAnalogInput(1,100, 550, 30);
        device2.addAnalogInput(1,150, 600, 10);

        return FXCollections.observableArrayList(device1, device2);
        */
        if(!devices.isEmpty()) {
            System.out.println("Empty.");
            return FXCollections.observableArrayList(devices);
        } else {
            return null;
        }
    }

    public static TableColumn<RemoteDevice, InetAddress> getIPAddressColumn() {
        TableColumn<RemoteDevice, InetAddress> IPAddressColumn = new TableColumn<>("IP Address");
        PropertyValueFactory<RemoteDevice, InetAddress> IPAddressCellFactory = new PropertyValueFactory<>("ipAddress");
        IPAddressColumn.setCellValueFactory(IPAddressCellFactory);
        return IPAddressColumn;
    }

    public static TableColumn<RemoteDevice, String> getMacAddressColumn() {
        TableColumn<RemoteDevice, String> macAddressColumn = new TableColumn<>("MAC Address");
        PropertyValueFactory<RemoteDevice, String> macAddressCellFactory = new PropertyValueFactory<>("macAddress");
        macAddressColumn.setCellValueFactory(macAddressCellFactory);
        return macAddressColumn;
    }

    public static TableColumn<RemoteDevice, String> getNameColumn() {
        TableColumn<RemoteDevice, String> nameColumn = new TableColumn<>("Device Name");
        PropertyValueFactory<RemoteDevice, String> nameCellFactory = new PropertyValueFactory<>("deviceName");
        nameColumn.setCellValueFactory(nameCellFactory);
        return nameColumn;
    }

}
