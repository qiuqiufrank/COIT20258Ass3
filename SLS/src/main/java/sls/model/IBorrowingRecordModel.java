/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.Date;

/**
 *
 * @author Faqiu Sun
 */
public interface IBorrowingRecordModel {

    public BorrowingRecord issueABook(Book book, Borrower borrower,Date issueDate,Date expectedReturn) ;
    public BorrowingRecord returnABook(Book book, Borrower borrower);
}
