package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane root;
    public AreaChart areaChart;
    public BarChart barchart;
    public CategoryAxis caId;
    public NumberAxis naSales;

    public void initialize(){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        //series.setName("Order Summary");
        series.getData().add(new XYChart.Data<>("P001", 6000));
        series.getData().add(new XYChart.Data<>("P002", 5000));
        series.getData().add(new XYChart.Data<>("P003", 4000));
        series.getData().add(new XYChart.Data<>("P004", 7000));

        barchart.getData().add(series);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        //series.setName("Order Summary");

        series2.getData().add(new XYChart.Data<>("P001", 1000));
        series2.getData().add(new XYChart.Data<>("P002", 3000));
        series2.getData().add(new XYChart.Data<>("P003", 2000));
        series2.getData().add(new XYChart.Data<>("P004", 4000));
        series2.getData().add(new XYChart.Data<>("P005", 3000));


        areaChart.getData().add(series2);


    }

    public void btnAddStudentOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/AddStudentForm.fxml")));

    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/AddRoomForm.fxml")));

    }

    public void btnReserveOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/ReserveRoomForm.fxml")));

    }

    public void btnSignOutOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/SignInForm.fxml")));
    }
}
