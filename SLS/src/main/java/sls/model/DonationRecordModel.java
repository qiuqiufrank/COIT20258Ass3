/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
public class DonationRecordModel implements IDonationRecordModel {

    private PreparedStatement donateBooksStatement;
    private Connection connection;
/**
 * Insertion of donation record
 * @param connection 
 */
    public DonationRecordModel(Connection connection) {
        this.connection = connection;
        try {
            //Define PreparedStatments
            donateBooksStatement = connection.prepareStatement(
                    "INSERT INTO DonationRecord( DonorId ,BookId,Quantity,DonationDate  ) VALUES"
                    + "(?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
 * 
 * @param donorId
 * @param bookId
 * @param quantity
 * @param donationDate
 * @return Adds a new donation record and returns it on the object 
 */
    @Override
    public DonationRecord donateBooks(long donorId, String bookId, int quantity, Date donationDate) {
        try {
            //Set Parameters for the PreparedStatement
            donateBooksStatement.setLong(1, donorId);
            donateBooksStatement.setString(2, bookId);
            donateBooksStatement.setInt(3, quantity);
            donateBooksStatement.setDate(4, new java.sql.Date(donationDate.getTime()));

            donateBooksStatement.executeUpdate();
            ResultSet generatedKeys = donateBooksStatement.getGeneratedKeys();

            if (!generatedKeys.next()) {
                return null;
            }
            DonationRecord dr = new DonationRecord(donorId, bookId, quantity, donationDate);
            dr.setId(generatedKeys.getLong(1));

            return dr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
