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
import javafx.scene.control.Tab;
import sls.presenter.BookPresenter;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;
import sls.model.Book;
import sls.model.Borrower;
import sls.model.Donor;
import sls.presenter.BorrowerPresenter;
import sls.presenter.BorrowingRecordPresenter;
import sls.presenter.DonorPresenter;
import sls.presenter.UserPresenter;

/**
 * FXML Controller class
 *
 * @author Faqiu Sun
 */
public class MainView implements Initializable, IMainView {

    BookPresenter bookPresenter;
    BorrowerPresenter borrowerPresenter;
    BorrowingRecordPresenter borrowingRecordPresenter;
    DonorPresenter donorPresenter;
    UserPresenter userPresenter;

    @FXML
    private TextField addABorrowerEmailTF;

    @FXML
    private TextField addABookAuthorTF;

    @FXML
    private TextField donateBooksCopiesTF;

    @FXML
    private TextField searchBooksByAuthorTF;

    @FXML
    private TextField searchBooksByTitleTF;

    @FXML
    private TextField addADonorNameTF;
    @FXML
    private TextField addADonorFullNameTF;
    @FXML
    private TextField addADonorEmailTF;
    @FXML
    private TextField addADonorPhoneTF;

    @FXML
    private TextField addABorrowerPhoneTF;

    @FXML
    private TextField addABookTitleTF;

    @FXML
    private TextField addABorrowerNameTF;

    @FXML
    private TextField manageAUserUserNameTF;
    @FXML
    private TextField manageAUserPasswordTF;
    @FXML
    private TextField manageAUserFullNameTF;
    @FXML
    private TextField manageAUserPhoneTF;
    @FXML
    private TextField manageAUserEmailTF;

    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox<Book> borrowABookBookCB;
    @FXML
    private ComboBox<Borrower> borrowABookBorrowerCB;
    @FXML
    private ComboBox<Borrower> returnABookBorrowerCB;
    @FXML
    private ComboBox<Borrower> searchAllBooksByBorrowerBorrowerCB;
    @FXML
    private ComboBox<Book> donateBooksBookCB;
    @FXML
    private ComboBox<Donor> donateBooksDonorCB;
    @FXML
    private ComboBox<Donor> searchBooksByDonorDonorCB;

    @FXML
    private ComboBox<Book> returnABookBookCB;

    @FXML
    private DatePicker issuedDateDP;

    @FXML
    private DatePicker returnDateDP;

    @FXML
    private DatePicker donateDateDP;

