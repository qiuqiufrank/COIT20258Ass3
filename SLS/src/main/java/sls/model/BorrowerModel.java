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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Faqiu Sun
 */
public class BorrowerModel implements IBorrowerModel {

    //JDBC connection
    private Connection connection;
    private PreparedStatement addABorrowerStatement;
    private PreparedStatement getAllBorrowersStatement;

    public BorrowerModel(Connection connection) {
        this.connection = connection;
        try {
            //Define PreparedStatments
            addABorrowerStatement = connection.prepareStatement(
                    "INSERT INTO Borrower(Name,Email,PhoneNumber ) VALUES"
                    + "(?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            getAllBorrowersStatement = connection.prepareStatement(
                    "SELECT * FROM Borrower "
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Borrower addABorrower(String name, String phone, String email) {
        try {
            //Set Parameters for the PreparedStatement
            addABorrowerStatement.setString(1, name);
            addABorrowerStatement.setString(2, email);
            addABorrowerStatement.setString(3, phone);
            addABorrowerStatement.executeUpdate();

            ResultSet generatedKeys = addABorrowerStatement.getGeneratedKeys();

            if (!generatedKeys.next()) {
                return null;
            }
            Borrower borrower = new Borrower(name, email, phone);

            borrower.setId(generatedKeys.getLong(1));

            return borrower;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Borrower> getAllBorrowers() {
        try (ResultSet resultSet = getAllBorrowersStatement.executeQuery()) {
            List<Borrower> results = new ArrayList<Borrower>();
            //Loop for every book in results
            while (resultSet.next()) {
                Borrower b = new Borrower(
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")
                );
                b.setId(resultSet.getLong("Id"));
                results.add(b);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
