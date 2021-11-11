package OOP;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class UpdateController
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
    private DatePicker ngayPicker;
    private Donhang donhang;
    public void setDonhang(Donhang donhang)
    {
        this.donhang=donhang;
        nguoiguiField.setText(donhang.getNguoigui());
        nguoinhanField.setText(donhang.getNguoinhan());
        diachiField.setText(donhang.getDiachi());
        khoangcachField.setText(Double.toString(donhang.getKhoangcach()));
        cannangField.setText(Double.toString(donhang.getCannang()));
        ngayPicker.setValue(donhang.getNgay());
    }
    public void updatebutton(ActionEvent event)
    {
        if (nguoiguiField.getText().isEmpty() || nguoinhanField.getText().isEmpty() || diachiField.getText().isEmpty() ||
                khoangcachField.getText().isEmpty() || cannangField.getText().isEmpty() || ngayPicker.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not edit order");
            alert.setContentText("You have not entered complete data"+"\n"+
                                 "Please re-enter");
            alert.showAndWait();
            return;
        }
        if (khoangcachField.getText().matches(".*[a-zA-Z]+.*") || cannangField.getText().matches(".*[a-zA-Z]+.*")
                ||Double.parseDouble(khoangcachField.getText())<=0||Double.parseDouble(cannangField.getText())<=0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not edit order");
            alert.setContentText("You entered the wrong data"+"\n"+
                                 "Please re-enter");
            alert.showAndWait();
            return;
        }
        else
        {
            donhang.setNguoigui(nguoiguiField.getText());
            donhang.setNguoinhan(nguoinhanField.getText());
            donhang.setDiachi(diachiField.getText());
            donhang.setKhoangcach(Double.parseDouble(khoangcachField.getText()));
            donhang.setCannang(Double.parseDouble(cannangField.getText()));
            donhang.setNgay(ngayPicker.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Results:");
            alert.setContentText("Edit the order successfully!");
            alert.showAndWait();
        }
        closeStage(event);
    }
    private void closeStage(ActionEvent event)
    {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