    @FXML
    private Tab userTab;

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
    void searchIssuedBooks(ActionEvent event) {
        bookPresenter.searchIssuedBooks();
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
    void onShowBorrowedBooksByBorrower(Event event) {
        Borrower borrower = returnABookBorrowerCB.getValue();
        //Not select borrrower Id
        if (borrower == null) {
            ObservableList<Book> books = bookPresenter.getIssueddBooks();
            updateBookOptions(books, returnABookBookCB);
        } else {
            ObservableList<Book> books = bookPresenter.getIssuedBooksByBorrower(borrower);
            updateBookOptions(books, returnABookBookCB);
        }

    }

    @FXML
    void onShowDonateBooks(Event e) {
        ObservableList<Book> books = bookPresenter.getAllBooks();
        updateBookOptions(books, donateBooksBookCB);
    }

    @FXML
    void onShowDonateDonors(Event e) {
        ObservableList<Donor> books = donorPresenter.getAllDonors();
        updateDonorOptions(books, donateBooksDonorCB);
    }

    @FXML
    void onShowSearchBooksByDonors(Event e) {
        ObservableList<Donor> books = donorPresenter.getAllDonors();
        updateDonorOptions(books, searchBooksByDonorDonorCB);
    }

    /**
     * onShowAllBorrowers for borrowing a book
     *
     * @param event
     */
    @FXML
    void onShowAllBorrowersBAB(Event event) {

        ObservableList<Borrower> borrowers = borrowerPresenter.getAllBorrowers();
        updateBorrowerOptions(borrowers, borrowABookBorrowerCB);
    }

    /**
     * onShowAllBorrowers for searching books by a borrower
     *
     * @param event
     */
    @FXML
    void onShowAllBorrowersSBB(Event event) {

        ObservableList<Borrower> borrowers = borrowerPresenter.getAllBorrowers();
        updateBorrowerOptions(borrowers, searchAllBooksByBorrowerBorrowerCB);
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

    @FXML
    void addADonor(ActionEvent e) {

        if (isEmpty(addADonorNameTF) || isEmpty(addADonorFullNameTF) || isEmpty(addADonorEmailTF) || isEmpty(addADonorPhoneTF)) {
            return;
        }
        donorPresenter.addADonor(addADonorNameTF.getText(), addADonorFullNameTF.getText(), addADonorEmailTF.getText(), addADonorPhoneTF.getText());
    }

    @FXML
    void searchOverdueReturns(ActionEvent e) {
        bookPresenter.searchOverdueBooks();
    }

    @FXML
    void donateBooks(ActionEvent e) {
        Book book = donateBooksBookCB.getValue();
        Donor donor = donateBooksDonorCB.getValue();
        if (book == null) {
            promptMessage("Book must be selected");
            return;
        }
        if (donor == null) {
            promptMessage("Donor must be selected");
            return;
        }
        if (donateDateDP.getValue() == null) {
            promptMessage("Donate date must be selected");
            return;
        }

        if (isEmpty(donateBooksCopiesTF)) {
            return;
        }
        if (!validateNumber(donateBooksCopiesTF)) {
            return;
        }

        int copies = Integer.parseInt(donateBooksCopiesTF.getText());
        if (copies < 1) {
            promptMessage("Copies must be larger than 0");
            return;
        }
        LocalDate localDate = donateDateDP.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date donateDate = Date.from(instant);

        donorPresenter.donateBooks(donor, book, copies, donateDate);

    }

    @FXML
    void searchIssuedBookByBorrower(ActionEvent e) {
        Borrower borrower = searchAllBooksByBorrowerBorrowerCB.getValue();
        if (borrower == null) {
            promptMessage("Borrower must be selected");
            return;
        }
        bookPresenter.searchIssuedBooksByBorrower(borrower);
    }

    @FXML
    void searchBooksByDonator(ActionEvent e) {
        Donor donor = searchBooksByDonorDonorCB.getValue();
        if (donor == null) {
            promptMessage("Donor must be selected");
            return;
        }
        bookPresenter.searchBooksByDonor(donor);
    }

    @FXML
    void addAUser(ActionEvent e) {
        if (isEmpty(manageAUserUserNameTF) || isEmpty(manageAUserPasswordTF) || isEmpty(manageAUserFullNameTF)
                || isEmpty(manageAUserEmailTF) || isEmpty(manageAUserPhoneTF)) {
            return;
        }
        if (!validatePassword(manageAUserPasswordTF)) {
            return;
        }
        userPresenter.addAUser(manageAUserUserNameTF.getText(), manageAUserPasswordTF.getText(),
                manageAUserFullNameTF.getText(), manageAUserEmailTF.getText(), manageAUserPhoneTF.getText());

    }

    @FXML
    void searchAllUsers() {
        userPresenter.searchAllUsers();
    }

    @FXML
    void deleteAUser() {
        if (isEmpty(manageAUserUserNameTF)) {
            return;
        }
        userPresenter.deleteAUser(manageAUserUserNameTF.getText());
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

    void updateDonorOptions(ObservableList<Donor> donors, ComboBox<Donor> cb) {
        cb.setItems(donors);
        cb.setConverter(new StringConverter<Donor>() {
            @Override
            public String toString(Donor object) {
                return object.getName() + ":" + object.getId();
            }

            @Override
            public Donor fromString(String string) {
                String vs[] = string.split(":");
                if (vs.length != 2) {
                    return null;
                }
                Long id = Long.parseLong(vs[1]);
                return donors.stream().filter(ap
                        -> ap.getId() == (id)).findFirst().orElse(null);
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

    private boolean validatePassword(TextField tf) {
        String password = tf.getText();
        //If the length is less than 8, return false;
        if (password.length() != 8) {
            promptMessage("The length of the password must be 8");
            return false;
        }
        boolean containNumber = false;
        boolean containLetter = false;
        for (byte b : password.getBytes()) {
            if (('a' <= b && b <= 'z') || ('A' <= b && b <= 'Z')) {
                containLetter = true;
            }
            if (('0' <= b && b <= '9')) {
                containNumber = true;
            }
        }
        if (!containLetter) {
            promptMessage("Password must contain letters");
            return false;
        }
        if (!containNumber) {
            promptMessage("Password must contain numbers");
            return false;
        }

        return true;
    }

    private boolean validateNumber(TextField tf) {
        String v = tf.getText();
        try {
            Integer.parseInt(v);
        } catch (NumberFormatException n) {
            promptMessage("Text field should be an integer!");
            tf.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void disableUserTab(boolean disable) {

        userTab.setDisable(true);
    }

    @Override
    public void bind(BookPresenter bookPresenter, BorrowerPresenter borrowerPresenter, BorrowingRecordPresenter borrowingRecordPresenter, DonorPresenter donorPresenter, UserPresenter userPresenter) {
        this.bookPresenter = bookPresenter;
        this.borrowerPresenter = borrowerPresenter;
        this.borrowingRecordPresenter = borrowingRecordPresenter;
        this.donorPresenter = donorPresenter;
        this.userPresenter = userPresenter;
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
