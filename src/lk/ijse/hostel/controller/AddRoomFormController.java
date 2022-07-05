package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.view.tm.RoomTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddRoomFormController {
    public JFXTextField txtCode;
    public JFXTextField txtType;
    public JFXTextField txtMoney;
    public JFXTextField txtQty;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public JFXButton btnAddNewRoom;
    public TableView<RoomTM> tblRooms;
    public AnchorPane roomContext;
   // private RoomDAO roomDAO = new RoomDAOImpl();

   // private RoomBO roomBO = new RoomBOImpl();
   RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);


    public void initialize() {

        tblRooms.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblRooms.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRooms.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRooms.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));

        initUI();

        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCode.setText(newValue.getCode());
                txtType.setText(newValue.getType());
                txtMoney.setText(newValue.getKeyMoney().setScale(2).toString());
                txtQty.setText(newValue.getQty() + "");

                txtCode.setDisable(false);
                txtType.setDisable(false);
                txtMoney.setDisable(false);
                txtQty.setDisable(false);
            }
        });

        txtQty.setOnAction(event -> btnSave.fire());
        loadAllItems();
    }

    private void loadAllItems() {
        tblRooms.getItems().clear();
        try {
          //  Get all items

            List<RoomDTO> allRooms = roomBO.getAllItems();

          //  ArrayList<RoomDTO> allRooms = roomDAO.getAll();
            for(RoomDTO room : allRooms){
                tblRooms.getItems().add(new RoomTM(room.getCode(), room.getType(), room.getKeyMoney(), room.getQty()));

            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtCode.clear();
        txtType.clear();
        txtMoney.clear();
        txtQty.clear();
       // txtCode.setDisable(true);
        txtType.setDisable(true);
        txtMoney.setDisable(true);
        txtQty.setDisable(true);
      //  txtCode.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {
        txtCode.setDisable(false);
        txtType.setDisable(false);
        txtMoney.setDisable(false);
        txtQty.setDisable(false);
        txtCode.clear();
       // txtCode.setText(generateNewId());
        txtType.clear();
        txtMoney.clear();
        txtQty.clear();
        txtCode.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblRooms.getSelectionModel().clearSelection();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String type = txtType.getText();

        if (!type.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtType.requestFocus();
            return;
        } else if (!txtMoney.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid unit price").show();
            txtMoney.requestFocus();
            return;
        } else if (!txtQty.getText().matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty on hand").show();
            txtQty.requestFocus();
            return;
        }

        int qty = Integer.parseInt(txtQty.getText());
        BigDecimal keyMoney = new BigDecimal(txtMoney.getText()).setScale(2);


        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(code)) {
                    new Alert(Alert.AlertType.INFORMATION, code + " Saved").show();
                }
                //Save Item
                roomBO.saveItem(new RoomDTO(code,type,keyMoney,qty));

               // roomDAO.save(new RoomDTO(code,type,keyMoney,qty));

                tblRooms.getItems().add(new RoomTM(code, type, keyMoney, qty));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {

                if (!existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
                }
                /*Update Item*/

                roomBO.updateItem(new RoomDTO(code,type,keyMoney,qty));
               // roomDAO.update(new RoomDTO(code,type,keyMoney,qty));

                RoomTM selectedItem = tblRooms.getSelectionModel().getSelectedItem();
                selectedItem.setType(type);
                selectedItem.setQty(qty);
                selectedItem.setKeyMoney(keyMoney);
                tblRooms.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        btnAddNewRoom.fire();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        /*Delete Item*/
        String code = tblRooms.getSelectionModel().getSelectedItem().getCode();
        try {
            if (!existItem(code)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
            }

            roomBO.deleteItem(code);
          // roomDAO.delete(code);

            tblRooms.getItems().remove(tblRooms.getSelectionModel().getSelectedItem());
            tblRooms.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + code).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private boolean existItem(String code) throws SQLException, ClassNotFoundException {

      return roomBO.itemExist(code);
       // return roomDAO.exist(code);
    }

  /*  private String generateNewId() {
        try {

           return roomBO.generateNewId();
           // return roomDAO.generateNewId();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "I00-001";
    }*/

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) roomContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
