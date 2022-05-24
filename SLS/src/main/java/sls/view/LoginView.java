/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sls.presenter.LoginPresenter;
import sls.presenter.UserPresenter;

/**
 * FXML Controller class
 *
 * @author Faqiu Sun
 */
public class LoginView implements Initializable, ILoginView {

    private LoginPresenter lp;
    @FXML
    private TextField nameTF;

    @FXML
    private Button loginBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private PasswordField passwordTF;

    @FXML
    void loginAction(ActionEvent event) {
        lp.login(nameTF.getText(), passwordTF.getText());
    }

    @FXML
    void exitAction(ActionEvent event) {
        lp.exit();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void promptMessage(String m) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(m);
        alert.show();
    }

    @Override
    public void bind(LoginPresenter loginPresenter) {
        this.lp = loginPresenter;
    }

}
