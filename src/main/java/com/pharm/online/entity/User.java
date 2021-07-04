package com.pharm.online.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Setter
@Getter
@ToString
@Entity
public class User{

    @Id
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String password;
    @Transient
    private String confirmPassword;
    private String role;
    public User()
    {

    }

    public User(String username, String firstname, String lastname, String email, String phone, String address) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public User(String username, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
    }


}
