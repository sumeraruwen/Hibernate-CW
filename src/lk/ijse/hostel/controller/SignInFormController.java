package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SignInFormController {
    public AnchorPane root;
    public JFXTextField txtName;
    public JFXPasswordField pwdPassword;
    public FontAwesomeIconView showPw;


    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        String tempName = txtName.getText();
        String tempPassword = pwdPassword.getText();
        if (tempName.equals("admin") && tempPassword.equals("1234")) {
            this.root.getChildren().clear();
            this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml")));
        }

    }

   /* public void showPassword_OnAction(ActionEvent actionEvent) {
        if (showPw.getGlyphName().equals("EYE")){
            txtShowPassword.setText(pwdPassword.getText());
            txtShowPassword.setVisible(true);
            txtPassword.setVisible(false);

        }else {
            txtPassword.setText(txtShowPassword.getText());
            txtShowPassword.setVisible(false);
            txtPassword.setVisible(true);
        }
    }*/
}
