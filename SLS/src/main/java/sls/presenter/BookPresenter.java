/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.presenter;

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
        if(bookModel.addNewBook(title, author)>0){
            mainView.appendTextArea("Adding book successfully");
        }
        else{
            mainView.appendTextArea("Adding book failed");            
        }
    }

}
