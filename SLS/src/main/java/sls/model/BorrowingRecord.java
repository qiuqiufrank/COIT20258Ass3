/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.Date;

/**
 * BorrowingRecord getter and setter ,constructor
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
public class BorrowingRecord {

    private Long id;
    private Borrower borrower;
    private Book book;
    private Date issueDate;
    private Date expectedReturnDate;
    private boolean returned;

    public BorrowingRecord(Borrower borrower, Book book, Date issueDate, Date expectedReturnDate, boolean returned) {
        this.borrower = borrower;
        this.book = book;
        this.issueDate = issueDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returned = returned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return String.format("Borrowing Record Id:%d, issued date:%s, expected return date:%s, book name:%s, borrower:%s, returned:%s",
                id, issueDate.toString(), expectedReturnDate.toString(), book.getTitle(), borrower.getName(), returned ? "yes" : "no");
    }

}
