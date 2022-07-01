package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.hostel.bo.custom.ReserveDetailBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.bo.custom.impl.ReserveDetailBOImpl;
import lk.ijse.hostel.dao.custom.ReserveDetailDAO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dao.custom.impl.ReserveDetailDAOImpl;
import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;
//import lk.ijse.hostel.db.DBConnection;
import lk.ijse.hostel.dto.ReserveDetailDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.ReserveDetail;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.view.tm.ReserveDetailTM;
import lk.ijse.hostel.view.tm.RoomTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReserveRoomFormController {
    public JFXTextField txtStatus;
    public JFXTextField txtType;
    public JFXTextField txtName;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQty;
    public JFXTextField txtKeyMoney;
    public JFXComboBox<String> cmbId;
    public JFXComboBox<String> cmbCode;
    public TableView<ReserveDetailTM> tblReserveDetail;
    public JFXButton btnAddNewReserve;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public JFXTextField txtResId;
    public AnchorPane reserveContext;
    private RoomDAO roomDAO = new RoomDAOImpl();
    private StudentDAO studentDAO = new StudentDAOImpl();
    private ReserveDetailDAO reserveDetailDAO = new ReserveDetailDAOImpl();
   // private  ReserveDetailBO reserveDetailBO = new ReserveDetailBOImpl();
  private ReserveDetailBO reserveDetailBO = (ReserveDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVEDETAIL);
    private RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);


    public void initialize() {


        tblReserveDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("resId"));
        tblReserveDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblReserveDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblReserveDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblReserveDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblReserveDetail.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));

        initUI();
        loadAllItemCodes();
       loadAllCustomerIds();

        tblReserveDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {

                txtResId.setText(newValue.getResId());
                txtStatus.setText(newValue.getStatus());
                txtKeyMoney.setText(newValue.getKeyMoney().setScale(2).toString());
                txtQty.setText(newValue.getQty() + "");

              /*  txtResId.setDisable(false);
                txtType.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtQty.setDisable(false);*/
            }
        });

        txtQty.setOnAction(event -> btnSave.fire());
        loadAllItems();

        cmbId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

           /* if (newValue != null) {
                try {
                    *//*Search Customer*//*
                  //  Connection connection = DBConnection.getDbConnection().getConnection();
                    try {
                        if (!existCustomer(newValue + "")) {
                            //  "There is no such customer associated with the id " + id
                            new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + newValue + "").show();
                        }

                            // Student search = studentDAO.search(newValue + "");

                            //ReserveDetailBO reserveDetailBO = new ReserveDetailBOImpl();
                           // StudentDTO search = reserveDetailBO.searchCustomer(newValue + "");
                        List<StudentDTO> all = reserveBO.searchStudent(newValue);

                            txtName.setText(search.getName());

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to find the customer " + newValue + "" + e).show();
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                txtName.clear();
            }*/
            if (newValue != null) {
                try {
                    List<StudentDTO> all = reserveDetailBO.searchCustomer(newValue);
                    for (StudentDTO dto : all) {
                        txtName.setText(dto.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                txtName.clear();
            }
        });


        cmbCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
            //txtQty.setEditable(newItemCode != null);
           // btnSave.setDisable(newItemCode == null);

            if (newItemCode != null) {



                try {
                   /* if (!existItem(newItemCode + "")) {
//                        throw new NotFoundException("There is no such item associated with the id " + code);
                    }*/
                    List<RoomDTO> all = reserveDetailBO.searchItem(newItemCode);
                    for (RoomDTO dto : all) {

                        //  Room item = roomDAO.search(newItemCode + "");

                        //  ReserveDetailBOImpl reserveDetailBO = new ReserveDetailBOImpl();
                       // RoomDTO item = reserveDetailBO.searchItem(newItemCode + "");
                       /* txtType.setText(dto.getType());

                        txtKeyMoney.setText(dto.getKeyMoney().setScale(2).toString());*/
                        txtType.setText(dto.getType());
                        txtKeyMoney.setText(dto.getKeyMoney().setScale(2).toString());
                       // txtQty.setText(dto.getQty().toString());

//                    txtQtyOnHand.setText(tblOrderDetails.getItems().stream().filter(detail-> detail.getCode().equals(item.getCode())).<Integer>map(detail-> item.getQtyOnHand() - detail.getQty()).findFirst().orElse(item.getQtyOnHand()) + "");
                       // Optional<ReserveDetailTM> optOrderDetail = tblReserveDetail.getItems().stream().filter(detail -> detail.getCode().equals(newItemCode)).findFirst();
                       // txtQtyOnHand.setText((optOrderDetail.isPresent() ? dto.getQty() - optOrderDetail.get().getQty() : dto.getQty()) + "");
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                txtType.clear();
                txtQty.clear();
                txtQtyOnHand.clear();
                txtKeyMoney.clear();
            }
        });

       /* tblReserveDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {

            if (selectedOrderDetail != null) {
                cmbCode.setDisable(true);
                cmbCode.setValue(selectedOrderDetail.getCode());
                btnSave.setText("Update");
                txtQtyOnHand.setText(Integer.parseInt(txtQtyOnHand.getText()) + selectedOrderDetail.getQty() + "");
                txtQty.setText(selectedOrderDetail.getQty() + "");
            } else {
                btnSave.setText("Add");
                cmbCode.setDisable(false);
                cmbCode.getSelectionModel().clearSelection();
                txtQty.clear();
            }

        });*/


    }

    private void loadAllItems() {
        tblReserveDetail.getItems().clear();
        try {

        List<ReserveDetailDTO> allReserve = reserveDetailBO.getAllReserve();

            for(ReserveDetailDTO reserve : allReserve){
                tblReserveDetail.getItems().add(new ReserveDetailTM(reserve.getResId(),reserve.getId(),reserve.getCode(),reserve.getQty(),reserve.getKeyMoney(),reserve.getStatus()));

            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtResId.clear();
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        //txtResId.setDisable(true);
       // txtType.setDisable(true);
      //  txtKeyMoney.setDisable(true);
      //  txtQty.setDisable(true);
       // txtResId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadAllCustomerIds() {
        try {

           // ReserveDetailBOImpl reserveDetailBO = new ReserveDetailBOImpl();
            List<StudentDTO> all = reserveDetailBO.getAllCustomers();
          //  ArrayList<StudentDTO> all = studentDAO.getAll();
            for (StudentDTO studentDTO : all) {
                cmbId.getItems().add(studentDTO.getId());
            }


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllItemCodes() {
        try {
           // Get all items

          //  ReserveDetailBOImpl reserveDetailBO = new ReserveDetailBOImpl();
            List<RoomDTO> all = reserveDetailBO.getAllItems();
           //ArrayList<Room> all = roomDAO.getAll();
            for (RoomDTO roomDTO : all) {
                cmbCode.getItems().add(roomDTO.getCode());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String resId = txtResId.getText();
        String id = cmbId.getSelectionModel().getSelectedItem();
        String code = cmbCode.getSelectionModel().getSelectedItem();
        int qty = Integer.parseInt(txtQty.getText());
        BigDecimal keyMoney = new BigDecimal(txtKeyMoney.getText()).setScale(2);
        String status = txtStatus.getText();

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(resId)) {
                    new Alert(Alert.AlertType.INFORMATION, resId + " Saved").show();
                }
                //Save Item
               /* Connection connection = DBConnection.getDbConnection().getConnection();
                PreparedStatement pstm = connection.prepareStatement("INSERT INTO ReserveDetail (resId,id,code, qty, keyMoney, status) VALUES (?,?,?,?,?,?)");
                pstm.setString(1, resId);
                pstm.setString(2, id);
                pstm.setString(3, code);
                pstm.setInt(4, qty);
                pstm.setBigDecimal(5, keyMoney);
                pstm.setString(6, status);
                pstm.executeUpdate();*/

                reserveDetailDAO.save(new ReserveDetail(resId,id,code,qty,keyMoney,status));

                tblReserveDetail.getItems().add(new ReserveDetailTM(resId,id,code, qty, keyMoney, status));

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
              /*  Connection connection = DBConnection.getDbConnection().getConnection();
                PreparedStatement pstm = connection.prepareStatement("UPDATE Room SET qty =?, keyMoney=? WHERE code=?");
                pstm.setInt(1, qty);
                pstm.setBigDecimal(2, keyMoney);
                pstm.setString(3, code);
                pstm.executeUpdate();*/

               // roomDAO.save(new RoomDTO(code,keyMoney,qty));
                // roomDAO.update(qty,keyMoney,code);

                reserveDetailDAO.update(new ReserveDetail(resId,id,code,qty,keyMoney,status));
                ReserveDetailTM selectedItem = tblReserveDetail.getSelectionModel().getSelectedItem();

                selectedItem.setId(id);
                selectedItem.setCode(code);
                selectedItem.setQty(qty);
                selectedItem.setKeyMoney(keyMoney);
                selectedItem.setStatus(status);
                //selectedItem.setStatus(id);
               // selectedItem.setStatus(code);
                tblReserveDetail.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        btnAddNewReserve.fire();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String resId = tblReserveDetail.getSelectionModel().getSelectedItem().getCode();
        try {
            if (!existReserve(resId)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + resId).show();
            }
          /*  Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM ReserveDetail WHERE resId=?");
            pstm.setString(1, resId);
            pstm.executeUpdate();*/
                reserveDetailDAO.delete(resId);

            tblReserveDetail.getItems().remove(tblReserveDetail.getSelectionModel().getSelectedItem());
            tblReserveDetail.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + resId).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {
        txtResId.setDisable(false);
        txtStatus.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtQty.setDisable(false);
        txtResId.clear();
       // txtResId.setText(generateNewId());
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        txtType.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblReserveDetail.getSelectionModel().clearSelection();
    }

    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
      //  ReserveDetailBOImpl reserveDetailBO = new ReserveDetailBOImpl();
       return reserveDetailBO.checkItemIsAvailable(code);

       // return roomDAO.exist(code);

    }
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
     //   ReserveDetailBOImpl reserveDetailBO = new ReserveDetailBOImpl();
       return reserveDetailBO.checkCustomerIsAvailable(id);
       // return studentDAO.exist(id);
    }

    private boolean existReserve(String resId) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT resId FROM ReserveDetail WHERE resId=?");
        pstm.setString(1, resId);
        return pstm.executeQuery().next();*/
        return reserveDetailDAO.exist(resId);
    }


   /* private String generateNewId() {
        try {
           *//* Connection connection = DBConnection.getDbConnection().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT resId FROM ReserveDetail ORDER BY resId DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("resId");
                int newItemId = Integer.parseInt(id.replace("R00-", "")) + 1;
                return String.format("R00-%03d", newItemId);
            } else {
                return "R00-001";
            }*//*


            ReserveDetailBOImpl reserveDetailBO = new ReserveDetailBOImpl();
         return reserveDetailBO.generateNewId();
         //   return reserveDetailDAO.generateNewId();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "R00-001";
    }*/

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) reserveContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }


    //-------------------------------------------------
   /* public RoomDTO findItem(String code) {
        try {


            ReserveDetailBOImpl reserveDetailBO = new ReserveDetailBOImpl();
           return reserveDetailBO.searchItem(code);
           // return roomDAO.search(code);

           // return new RoomDTO(code, rst.getString("type") , rst.getBigDecimal("keyMoney"),rst.getInt("qtyOnHand"));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }*/
    //----------------------------------------------------------------

}
