/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.sql.Connection;

/**
 *
 * @author Faqiu Sun
 */
public class UserModel implements IUserModel {

    //JDBC connection
    private Connection connection;

    public UserModel(Connection connection) {
        this.connection = connection;
    }
    

    public User findValidUser(String name, String password) {
        return new User();
    }
}
