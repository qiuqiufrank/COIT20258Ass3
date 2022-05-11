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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sls.presenter.LoginPresenter;

/**
 * FXML Controller class
 *
 * @author Faqiu Sun
 */
public class LoginView implements Initializable {

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
    void bind(LoginPresenter lp){
        this.lp=lp;
    }

}
