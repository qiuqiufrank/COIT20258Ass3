/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.presenter;

import java.util.List;
import sls.model.Book;
import sls.model.IBookModel;
import sls.model.IUserModel;
import sls.model.User;
import sls.view.IMainView;

/**
 *
 * @author Faqiu Sun
 * @edited Hirvi
 */
public class UserPresenter {

    private IUserModel userModel;
    private IMainView mainView;



    public UserPresenter(IUserModel userModel, IMainView mainView) {
        this.userModel = userModel;
        this.mainView = mainView;
    }
/**
 * 
 * @param userName
 * @param password
 * @param fullName
 * @param email
 * @param phoneNumber 
 * Add a new user with all the params as the details
 */
    public void addAUser(String userName, String password, String fullName, String email, String phoneNumber) {
        User user = new User(userName, password, fullName, email, phoneNumber, false);
        User nUser = userModel.addUser(user);
        if (nUser == null) {
            mainView.appendTextArea("\nAdding user:" + user.getUserName() + " failed");
        } else {
            mainView.appendTextArea("\nAdding user:" + user.getUserName() + " successfully");
            mainView.appendTextArea(nUser.toString());
        }
    }

    /**
     * Search the users
     */
    public void searchAllUsers() {

        List<User> users = userModel.getAllUsers();
        String s = "\nTable of all users: \n";
        String headerString = String.format("User Name\tFull Name\tPassword\tEmail\tPhone\tIs Admin");
        s += headerString + "\n";
        for (User u : users) {
            String line = String.format("%s\t%s\t%s\t%s\t%s\t%s",
                    u.getUserName(), u.getFullName(), u.getPassword(), u.getEmail(), u.getPhoneNumber(), u.isIsAdmin() ? "Yes" : "No"
            );
            s += line + "\n";
        }
        mainView.appendTextArea(s);
    }

    
    /**
     * 
     * @param userName Deletes the user with this username
     */
    public void deleteAUser(String userName) {
        if (userModel.DeleteAUser(userName) >0) {
            mainView.appendTextArea("\nDeleting user:" + userName + " successfully");
        } else {
            mainView.appendTextArea("\nDeleting user:" + userName + " failed");
        }
    }

}
