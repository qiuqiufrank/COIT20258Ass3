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
    private PreparedStatement allAvailableBooksStatement;
    private PreparedStatement queryByTitleStatement;
    private PreparedStatement queryByAuthorStatement;
    private PreparedStatement queryByTitleAndAuthorStatement;

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

            queryByTitleAndAuthorStatement = connection.prepareStatement(
                    "SELECT * FROM Book where Title=? and Author=?"
            );
            queryByTitleStatement = connection.prepareStatement(
                    "SELECT * FROM Book where Title=? "
            );
            queryByAuthorStatement = connection.prepareStatement(
                    "SELECT * FROM Book where Author=? "
            );
            allIssuedBookStatement = connection.prepareStatement(
                    "SELECT * FROM Book where BorrowedCount>0 "
            );

            allAvailableBooksStatement = connection.prepareStatement(
                    "SELECT * FROM Book where (Copies-BorrowedCount)>0 "
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Book addNewBook(String title, String author) {
        try {
            //Set Parameters for the PreparedStatement
            addBookStatement.setString(1, title);
            addBookStatement.setString(2, author);
            addBookStatement.setString(3, "0");
            addBookStatement.setString(4, "0");
            addBookStatement.executeUpdate();
            return queryByTitleAndAuthor(title, author);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Book> parseBooks(ResultSet resultSet) {
        try {
            List<Book> results = new ArrayList<Book>();
            //Loop for every book in results
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

    @Override
    public List<Book> searchByTitle(String title) {
        try {
            queryByTitleStatement.setString(1, title);
            ResultSet resultSet = queryByTitleStatement.executeQuery();
            return parseBooks(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        try {
            queryByAuthorStatement.setString(1, author);
            ResultSet resultSet = queryByAuthorStatement.executeQuery();
            return parseBooks(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Book queryByTitleAndAuthor(String title, String author) {

        try {
            queryByTitleAndAuthorStatement.setString(1, title);
            queryByTitleAndAuthorStatement.setString(2, author);
            ResultSet resultSet = queryByTitleAndAuthorStatement.executeQuery();

            //Loop for every taxpayer in results
            if (resultSet.next()) {
                return new Book(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("Author"),
                        resultSet.getInt("Copies"),
                        resultSet.getInt("BorrowedCount")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Book> getAllIssuedBooks() {
        try (ResultSet resultSet = allIssuedBookStatement.executeQuery()) {
            List<Book> results = new ArrayList<Book>();
            //Loop for every book in results
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

    @Override
    public List<Book> getAvailableBooks() {
        try (ResultSet resultSet = allAvailableBooksStatement.executeQuery()) {
            List<Book> results = new ArrayList<Book>();
            //Loop for every book in results
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
