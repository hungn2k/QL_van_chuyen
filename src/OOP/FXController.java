package OOP;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class FXController implements Initializable
{
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
    private TableColumn<Donhang, String> chonColumn;
    @FXML
    private TableColumn<Donhang, LocalDate> ngayColumn;
    @FXML
    private TextField search1Field;
    @FXML
    private TextField search2Field;
    @FXML
    private CheckBox selectall;
    @FXML
    private ComboBox<Integer> boxMonth;
    @FXML
    private ComboBox<Integer> boxYear;
    @FXML
    private MenuBar fileMenu;
    ObservableList<Donhang> list = FXCollections.observableArrayList();
    DecimalFormat currency = new DecimalFormat("$0.00");
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nguoinhanColumn.setCellValueFactory(new PropertyValueFactory<>("nguoinhan"));
        nguoiguiColumn.setCellValueFactory(new PropertyValueFactory<>("nguoigui"));
        diachiColumn.setCellValueFactory(new PropertyValueFactory<>("diachi"));
        cannangColumn.setCellValueFactory(new PropertyValueFactory<>("cannang"));
        khoangcachColumn.setCellValueFactory(new PropertyValueFactory<>("khoangcach"));
        loaiColumn.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().loaihang()));
        chonColumn.setCellValueFactory(new PropertyValueFactory<>("chon"));
        table.setItems(list);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        chiphiColumn.setCellValueFactory(o -> {
            String formattedCost = currency.format(o.getValue().chiphi());
            return new SimpleStringProperty(formattedCost);
        });
        ngayColumn.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        selectall.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            for (Donhang item : table.getItems()) {
                if (selectall.isSelected())
                    item.getChon().setSelected(true);
                else
                    item.getChon().setSelected(false);
            }
        });
        ObservableList<Integer> arrmonth = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        boxMonth.setItems(arrmonth);
        ObservableList<Integer> arryear = FXCollections.observableArrayList(2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022);
        boxYear.setItems(arryear);
    }
    public void newbutton(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXadd.fxml"));
        Parent parent = fxmlLoader.load();
        AddController dialogController = fxmlLoader.<AddController>getController();
        dialogController.setLoaiBox();
        dialogController.setAppMainObservableList(list);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Add the order");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void listbutton(ActionEvent event) throws IOException
    {
        if(boxMonth.getSelectionModel().isEmpty()||boxYear.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not create list order");
            alert.setContentText("You have not selected");
            alert.showAndWait();
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("FXlist.fxml")));
            Parent parent = fxmlLoader.load();
            ListController listController = fxmlLoader.<ListController>getController();
            ObservableList<Donhang> doanhthu = FXCollections.observableArrayList(thunhap(boxMonth.getSelectionModel().getSelectedItem(),
                    boxYear.getSelectionModel().getSelectedItem()));
            listController.setListdonhang(doanhthu);
            listController.setDoanhthu(listController.doanhthu());
            listController.setThangNam(boxMonth.getSelectionModel().getSelectedItem(),boxYear.getSelectionModel().getSelectedItem());
            listController.initialize();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("List");
            stage.setScene(scene);
            stage.showAndWait();
            boxMonth.getSelectionModel().clearSelection();
            boxYear.getSelectionModel().clearSelection();
        }
    }
    public void updatebutton(ActionEvent event) throws IOException
    {
        if (list.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not update order");
            alert.setContentText("The list is empty");
            alert.showAndWait();
        }
        else
        {
            Donhang donhangselected = table.getSelectionModel().getSelectedItem();
            if (donhangselected == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("Can not update order");
                alert.setContentText("You have not selected the order");
                alert.showAndWait();
            }
            if (donhangselected != null)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXupdate.fxml"));
                Parent parent = fxmlLoader.load();
                UpdateController updateController = fxmlLoader.getController();
                updateController.setDonhang(donhangselected);
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Update the order");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                table.refresh();
                table.getSelectionModel().clearSelection();
            }
        }
    }
    public void deletebutton() {
        if (list.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not delete order");
            alert.setContentText("The list is empty");
            alert.showAndWait();
        }
        else
        {
            ObservableList<Donhang> selectRow = FXCollections.observableArrayList();
            int count = 0;
            for (Donhang donhang : list) {
                if (donhang.getChon().isSelected()) {
                    selectRow.add(donhang);
                    count++;
                }
            }
            list.removeAll(selectRow);
            if (count != 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Results:");
                alert.setContentText("Delete the order successfully!");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("Can not delete order");
                alert.setContentText("You have not selected an order");
                alert.showAndWait();
            }
        }
    }
    public void search1()
    {
        FilteredList<Donhang> filteredData = new FilteredList<>(list, p -> true);
        search1Field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(o -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (o.getNguoinhan().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else return o.getDiachi().toLowerCase().contains(lowerCaseFilter);
            });
            SortedList<Donhang> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });
    }
    public void search2() {
        FilteredList<Donhang> filteredData = new FilteredList<>(list, p -> true);
        if(search2Field.getText().matches(".*[a-zA-Z]+.*"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Can not search order");
            alert.setContentText("You entered the wrong data"+"\n"+
                                 "Please re-enter");
            alert.showAndWait();
            search2Field.clear();
        }
        else
        {
            search2Field.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(o -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    double n = Double.parseDouble(newValue);
                    return o.chiphi() > n;
                });
                SortedList<Donhang> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedData);
            });
        }
    }
    public void handleSave()
    {
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save The Order");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if (list.isEmpty())
        {
            secondaryStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "EMPTY TABLE", ButtonType.OK);
            emptyTableAlert.setContentText("You have nothing to save");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(this.fileMenu.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if (emptyTableAlert.getResult() == ButtonType.OK) {
                emptyTableAlert.close();
            }
        }
        else
        {
            File file = fileChooser.showSaveDialog(secondaryStage);
            if (file != null) {
                saveFile(table.getItems(), file);
            }
        }
    }
    public void saveFile(ObservableList<Donhang> observableList, File file)
    {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for (Donhang students : observableList) {
                outWriter.write("Đơn Hàng Số " + (1 + observableList.indexOf(students)) + ":" + "\n" + students.toString() + "\n");
                outWriter.newLine();
            }
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
            ioAlert.setContentText("Sorry. An error has occurred");
            ioAlert.showAndWait();
            if (ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
        }
    }
    public List<Donhang> thunhap(int thang, int nam)
    {
        return list.stream().filter(o->o.getNgay().getMonthValue()==thang&&o.getNgay().getYear()==nam).collect(Collectors.toList());
    }
}


































