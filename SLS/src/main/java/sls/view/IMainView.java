/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.view;

import sls.presenter.BookPresenter;

/**
 *
 * @author Faqiu Sun
 */
public interface IMainView {
    void bind(BookPresenter bp);
    void appendTextArea(String m);
    void promptMessage(String m);
}
