package edu.cqu.sls;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sls.model.UserModel;
import sls.model.UserType;
import sls.presenter.LoginPresenter;
import sls.view.LoginView;
import sls.view.MainView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(UserType.Borrower);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent loginRoot = loader.load();
        Scene loginScene = new Scene(loginRoot);
        loginScene.getStylesheets().add("/styles/Styles.css");
        LoginView loginView = loader.getController();



        Parent mainRoot = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        Scene mainScene = new Scene(mainRoot);
        mainScene.getStylesheets().add("/styles/Styles.css");

        LoginPresenter lp = new LoginPresenter();

        UserModel userModel = new UserModel();

        loginView.bind(lp);
        lp.bind(stage, userModel, mainScene);

        stage.setTitle("OLMC Spiritual Library System");
        stage.setScene(mainScene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
