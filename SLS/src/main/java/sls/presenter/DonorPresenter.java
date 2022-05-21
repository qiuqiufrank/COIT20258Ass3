/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.presenter;

import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sls.model.Book;
import sls.model.DonationRecord;
import sls.model.Donor;
import sls.model.IBookModel;
import sls.model.IDonationRecordModel;
import sls.model.IDonorModel;
import sls.view.IMainView;

/**
 *
 * @author Faqiu Sun
 */
public class DonorPresenter {

    private IDonorModel donorModel;
    private IDonationRecordModel donationRecordModel;
    private IBookModel bookModel;
    private IMainView mainView;

    public DonorPresenter(IDonorModel donorModel, IDonationRecordModel donationRecordModel, IBookModel bookModel, IMainView mainView) {
        this.donorModel = donorModel;
        this.donationRecordModel = donationRecordModel;
        this.bookModel = bookModel;
        this.mainView = mainView;
    }

    public void addADonor(String name, String fullName, String email, String phone) {

        Donor donor = donorModel.addNewDonor(name, fullName, email, phone);
        if (donor == null) {
            mainView.appendTextArea("\nAdding a donor failed");
        } else {
            mainView.appendTextArea("\nAdding the donor successfully:");
            mainView.appendTextArea(donor.toString());
        }
    }

    public ObservableList<Donor> getAllDonors() {
        List<Donor> donors = donorModel.getAllDonors();
        ObservableList<Donor> ods = FXCollections.observableArrayList();
        for (Donor d : donors) {
            ods.add(d);
        }
        return ods;
    }

    public void donateBooks(Donor donor, Book book, int copies, Date donationDate) {
        DonationRecord dr = donationRecordModel.donateBooks(donor.getId(), book.getId(), copies, donationDate);

        if (dr == null) {
            mainView.appendTextArea("\n Failed to donate Book:" + book.getTitle() + " donated by:" + donor.getName());
        } else {
            book.setCopies(book.getCopies() + copies);
            Book nbook = bookModel.updateCopiesCount(book);
            if (nbook!=null) {
                mainView.appendTextArea("\n Succeeded to donate Book:" + nbook.getTitle() + " donated by:" + donor.getName() + ":");
                mainView.appendTextArea(dr.toString());
            }

        }

    }

}
