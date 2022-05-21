/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.presenter;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sls.model.Book;
import sls.model.Borrower;
import sls.model.Donor;
import sls.model.IBookModel;
import sls.view.IMainView;

/**
 *
 * @author Faqiu Sun
 */
public class BookPresenter {

    private IBookModel bookModel;
    private IMainView mainView;

    public BookPresenter(IBookModel bookModel, IMainView mainView) {
        this.bookModel = bookModel;
        this.mainView = mainView;
    }

    public void addNewBook(String title, String author) {

        Book book = bookModel.addNewBook(title, author);
        if (book == null) {
            mainView.appendTextArea("\nAdding book failed");
        } else {
            mainView.appendTextArea("\nAdding the book successfully:");
            mainView.appendTextArea(book.toString());
        }
    }

    private String booksToString(List<Book> books) {
        String s = "";
        String headerString = String.format("Id\tTitle\tAuthor\tCopies\tBorrowed Count");
        s += headerString + "\n";
        for (Book b : books) {
            String line = String.format("%s\t%s\t%s\t%d\t%d", b.getId(), b.getTitle(), b.getAuthor(), b.getCopies(), b.getBorrowedCount());
            s += line + "\n";
        }
        return s;
    }

    public void searchByTitle(String title) {
        List<Book> books = bookModel.searchByTitle(title);
        if (books.size() > 0) {
            mainView.appendTextArea("\nSearch for title " + title + ", found " + books.size() + " books:");
            mainView.appendTextArea(booksToString(books));
        } else {
            mainView.appendTextArea("\nSearching title " + title + " is not found");
        }
    }

    public void searchByAuthor(String author) {
        List<Book> books = bookModel.searchByAuthor(author);
        if (books.size() > 0) {
            mainView.appendTextArea("\nSearch for author " + author + ", found " + books.size() + " books:");
            mainView.appendTextArea(booksToString(books));
        } else {
            mainView.appendTextArea("\nSearching author " + author + " is not found");
        }
    }

    public void searchIssuedBooks() {
        List<Book> books = bookModel.getAllIssuedBooks();
        if (books.size() > 0) {
            mainView.appendTextArea("\nGet all issued books, found " + books.size() + " books:");
            mainView.appendTextArea(booksToString(books));
        } else {
            mainView.appendTextArea("\nIssued books are not found");
        }
    }

    public void searchIssuedBooksByBorrower(Borrower borrower) {
        List<Book> books = bookModel.searchIssuedBooksByBorrower(borrower.getId());
        if (books.size() > 0) {
            mainView.appendTextArea("\nGet all issued books borrowed by " + borrower.getName() + ", found " + books.size() + " books:");
            mainView.appendTextArea(booksToString(books));
        } else {
            mainView.appendTextArea("\nIssued books borrowed by " + borrower.getName() + " are not found");
        }
    }
        public void searchBooksByDonor(Donor donor) {
        List<Book> books = bookModel.searchBooksByDonor(donor.getId());
        if (books.size() > 0) {
            mainView.appendTextArea("\nGet all books donated by " + donor.getName() + ", found " + books.size() + " books:");
            mainView.appendTextArea(booksToString(books));
        } else {
            mainView.appendTextArea("\n"+donor.getName() + " did not donate any book");
        }
    }

    public ObservableList<Book> getAvailableBooks() {
        List<Book> books = bookModel.getAvailableBooks();
        ObservableList<Book> obs = FXCollections.observableArrayList();
        for (Book b : books) {
            obs.add(b);
        }
        return obs;
    }

    public ObservableList<Book> getAllBooks() {
        List<Book> books = bookModel.getAllBooks();
        ObservableList<Book> obs = FXCollections.observableArrayList();
        for (Book b : books) {
            obs.add(b);
        }
        return obs;
    }

    public ObservableList<Book> getIssueddBooks() {
        List<Book> books = bookModel.getAllIssuedBooks();
        ObservableList<Book> obs = FXCollections.observableArrayList();
        for (Book b : books) {
            obs.add(b);
        }
        return obs;
    }

    public ObservableList<Book> getIssuedBooksByBorrower(Borrower borrower) {
        List<Book> books = bookModel.searchIssuedBooksByBorrower(borrower.getId());
        ObservableList<Book> obs = FXCollections.observableArrayList();
        for (Book b : books) {
            obs.add(b);
        }
        return obs;
    }

}
