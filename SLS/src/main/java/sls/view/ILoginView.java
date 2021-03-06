/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.view;

import sls.presenter.BookPresenter;
import sls.presenter.BorrowerPresenter;
import sls.presenter.BorrowingRecordPresenter;
import sls.presenter.DonorPresenter;
import sls.presenter.LoginPresenter;
import sls.presenter.UserPresenter;

/**
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
public interface ILoginView {

    public void bind(LoginPresenter loginPresenter);

    /**
     * Displays an alert used for Main view
     *
     * @param m
     */
    public void promptMessage(String m);
}
