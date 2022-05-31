/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.List;

/**
 *To be implemented by User model
 * @author Faqiu Sun
 * @edited Hirvi
 */
public interface IUserModel {

    public User searchValidUser(String name, String password);

    public User addUser(User user);

    public List<User> getAllUsers();

    public int DeleteAUser(String userName);
}
