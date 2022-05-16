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

    int addNewBook(String title, String author);
    List<Book> getAllIssuedBooks();
}
