/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.Date;

/**
 *To be implemented by Borrowing record model
 * @author Faqiu Sun
 * @edited Hirvi
 */
public interface IBorrowingRecordModel {

    public BorrowingRecord issueABook(Book book, Borrower borrower, Date issueDate, Date expectedReturn);

    public int returnABook(Book book, Borrower borrower);
}
