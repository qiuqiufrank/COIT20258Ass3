/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.Date;

/**
 *
 * @author Faqiu Sun
 */
public interface IDonationRecordModel {

     public DonationRecord donateBooks(long donorId, String bookId, int quantity, Date donationDate);
}
