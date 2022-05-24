/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.presenter;


import javafx.scene.Scene;
import javafx.stage.Stage;
import sls.model.IUserModel;
import sls.model.User;
import sls.model.IUserModel;
import sls.view.ILoginView;
import sls.view.IMainView;
import sls.view.MainView;

/**
 *
 * @author Faqiu Sun
 */
public class LoginPresenter {

    private Stage stage;
    private IUserModel userModel;
    private ILoginView loginView;
    private IMainView mainView;
    private Scene mainScene;

    public void bind(Stage stage, IUserModel userModel, Scene mainScene, ILoginView loginView, IMainView mainView) {
        this.stage = stage;
        this.userModel = userModel;
        this.mainScene = mainScene;
        this.loginView = loginView;
        this.mainView = mainView;
    }

    public void login(String name, String password) {

        User user = userModel.searchValidUser(name, password);
        if (user == null) {
            loginView.promptMessage("Invalid username or password!");
            return;
        }
        //disabe user management tab
        if (!user.isIsAdmin()) {
            mainView.disableUserTab(true);
        }

        stage.setScene(mainScene);
        stage.show();

    }

    public void exit() {
        System.exit(0);
    }
}
