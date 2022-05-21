/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

/**
 *
 * @author Faqiu Sun
 */
public class User {

    //Primary key
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private boolean isAdmin;

    public User(String userName, String password, String fullName, String email, String phoneNumber, boolean isAdmin) {

        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return String.format("User User name:%s, Full Name:%s, Password:%s, Email:%s, Phone:%s, isAdmin:%s",
                userName,fullName,password,email,phoneNumber,isAdmin?"Yes":"No");

    }

}
