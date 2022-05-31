/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sls.model;

import java.util.List;

/**
 * To be implemented by Donor model
 * @author Faqiu Sun
 * @edited Hirvi
 */
public interface IDonorModel {

    public Donor addNewDonor(String name, String fullName, String email, String phone);
    public List<Donor> getAllDonors() ;
}
