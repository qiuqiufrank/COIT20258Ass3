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
    
}
