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
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.view.tm.StudentTM;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AddStudentFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public JFXButton btnAddNewStudent;
    public TableView<StudentTM> tblStudents;
    public AnchorPane studentContext;
  //  private StudentDAO studentDAO = new StudentDAOImpl();
   // private StudentBO studentBO = new StudentBOImpl();
private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);



    public void initialize() {
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));

        initUI();
        loadAllCustomers();

        tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtId.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());

                txtId.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtContact.setDisable(false);
            }
        });

       /* try {
            List<StudentDTO> students = studentBO.getAllCustomers();
            for (StudentDTO student : students) {
                tblStudents.getItems().add(new StudentTM(student.getId(), student.getName(), student.getAddress(), student.getContact()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

      //  txtQty.setOnAction(event -> btnSave.fire());
        //loadAllCustomers();
    }

    private void loadAllCustomers() {
       tblStudents.getItems().clear();
        try {
            //Get all items
            List<StudentDTO> allStudents = studentBO.getAllCustomers();

          //  ArrayList<StudentDTO> allStudents = studentDAO.getAll();

            for(StudentDTO student :allStudents){
                tblStudents.getItems().add(new StudentTM(student.getId(), student.getName(), student.getAddress(), student.getContact()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
       // txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
       // txtId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }



    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();



        if (!name.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtName.requestFocus();
            return;
        } else if (!address.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtAddress.requestFocus();
            return;
        } else if (!contact.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtContact.requestFocus();
            return;
        }

       // int qty = Integer.parseInt(txtQty.getText());
       // BigDecimal keyMoney = new BigDecimal(txtMoney.getText()).setScale(2);


        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(id)) {
                    new Alert(Alert.AlertType.INFORMATION, id + " Saved ").show();
                }
                //Save Item

                studentBO.saveCustomer(new StudentDTO(id,name,address,contact));
               // studentDAO.save(new StudentDTO(id,name,address,contact));

                tblStudents.getItems().add(new StudentTM(id, name, address, contact));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {

                if (!existItem(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + id).show();
                }
                /*Update Item*/

                studentBO.updateCustomer(new StudentDTO(id,name,address,contact));
                //studentDAO.update(new StudentDTO(id,name,address,contact));

                StudentTM selectedItem = tblStudents.getSelectionModel().getSelectedItem();
                selectedItem.setName(name);
                selectedItem.setAddress(address);
                selectedItem.setContact(contact);
                tblStudents.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        btnAddNewStudent.fire();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        /*Delete Item*/
        String id = tblStudents.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existItem(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + id).show();
            }

            studentBO.deleteCustomer(id);
          //  studentDAO.delete(id);

            tblStudents.getItems().remove(tblStudents.getSelectionModel().getSelectedItem());
            tblStudents.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtId.clear();
        //txtId.setText(generateNewId());
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtId.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblStudents.getSelectionModel().clearSelection();
    }

    private boolean existItem(String id) throws SQLException, ClassNotFoundException {

       return studentBO.customerExist(id);
        //return studentDAO.exist(id);
    }


   /* private String generateNewId() {
        try {

           return studentBO.generateNewId();

          //  return studentDAO.generateNewId();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "S00-001";
    }*/

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) studentContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }
}
