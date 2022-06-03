package edu.cqu.sls;

import java.sql.DriverManager;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sls.model.BookModel;
import sls.model.UserModel;
import sls.presenter.BookPresenter;
import sls.presenter.LoginPresenter;

import sls.view.LoginView;
import sls.view.MainView;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import sls.model.BorrowerModel;
import sls.model.BorrowingRecordModel;
import sls.model.DonationRecord;
import sls.model.DonationRecordModel;
import sls.model.DonorModel;
import sls.presenter.BorrowerPresenter;
import sls.presenter.BorrowingRecordPresenter;
import sls.presenter.DonorPresenter;
import sls.presenter.UserPresenter;

/**
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
public class MainApp extends Application {

    //Mysql url
    private static final String URL = "jdbc:mysql://localhost:3306/Ass3db";
    //Mysql username
    private static final String USERNAME = "root";
    //Mysql password
    private static final String PASSWORD = "mypassword";

    @Override
    public void start(Stage stage) throws Exception {

        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Cannot connect to the database. The software will exit.");
            alert.show();
            return;

        }

        //create views
        FXMLLoader lloader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent loginRoot = lloader.load();
        Scene loginScene = new Scene(loginRoot);
        loginScene.getStylesheets().add("/styles/Styles.css");
        LoginView loginView = lloader.getController();

        FXMLLoader mloader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        Parent mainRoot = mloader.load();
        Scene mainScene = new Scene(mainRoot);
        loginScene.getStylesheets().add("/styles/Styles.css");
        MainView mainView = mloader.getController();

        //create models
        UserModel userModel = new UserModel(connection);
        BookModel bookModel = new BookModel(connection);
        BorrowerModel borrowerModel = new BorrowerModel(connection);
        BorrowingRecordModel borrowingRecordModel = new BorrowingRecordModel(connection);
        DonorModel donorModel = new DonorModel(connection);
        DonationRecordModel donationRecordModel = new DonationRecordModel(connection);

        //create presenters
        LoginPresenter loginPresenter = new LoginPresenter();
        BorrowerPresenter borrowerPresenter = new BorrowerPresenter(borrowerModel, mainView);
        BookPresenter bookPresenter = new BookPresenter(bookModel, donationRecordModel, borrowingRecordModel, mainView);
        BorrowingRecordPresenter borrowingRecordPresenter = new BorrowingRecordPresenter(borrowingRecordModel, bookModel, mainView);
        DonorPresenter donorPresenter = new DonorPresenter(donorModel, donationRecordModel, bookModel, mainView);
        UserPresenter userPresenter = new UserPresenter(userModel, mainView);

        mainView.bind(bookPresenter, borrowerPresenter, borrowingRecordPresenter, donorPresenter, userPresenter);
        loginView.bind(loginPresenter);
        loginPresenter.bind(stage, userModel, mainScene, loginView, mainView);

        stage.setTitle("OLMC Spiritual Library System");
        stage.setScene(loginScene);
      //  stage.setScene(mainScene);
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
