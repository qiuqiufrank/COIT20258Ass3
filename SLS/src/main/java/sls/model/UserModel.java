/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import com.mysql.cj.callback.UsernameCallback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
public class UserModel implements IUserModel {

    //JDBC connection
    private Connection connection;
    private PreparedStatement queryByUserNameAndPasswordStatement;
//    private PreparedStatement updateUserStatement;
    private PreparedStatement addUserStatement;
    private PreparedStatement allUsersStatement;
    private PreparedStatement deleteUserStatement;
/**
 * Retrieve all forms if results in the form of user objects
 * @param connection 
 */
    public UserModel(Connection connection) {
        this.connection = connection;
        try {
            //Connecte to Mysql
            //    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            allUsersStatement = connection.prepareStatement(
                    "SELECT * FROM User"
            );
            queryByUserNameAndPasswordStatement = connection.prepareStatement(
                    "SELECT * FROM User where UserName=? and Password=?"
            );
//            updateUserStatement = connection.prepareStatement(
//                    "UPDATE User SET Password=? FullName=? email=? phoneNumber=? isAdmin=? where UserName=?"
//            );
            addUserStatement = connection.prepareStatement(
                    "INSERT User(UserName, Password, FullName, email, phoneNumber, isAdmin) Values(?,?,?,?,?,?)"
            );

            deleteUserStatement = connection.prepareStatement(
                    "DELETE FROM User where UserName=?"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
 * 
 * @param user
 * @return Deletes the user in the db and returns 1 or -1 if failed.
 */
    public int deleteUser(User user) {

        try {
            //Set Parameters for the PreparedStatement
            deleteUserStatement.setString(1, user.getUserName());
            return deleteUserStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;

    }
/**
 * 
 * @param user
 * @return Adds the new user to the db and returns it
 */
    @Override
    public User addUser(User user) {

        try {
            //Set Parameters for the PreparedStatement
            addUserStatement.setString(1, user.getUserName());
            addUserStatement.setString(2, user.getPassword());
            addUserStatement.setString(3, user.getFullName());
            addUserStatement.setString(4, user.getEmail());
            addUserStatement.setString(5, user.getPhoneNumber());
            addUserStatement.setBoolean(6, user.isIsAdmin());
            if (addUserStatement.executeUpdate() > 0) {
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

//    public User updateUser(User user) {
//
//        try {
//            // Set Parameters for the PreparedStatement
//            updateUserStatement.setString(1, user.getPassword());
//            updateUserStatement.setString(2, user.getFullName());
//            updateUserStatement.setString(3, user.getEmail());
//            updateUserStatement.setString(4, user.getPhoneNumber());
//            updateUserStatement.setBoolean(5, user.isIsAdmin());
//            updateUserStatement.setString(6, user.getUserName());
//            if (updateUserStatement.executeUpdate() > 0) {
//                return user;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    
    /**
     * 
     * @param userName
     * @param password
     * @return A user that has a username and password as the passed parameters
     */
    @Override
    public User searchValidUser(String userName, String password) {
        try {
            queryByUserNameAndPasswordStatement.setString(1, userName);
            queryByUserNameAndPasswordStatement.setString(2, password);
            ResultSet resultSet = queryByUserNameAndPasswordStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getString("UserName"),
                        resultSet.getString("FullName"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getBoolean("isAdmin")
                );
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
/**
 * 
 * @return List of all the users present in the system
 */
    @Override
    public List<User> getAllUsers() {
        try {

            ResultSet resultSet = allUsersStatement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("UserName"),
                        resultSet.getString("Password"),
                        resultSet.getString("FullName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getBoolean("isAdmin")
                );
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
/**
 * 
 * @param userName
 * @return Will delete the user from the db that has the username has the 
 * string parameter
 */
    public int DeleteAUser(String userName) {
        try {
            //Set Parameters for the PreparedStatement
            deleteUserStatement.setString(1,userName);

            return deleteUserStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
