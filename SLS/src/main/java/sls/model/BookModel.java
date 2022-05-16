/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Faqiu Sun
 */
public class BookModel implements IBookModel {


    //JDBC connection
    private Connection connection;

    private PreparedStatement addBookStatement;
    private PreparedStatement allIssuedBookStatement;
    private PreparedStatement allOverdueReturnStatement;

    public BookModel(Connection connection) {
        this.connection = connection;
        try {
            //Connecte to Mysql
            //    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //Define PreparedStatments
            addBookStatement = connection.prepareStatement(
                    "INSERT INTO Book(Title ,Author ,Copies ,BorrowedCount ) VALUES"
                    + "(?,?,?,?)"
            );
            allIssuedBookStatement= connection.prepareStatement(
                    "SELECT * FROM Book where Returned=false"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int addNewBook(String title, String author) {

        try {
            //Set Parameters for the PreparedStatement
            addBookStatement.setString(1, title);
            addBookStatement.setString(2, author);
            addBookStatement.setString(3, "0");
            addBookStatement.setString(4, "0");
            return addBookStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
    public List<Book> getAllIssuedBooks(){
        try (ResultSet resultSet = allIssuedBookStatement.executeQuery()) {
            List<Book> results = new ArrayList<Book>();
            //Loop for every taxpayer in results
            while (resultSet.next()) {
                results.add(new Book(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("Author"),
                        resultSet.getInt("Copies"),
                        resultSet.getInt("BorrowedCount")                        
                ));
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
