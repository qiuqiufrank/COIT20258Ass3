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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import sls.presenter.BookPresenter;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Faqiu Sun
 */
public class MainView implements Initializable, IMainView {

    BookPresenter bp;

    @FXML
    private Button searchBooksByTitleBtn;

    @FXML
    private TextField AddABookAuthorTF;

    @FXML
    private TextField AddABookTitleTF;

    @FXML
    private TextField searchBooksByAuthorTF;

    @FXML
    private Button AddBookBtn;

    @FXML
    private TextField searchBooksByTitleTF;

    @FXML
    private Button searchBooksByAuthorBtn;

    @FXML
    private TextArea textArea;

    @FXML
    void searchBooksByTitle(ActionEvent event) {

    }

    @FXML
    void searchBooksByAuthor(ActionEvent event) {

    }

    @FXML
    void addABook(ActionEvent event) {
        String author = AddABookAuthorTF.getText();
        String title = AddABookTitleTF.getText();

        if (isEmpty(AddABookAuthorTF)) {
            return;
        }
        if (isEmpty(AddABookTitleTF)) {
            return;
        }
        bp.addNewBook(title, author);
    }

    private boolean isEmpty(TextField tf) {
        String v = AddABookTitleTF.getText();
        if (v.isEmpty()) {
            promptMessage("Text field should not be empty!");
            AddABookTitleTF.requestFocus();
            return true;
        }
        return false;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void bind(BookPresenter bp) {
        this.bp = bp;
    }

    @Override
    public void appendTextArea(String m) {
        textArea.appendText(m + "\n");
    }

    @Override
    public void promptMessage(String m) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(m);
        alert.show();
    }

}
