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
    private TextArea textArea;
    
    @FXML
    private Button AddBookBtn;
    
    @FXML
    private TextField AddABookAuthorTF;

    @FXML
    private TextField AddABookTitleTF;

    @FXML
    void addABook(ActionEvent event) {

        bp.addNewBook(AddABookTitleTF.getText(), AddABookAuthorTF.getText());
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
        textArea.appendText(m);
    }

}
