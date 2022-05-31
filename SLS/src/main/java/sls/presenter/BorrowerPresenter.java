/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.presenter;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sls.model.Book;
import sls.model.Borrower;
import sls.model.IBorrowerModel;
import sls.view.IMainView;

/**
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
public class BorrowerPresenter {

    private IBorrowerModel borrowerModel;
    private IMainView mainView;

    public BorrowerPresenter(IBorrowerModel borrowerModel, IMainView mainView) {
        this.borrowerModel = borrowerModel;
        this.mainView = mainView;
    }
/**
 * 
 * @return All the borrowers
 */
    public ObservableList<Borrower> getAllBorrowers() {
        List<Borrower> borrowers = borrowerModel.getAllBorrowers();
        ObservableList<Borrower> obs = FXCollections.observableArrayList();
        for (Borrower b : borrowers) {
            obs.add(b);
        }
        return obs;
    }
    /**
     * 
     * @return Return observable of all the borrowers
     */
        public ObservableList<Borrower> getAllIssuedBorrowers() {
        List<Borrower> borrowers = borrowerModel.getAllIssuedBorrowers();
        ObservableList<Borrower> obs = FXCollections.observableArrayList();
        for (Borrower b : borrowers) {
            obs.add(b);
        }
        return obs;
    }
/**
 * 
 * @param name
 * @param phone
 * @param email 
 * 
 * This will add a new borrower
 */
    public void addABorrower(String name, String phone, String email) {
        Borrower borrower = borrowerModel.addABorrower(name, phone, email);
        if (borrower == null) {
            mainView.appendTextArea("Adding borrower failed");
        } else {
            mainView.appendTextArea("Adding the borrower successfully:");
            mainView.appendTextArea(borrower.toString());
        }
    }
/**
 * 
 * @return The set borrower model
 */
    public IBorrowerModel getBorrowerModel() {
        return borrowerModel;
    }
/**
 * Set the borrower model
 * @param borrowerModel 
 */
    public void setBorrowerModel(IBorrowerModel borrowerModel) {
        this.borrowerModel = borrowerModel;
    }
/**
 * 
 * @return The main view to be used 
 */
    public IMainView getMainView() {
        return mainView;
    }
/**
 * 
 * @param mainView Initialize the main view
 */
    public void setMainView(IMainView mainView) {
        this.mainView = mainView;
    }

}
