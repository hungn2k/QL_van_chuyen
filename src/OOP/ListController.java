package OOP;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.DecimalFormat;
import java.time.LocalDate;
public class ListController {
    @FXML
    private TableView<Donhang> table;
    @FXML
    private TableColumn<Donhang, String> nguoiguiColumn;
    @FXML
    private TableColumn<Donhang, String> nguoinhanColumn;
    @FXML
    private TableColumn<Donhang, String> diachiColumn;
    @FXML
    private TableColumn<Donhang, Double> cannangColumn;
    @FXML
    private TableColumn<Donhang, Double> khoangcachColumn;
    @FXML
    private TableColumn<Donhang, String> chiphiColumn;
    @FXML
    private TableColumn<Donhang, String> loaiColumn;
    @FXML
    private TableColumn<Donhang, LocalDate> ngayColumn;
    @FXML
    private Label thunhapLabel;
    @FXML
    private Label thangnamLabel;
    DecimalFormat currency = new DecimalFormat("$0.00");
    private ObservableList<Donhang> listdonhang = FXCollections.observableArrayList();
    double tongdoanhthu;
    int thang,nam;
    public void initialize()
    {
        nguoiguiColumn.setCellValueFactory(new PropertyValueFactory<>("nguoigui"));
        nguoinhanColumn.setCellValueFactory(new PropertyValueFactory<>("nguoinhan"));
        diachiColumn.setCellValueFactory(new PropertyValueFactory<>("diachi"));
        cannangColumn.setCellValueFactory(new PropertyValueFactory<>("cannang"));
        khoangcachColumn.setCellValueFactory(new PropertyValueFactory<>("khoangcach"));
        loaiColumn.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().loaihang()));
        ngayColumn.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        chiphiColumn.setCellValueFactory(o -> {
            String formattedCost = currency.format(o.getValue().chiphi());
            return new SimpleStringProperty(formattedCost);
        });
        table.setItems(listdonhang);
        thunhapLabel.setText("$"+ tongdoanhthu);
        thangnamLabel.setText("("+ thang +"/"+ nam +")"+":");
    }
    public void setListdonhang(ObservableList<Donhang>t)
    {
        this.listdonhang=t;
    }
    public double doanhthu()
    {
        double n=0.0d;
        for(Donhang donhang:listdonhang)
        {
            n+=donhang.chiphi();
        }
        return n;
    }
    public void setDoanhthu(double tongdoanhthu)
    {
        this.tongdoanhthu=tongdoanhthu;
    }
    public void setThangNam(int thang,int nam)
    {
        this.thang=thang;
        this.nam=nam;
    }
}
