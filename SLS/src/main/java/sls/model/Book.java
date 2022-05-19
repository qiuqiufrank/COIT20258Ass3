/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

/**
 *
 * @author Faqiu Sun
 */
public class Book {
    private String id;
    private String title;
    private String author;
    private int copies;
    private int borrowedCount;

    public Book(String id, String title, String author, int copies, int borrowedCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.borrowedCount = borrowedCount;
    }

    @Override
    public String toString() {
        return String.format("book id:%s, title=%s, author=%s, copies=%d, borrowedCount=%d", id,title,author,copies,borrowedCount);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }
    
    
}
