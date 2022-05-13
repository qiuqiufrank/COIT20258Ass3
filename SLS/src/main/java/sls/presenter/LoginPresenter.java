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
import sls.model.UserType;
import sls.view.MainView;

/**
 *
 * @author Faqiu Sun
 */
public class LoginPresenter {
    private Stage stage;
    private IUserModel userModel;
    private Scene mainScene ;
    public void bind(Stage stage,IUserModel userModel, Scene mainScene){
        this.stage=stage;
        this.userModel=userModel;
        this.mainScene=mainScene;
    }
    public void login(String name,String password){
    
       //User user=userModel.findValidUser(name, password);
      // if(user.getUserType()==UserType.Librarian || user.getUserType()==UserType.MainLibrarian ){
           stage.setScene(mainScene);
           stage.show();
      // }
    }
    public void exit(){
        System.exit(0);
    }
}
