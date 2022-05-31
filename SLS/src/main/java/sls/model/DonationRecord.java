/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.Date;

/**
 * Donation record getter setter
 * @author Faqiu Sun
 * @edited Hirvi
 */
public class DonationRecord {
   private  Long id;
   private Long donorId;
   private String bookId;
   private int quantity;
   private Date donationDate;

    public DonationRecord(Long donorId, String bookId, int quantity, Date donationDate) {
        this.donorId = donorId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.donationDate = donationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    @Override
    public String toString() {
        return String.format("Donate record: Id:%d, donator Id: %d, book Id: %s, quantity:%d, date:%s", id,donorId,bookId,quantity,donationDate.toString());
    }
    
   
    
}
