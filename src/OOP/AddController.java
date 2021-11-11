package OOP;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class AddController
{
    @FXML
    private TextField nguoiguiField;
    @FXML
    private TextField nguoinhanField;
    @FXML
    private TextField diachiField;
    @FXML
    private TextField khoangcachField;
    @FXML
    private TextField cannangField;
    @FXML
    private ComboBox<String> loaiBox;
    @FXML
    private DatePicker ngayPicker;
    private ObservableList<Donhang> adddonhang=FXCollections.observableArrayList();
    private final ObservableList<String> loaihang=FXCollections.observableArrayList("Đường Bộ","Hàng Không");
    public void setLoaiBox()
    {
        loaiBox.setItems(loaihang);
    }
    private void themhangduongbo()
    {
        Donhang hangduongbo = new Duongbo(
                nguoiguiField.getText(),
                nguoinhanField.getText(),
                diachiField.getText(),
                Double.parseDouble(khoangcachField.getText()),
                Double.parseDouble(cannangField.getText()),
                ngayPicker.getValue());
        adddonhang.add(hangduongbo);
        nguoiguiField.clear();
        nguoinhanField.clear();
        diachiField.clear();
        khoangcachField.clear();
        cannangField.clear();
    }
    private void themhanghangkhong()
    {
        Donhang hanghangkhong = new Hangkhong(
                nguoiguiField.getText(),
                nguoinhanField.getText(),
                diachiField.getText(),
                Double.parseDouble(khoangcachField.getText()),
                Double.parseDouble(cannangField.getText()),
                ngayPicker.getValue());
        adddonhang.add(hanghangkhong);
        nguoiguiField.clear();
        nguoinhanField.clear();
        diachiField.clear();
        khoangcachField.clear();
        cannangField.clear();
    }
    public void addbutton(ActionEvent event)
    {
        if (nguoiguiField.getText().isEmpty() || nguoinhanField.getText().isEmpty() || diachiField.getText().isEmpty() ||
                khoangcachField.getText().isEmpty() || cannangField.getText().isEmpty() || ngayPicker.getValue()==null||
        loaiBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not add order");
            alert.setContentText("You have not entered complete data"+"\n"+
                                 "Please continue enter");
            alert.showAndWait();
            return;
        }
        if (khoangcachField.getText().matches(".*[a-zA-Z]+.*") || cannangField.getText().matches(".*[a-zA-Z]+.*")
                ||Double.parseDouble(khoangcachField.getText())<=0||Double.parseDouble(cannangField.getText())<=0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not add order");
            alert.setContentText("You entered the wrong data"+"\n"+
                                 "Please re-enter");
            alert.showAndWait();
            return;
        }
        else
        {
            if (loaiBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Đường Bộ")) {
                themhangduongbo();
            }
            if (loaiBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Hàng Không")) {
                themhanghangkhong();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Results:");
            alert.setContentText("Add the order successfully!");
            alert.showAndWait();
        }
        closeStage(event);
    }
    public void setAppMainObservableList (ObservableList<Donhang>tvObservableList)
    {
        this.adddonhang = tvObservableList;
    }
    private void closeStage (ActionEvent event)
    {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}