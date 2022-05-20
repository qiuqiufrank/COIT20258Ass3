/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.List;

/**
 *
 * @author Faqiu Sun
 */
public interface IBookModel {

    public Book addNewBook(String title, String author);

    public List<Book> getAllIssuedBooks();

    public List<Book> searchByTitle(String title);

    public List<Book> searchByAuthor(String author);

    public List<Book> getAvailableBooks();

    public Book updateBorrowedCount(Book book);

}
