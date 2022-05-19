/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.view;

import sls.presenter.BookPresenter;
import sls.presenter.BorrowerPresenter;
import sls.presenter.BorrowingRecordPresenter;

/**
 *
 * @author Faqiu Sun
 */
public interface IMainView {

    public void bind(BookPresenter bookPresenter, BorrowerPresenter borrowerPresenter, BorrowingRecordPresenter borrowingRecordPresenter);

    public void appendTextArea(String m);

    public void promptMessage(String m);
}
