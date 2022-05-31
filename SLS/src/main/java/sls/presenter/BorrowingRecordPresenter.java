/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.presenter;

import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sls.model.Book;
import sls.model.Borrower;
import sls.model.BorrowingRecord;
import sls.model.IBookModel;
import sls.model.IBorrowingRecordModel;
import sls.view.IMainView;

/**
 *
 * @author Faqiu Sun
 * @edited Hirvi
 * The borrowing Record presenter
 */
public class BorrowingRecordPresenter {

    private IBorrowingRecordModel borrowingRecordModel;
    private IBookModel bookModel;
    private IMainView mainView;

    public BorrowingRecordPresenter(IBorrowingRecordModel borrowingRecordModel, IBookModel bookModel, IMainView mainView) {
        this.borrowingRecordModel = borrowingRecordModel;
        this.bookModel = bookModel;
        this.mainView = mainView;
    }
/**
 * 
 * @param book The book to be issued
 * @param borrower The borrower who is taking the book
 * @param issuedDate The date of issuing
 * @param returnDate Expected return date
 * This will issue a new book to a borrower
 * 
 */
    public void IssueABook(Book book, Borrower borrower, Date issuedDate, Date returnDate) {
        //No copies avaliable
        if (book.getCopies() - book.getBorrowedCount() < 1) {
            mainView.appendTextArea("\nError, not enough copies:");
            mainView.appendTextArea(book.toString());
            return;
        }
        book.setBorrowedCount(book.getBorrowedCount() + 1);
        Book nBook = bookModel.updateBorrowedCount(book);
        if (nBook == null) {
            mainView.appendTextArea("\nIssuing a book failed");
            return;
        }

        BorrowingRecord br = borrowingRecordModel.issueABook(nBook, borrower, issuedDate, returnDate);
        if (br == null) {
            mainView.appendTextArea("\nIssuing a book failed");
        } else {
            mainView.appendTextArea("\nIssuing a book successfully:");
            mainView.appendTextArea(br.toString());
        }

    }
/**
 * 
 * @param book This will add the book to available in the DB
 * @param borrower The borrower who is returning the book.
 */
    public void returnABook(Book book, Borrower borrower) {
        if (borrowingRecordModel.returnABook(book, borrower) > 0) {

            book.setBorrowedCount(book.getBorrowedCount() - 1);
            Book nBook = bookModel.updateBorrowedCount(book);
            if (nBook != null) {
                mainView.appendTextArea("\nRecturning a book:"+book.getTitle()+" by "+borrower.getName()+" successfully");
                return;
            }

        }
        mainView.appendTextArea("\nRecturning a book " +book.getTitle()+" by "+borrower.getName()+" failed");

    }

}
