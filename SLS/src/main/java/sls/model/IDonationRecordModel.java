/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.Date;

/**
 *To be implemented by DonationRecord model
 * @author Faqiu Sun
 * @edited Hirvi
 */
public interface IDonationRecordModel {

     public DonationRecord donateBooks(long donorId, String bookId, int quantity, Date donationDate);
}
