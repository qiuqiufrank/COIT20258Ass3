/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.List;

/**
 * To be implemented by book model
 * @author Faqiu Sun
 * @edited Hirvi
 */
public interface IBookModel {

    public Book addNewBook(String title, String author);

    public List<Book> searchByTitle(String title);

    public List<Book> searchByAuthor(String author);

    public List<Book> searchIssuedBooksByBorrower(Long BorrowerId);

    public List<Book> searchBooksByDonor(Long donorId);

    public List<Book> getAvailableBooks();

    public List<Book> getAllIssuedBooks();

    public List<Book> getAllOverdueBooks();

    public List<Book> getAllBooks();

    public Book updateBorrowedCount(Book book);

    public Book updateCopiesCount(Book book);
}
