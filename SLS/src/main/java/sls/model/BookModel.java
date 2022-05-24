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
    private PreparedStatement allBooksStatement;

    private PreparedStatement queryByTitleStatement;
    private PreparedStatement queryByAuthorStatement;
    private PreparedStatement queryByTitleAndAuthorStatement;
    private PreparedStatement queryByDonorStatement;
    private PreparedStatement queryIssuedBooksByBorrowerStatement;

    private PreparedStatement updateBorrowedCountStatement;
    private PreparedStatement updateCopiesStatement;

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

            allOverdueReturnStatement = connection.prepareStatement("select * from Book where Id in "
                    + "(select DISTINCT BookId  FROM BorrowingRecord where ExpectedReturn < NOW() and returned =false);");

            allAvailableBooksStatement = connection.prepareStatement(
                    "SELECT * FROM Book where (Copies-BorrowedCount)>0 "
            );
            allBooksStatement = connection.prepareStatement(
                    "SELECT * FROM Book"
            );

            updateBorrowedCountStatement = connection.prepareStatement(
                    "UPDATE  Book SET BorrowedCount =?  where Id=?"
            );
            updateCopiesStatement = connection.prepareStatement(
                    "UPDATE  Book SET Copies =?  where Id=?"
            );
            queryIssuedBooksByBorrowerStatement = connection.prepareStatement(
                    "SELECT * FROM Book where Id in (SELECT DISTINCT BookId FROM BorrowingRecord where BorrowerId=? and returned=false);"
            );
            queryByDonorStatement = connection.prepareStatement(
                    "SELECT * FROM Book where Id in (SELECT DISTINCT BookId FROM DonationRecord where DonorId=?)"
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
            addBookStatement.setInt(3, 0);
            addBookStatement.setInt(4, 0);
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
        return new ArrayList<Book>();
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
        return new ArrayList<Book>();
    }

    @Override
    public List<Book> getAllOverdueBooks() {
        try {
            ResultSet resultSet = allOverdueReturnStatement.executeQuery();
            return parseBooks(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();
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
        return new ArrayList<Book>();
    }

    @Override
    public List<Book> searchBooksByDonor(Long donorId) {
        try {
            queryByDonorStatement.setLong(1, donorId);
            ResultSet resultSet = queryByDonorStatement.executeQuery();
            return parseBooks(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();
    }

    private List<Book> resultToBookList(ResultSet resultSet) {
        try {
            List<Book> results = new ArrayList<Book>();
            // Loop for every book in results
            while (resultSet.next()) {
                results.add(new Book(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("Author"),
                        resultSet.getInt("Copies"),
                        resultSet.getInt("BorrowedCount")));
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();

    }

    private Book queryByTitleAndAuthor(String title, String author) {

        try {
            queryByTitleAndAuthorStatement.setString(1, title);
            queryByTitleAndAuthorStatement.setString(2, author);
            ResultSet resultSet = queryByTitleAndAuthorStatement.executeQuery();
            List<Book> books = resultToBookList(resultSet);
            if (books.size() > 0) {
                return books.get(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getAllIssuedBooks() {
        try (ResultSet resultSet = allIssuedBookStatement.executeQuery()) {
            return resultToBookList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();
    }

    @Override
    public List<Book> getAvailableBooks() {
        try (ResultSet resultSet = allAvailableBooksStatement.executeQuery()) {
            return resultToBookList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();
    }

    @Override
    public List<Book> getAllBooks() {
        try (ResultSet resultSet = allBooksStatement.executeQuery()) {
            return resultToBookList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();
    }

    @Override
    public Book updateBorrowedCount(Book book) {
        try {
            // Set Parameters for the PreparedStatement
            updateBorrowedCountStatement.setInt(1, book.getBorrowedCount());
            updateBorrowedCountStatement.setString(2, book.getId());
            updateBorrowedCountStatement.executeUpdate();
            return book;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book updateCopiesCount(Book book) {
        try {
            // Set Parameters for the PreparedStatement
            updateCopiesStatement.setInt(1, book.getCopies());
            updateCopiesStatement.setString(2, book.getId());
            updateCopiesStatement.executeUpdate();
            return book;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> searchIssuedBooksByBorrower(Long BorrowerId) {
        try {
            queryIssuedBooksByBorrowerStatement.setLong(1, BorrowerId);
            ResultSet resultSet = queryIssuedBooksByBorrowerStatement.executeQuery();
            return resultToBookList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();

    }

}
