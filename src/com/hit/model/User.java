
package com.hit.model;

import javax.persistence.*;

@Entity
@Table (name = "USERS")
public class User {

    @Id
    @Column (name = "username", nullable = false)
    private String username;

    @Column (name = "password")
    private String password;
    @Column (name = "email")
    private String email;
    @Column (name = "firstname")
    private String firstname;
    @Column (name = "lastname")
    private String lastname;
    @Column(name = "phone")
    private String phone;



    public User(){}
    public User(String username, String password){
        this.username=username;
        this.password=password;

    }
    public User(String userName,String firstname, String lastname, String email, String phone, String newpassword){
        this.username=userName;
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.password=newpassword;
        this.phone=phone;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

