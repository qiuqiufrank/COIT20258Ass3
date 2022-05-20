/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.view;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import sls.presenter.BookPresenter;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;
import sls.model.Book;
import sls.model.Borrower;
import sls.presenter.BorrowerPresenter;
import sls.presenter.BorrowingRecordPresenter;

/**
 * FXML Controller class
 *
 * @author Faqiu Sun
 */
public class MainView implements Initializable, IMainView {

    BookPresenter bookPresenter;
    BorrowerPresenter borrowerPresenter;
    BorrowingRecordPresenter borrowingRecordPresenter;

    @FXML
    private Button searchBooksByTitleBtn;

    @FXML
    private Button addBookBtn;

    @FXML
    private TextField addABorrowerEmailTF;

    @FXML
    private TextField addABookAuthorTF;

    @FXML
    private TextField searchBooksByAuthorTF;

    @FXML
    private TextField searchBooksByTitleTF;

    @FXML
    private Button searchBooksByAuthorBtn;

    @FXML
    private TextField addABorrowerPhoneTF;

    @FXML
    private TextField addABookTitleTF;

    @FXML
    private TextField addABorrowerNameTF;

    @FXML
    private Button overdueReturnsBtn;

    @FXML
    private Button issuedBooks;

    @FXML
    private TextArea textArea;

    @FXML
    private Button addABorrowerBtn;

    @FXML
    private ComboBox<Book> borrowABookBookCB;
    @FXML
    private ComboBox<Borrower> borrowABookBorrowerCB;
    @FXML
    private ComboBox<Borrower> returnABookBorrowerCB;
    @FXML
    private ComboBox<Book> returnABookBookCB;

    @FXML
    private DatePicker issuedDateDP;

    @FXML
    private DatePicker returnDateDP;

    @FXML
    void addABorrower(ActionEvent event) {

        if (isEmpty(addABorrowerNameTF)) {
            return;
        }
        if (isEmpty(addABorrowerPhoneTF)) {
            return;
        }
        if (isEmpty(addABorrowerEmailTF)) {
            return;
        }
        borrowerPresenter.addABorrower(addABorrowerNameTF.getText(), addABorrowerPhoneTF.getText(), addABorrowerEmailTF.getText());
    }

    @FXML
    void getIssuedBooks(ActionEvent event) {
        bookPresenter.getIssuedBooks();
    }

    @FXML
    void getOverdueReturns(ActionEvent event) {

    }

    @FXML
    void searchBooksByTitle(ActionEvent event) {
        if (isEmpty(searchBooksByTitleTF)) {
            return;
        }
        bookPresenter.searchByTitle(searchBooksByTitleTF.getText());
    }

    @FXML
    void searchBooksByAuthor(ActionEvent event) {

        if (isEmpty(searchBooksByAuthorTF)) {
            return;
        }
        bookPresenter.searchByAuthor(searchBooksByAuthorTF.getText());
    }

    @FXML
    void addABook(ActionEvent event) {
        String author = addABookAuthorTF.getText();
        String title = addABookTitleTF.getText();

        if (isEmpty(addABookAuthorTF)) {
            return;
        }
        if (isEmpty(addABookTitleTF)) {
            return;
        }
        bookPresenter.addNewBook(title, author);
    }

    @FXML
    void onShowAvailableBooks(Event event) {

        ObservableList<Book> books = bookPresenter.getAvailableBooks();
        updateBookOptions(books, borrowABookBookCB);

//        borrowABookBookCB.valueProperty().addListener((obs, oldVal, newVal)
//                -> System.out.println("Price of the " + newVal.getId()));
    }

    @FXML
    void onShowBorrowedBooks(Event event) {
        ObservableList<Book> books = bookPresenter.getAvailableBooks();
        updateBookOptions(books, returnABookBookCB);
    }

    @FXML
    void onShowAllBorrowers(Event event) {

        ObservableList<Borrower> borrowers = borrowerPresenter.getAllBorrowers();
        updateBorrowerOptions(borrowers, borrowABookBorrowerCB);
    }

    @FXML
    void onShowIssuedBorrowers(Event event) {

        ObservableList<Borrower> borrowers = borrowerPresenter.getAllIssuedBorrowers();
        updateBorrowerOptions(borrowers, returnABookBorrowerCB);
    }

    @FXML
    void borrowABook(ActionEvent e) {

        if (issuedDateDP.getValue() == null) {
            promptMessage("Issued date must be selected");
            return;
        }
        if (returnDateDP.getValue() == null) {
            promptMessage("Return date must be selected");
            return;
        }

        LocalDate localDate1 = issuedDateDP.getValue();
        Instant instant1 = Instant.from(localDate1.atStartOfDay(ZoneId.systemDefault()));
        Date issuedDate = Date.from(instant1);

        LocalDate localDate2 = returnDateDP.getValue();
        Instant instant2 = Instant.from(localDate2.atStartOfDay(ZoneId.systemDefault()));
        Date returnDate = Date.from(instant2);

        if (returnDate.before(issuedDate) || returnDate.equals(issuedDate)) {
            promptMessage("Return date must be after the issued date");
        }
        Book book = borrowABookBookCB.getValue();
        Borrower borrower = borrowABookBorrowerCB.getValue();
        if (book == null) {
            promptMessage("Book must be selected");
            return;
        }
        if (borrower == null) {
            promptMessage("Borrower must be selected");
            return;
        }

        borrowingRecordPresenter.IssueABook(book, borrower, issuedDate, returnDate);
    }

    @FXML
    void returnABook(ActionEvent e) {

        Book book = returnABookBookCB.getValue();
        Borrower borrower = returnABookBorrowerCB.getValue();
        if (book == null) {
            promptMessage("Book must be selected");
            return;
        }
        if (borrower == null) {
            promptMessage("Borrower must be selected");
            return;
        }

        borrowingRecordPresenter.returnABook(book, borrower);

    }

    void updateBookOptions(ObservableList<Book> books, ComboBox<Book> cb) {
        cb.setItems(books);
        cb.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book object) {
                return object.getId();
            }

            @Override
            public Book fromString(String string) {
                return books.stream().filter(ap
                        -> ap.getId().equals(string)).findFirst().orElse(null);
            }
        });
    }

    void updateBorrowerOptions(ObservableList<Borrower> borrowers, ComboBox<Borrower> cb) {
        cb.setItems(borrowers);
        cb.setConverter(new StringConverter<Borrower>() {
            @Override
            public String toString(Borrower object) {
                return object.getName() + ":" + object.getId();
            }

            @Override
            public Borrower fromString(String string) {
                String vs[] = string.split(":");
                if (vs.length != 2) {
                    return null;
                }
                Long id = Long.parseLong(vs[1]);
                return borrowers.stream().filter(ap
                        -> ap.getId() == (id)).findFirst().orElse(null);
            }
        });
    }

    private boolean isEmpty(TextField tf) {
        String v = tf.getText();
        if (v.isEmpty()) {
            promptMessage("Text field should not be empty!");
            tf.requestFocus();
            return true;
        }
        return false;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void bind(BookPresenter bookPresenter, BorrowerPresenter borrowerPresenter, BorrowingRecordPresenter borrowingRecordPresenter) {
        this.bookPresenter = bookPresenter;
        this.borrowerPresenter = borrowerPresenter;
        this.borrowingRecordPresenter = borrowingRecordPresenter;
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
