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
 * This class handless the Donors presenter
 * @author Faqiu Sun
 * @edited Hirvi
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
/**
 * 
 * @param name
 * @param fullName
 * @param email
 * @param phone 
 * This will add a donor with all the details
 */
    public void addADonor( String fullName, String email, String phone) {

        Donor donor = donorModel.addNewDonor(fullName, email, phone);
        if (donor == null) {
            mainView.appendTextArea("\nAdding a donor failed");
        } else {
            mainView.appendTextArea("\nAdding the donor successfully:");
            mainView.appendTextArea(donor.toString());
        }
    }
/**
 * 
 * @return An observable list of all donors
 */
    public ObservableList<Donor> getAllDonors() {
        List<Donor> donors = donorModel.getAllDonors();
        ObservableList<Donor> ods = FXCollections.observableArrayList();
        for (Donor d : donors) {
            ods.add(d);
        }
        return ods;
    }
/**
 * 
 * @param donor Name of the donor 
 * @param book Book being donated
 * @param copies Total number of the copies
 * @param donationDate Date of donation
 */
    public void donateBooks(Donor donor, Book book, int copies, Date donationDate) {
        DonationRecord dr = donationRecordModel.donateBooks(donor.getId(), book.getId(), copies, donationDate);

        if (dr == null) {
            mainView.appendTextArea("\nFailed to donate Book:" + book.getTitle() + " donated by " + donor.getFullName());
        } else {
            book.setCopies(book.getCopies() + copies);
            Book nbook = bookModel.updateCopiesCount(book);
            if (nbook!=null) {
                mainView.appendTextArea("\nSucceeded to donate Book:" + nbook.getTitle() + " donated by" + donor.getFullName() + ":");
                mainView.appendTextArea(dr.toString());
            }

        }

    }

}
