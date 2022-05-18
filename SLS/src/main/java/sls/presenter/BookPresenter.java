/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.presenter;

import java.util.List;
import sls.model.Book;
import sls.model.IBookModel;
import sls.view.IMainView;

/**
 *
 * @author Faqiu Sun
 */
public class BookPresenter {

    private IBookModel bookModel;
    private IMainView mainView;

    public BookPresenter(IBookModel bookModel, IMainView mainView) {
        this.bookModel = bookModel;
        this.mainView = mainView;
    }

    public void addNewBook(String title, String author) {

        Book book = bookModel.addNewBook(title, author);
        if (book == null) {
            mainView.appendTextArea("Adding book failed");
        } else {
            mainView.appendTextArea("Adding the book successfully:");
            mainView.appendTextArea(book.toString());
        }
    }

    public void searchByTitle(String title) {
        List<Book> books = bookModel.searchByTitle(title);
    }

    public void searchByAuthor(String author) {
        List<Book> books = bookModel.searchByAuthor(author);
    }

}
