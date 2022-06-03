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
 * DB manipulator for Donor
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
public class DonorModel implements IDonorModel {
    //JDBC connection

    private Connection connection;

    private PreparedStatement addDonorStatement;
    private PreparedStatement allDonorsStatement;

    /**
     * Insertion of donor
     *
     * @param connection
     */
    public DonorModel(Connection connection) {
        this.connection = connection;
        try {
            //Connecte to Mysql
            //    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //Define PreparedStatments
            addDonorStatement = connection.prepareStatement(
                    "INSERT INTO Donor(FullName ,Email ,PhoneNumber ) VALUES"
                    + "(?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            allDonorsStatement = connection.prepareStatement(
                    "SELECT * FROM Donor"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param name
     * @param fullName
     * @param email
     * @param phone
     * @return Adds the new donor with parameters passed on the method and
     * returns it as a object
     */
    @Override
    public Donor addNewDonor( String fullName, String email, String phone) {
        try {
            //Set Parameters for the PreparedStatement
  
            addDonorStatement.setString(1, fullName);
            addDonorStatement.setString(2, email);
            addDonorStatement.setString(3, phone);
            addDonorStatement.executeUpdate();

            ResultSet generatedKeys = addDonorStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                return null;
            }
            Donor donor = new Donor( fullName, email, phone);

            donor.setId(generatedKeys.getLong(1));

            return donor;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return All the donors in the Database
     */
    @Override
    public List<Donor> getAllDonors() {
        try (ResultSet resultSet = allDonorsStatement.executeQuery()) {
            List<Donor> results = new ArrayList<Donor>();
            while (resultSet.next()) {
                Donor donor = new Donor( resultSet.getString("FullName"),
                        resultSet.getString("Email"), resultSet.getString("PhoneNumber"));
                donor.setId(resultSet.getLong("Id"));
                results.add(donor);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Donor>();
    }
}
