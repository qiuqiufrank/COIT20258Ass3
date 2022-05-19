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
import sls.model.IBorrowingRecordModel;
import sls.view.IMainView;

/**
 *
 * @author Faqiu Sun
 */
public class BorrowingRecordPresenter {

    private IBorrowingRecordModel borrowingRecordModel;
    private IMainView mainView;

    public BorrowingRecordPresenter(IBorrowingRecordModel borrowingRecordModel, IMainView mainView) {
        this.borrowingRecordModel = borrowingRecordModel;
        this.mainView = mainView;
    }

    public void IssueABook(Book book,Borrower borrower,Date issuedDate,Date returnDate){
        
        borrowingRecordModel.issueABook(book, borrower, issuedDate, returnDate);
    }

}
