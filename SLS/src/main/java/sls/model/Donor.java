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
public class Donor {

    private Long id;
    private String name;
    private String fullName;
    private String email;
    private String phone;

    public Donor(String name, String fullName, String email, String phone) {
        this.fullName = fullName;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Donor id:%d, name:%s, full name:%s, email:%s, phone number:%s", id,name,fullName,email,phone);
    }
    

}
