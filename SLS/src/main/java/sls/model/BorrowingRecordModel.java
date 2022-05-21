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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Faqiu Sun
 */
public class BorrowingRecordModel implements IBorrowingRecordModel {

    //JDBC connection
    private Connection connection;
    private PreparedStatement issueABookStatement;
    private PreparedStatement returnABookStatement;
  

    public BorrowingRecordModel(Connection connection) {
        this.connection = connection;
        try {
            //Define PreparedStatments
            issueABookStatement = connection.prepareStatement(
                    "INSERT INTO BorrowingRecord(IssuedDate ,ExpectedReturn , Returned, BorrowerId ,BookId ) VALUES"
                    + "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            returnABookStatement = connection.prepareStatement(
                    "UPDATE BorrowingRecord SET Returned=? WHERE BorrowerId=? and BookId=?"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BorrowingRecord issueABook(Book book, Borrower borrower, Date issuedDate, Date expectedReturn) {
        try {
            //Set Parameters for the PreparedStatement
            issueABookStatement.setDate(1, new java.sql.Date(issuedDate.getTime()));
            issueABookStatement.setDate(2, new java.sql.Date(expectedReturn.getTime()));
            issueABookStatement.setBoolean(3, false);
            issueABookStatement.setLong(4, borrower.getId());
            issueABookStatement.setString(5, book.getId());
            issueABookStatement.executeUpdate();

            ResultSet generatedKeys = issueABookStatement.getGeneratedKeys();

            if (!generatedKeys.next()) {
                return null;
            }
            BorrowingRecord br = new BorrowingRecord(borrower, book, issuedDate, expectedReturn, false);
            br.setId(generatedKeys.getLong(1));

            return br;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int returnABook(Book book, Borrower borrower) {
        try {

            returnABookStatement.setBoolean(1, true);
            returnABookStatement.setLong(2, borrower.getId());
            returnABookStatement.setString(3, book.getId());
            return returnABookStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
