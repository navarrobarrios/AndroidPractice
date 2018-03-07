package com.example.navarro.androidpractice.beans.unpersistible;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by ${Navarro} on 3/7/18.
 */

@IgnoreExtraProperties
public class User {
    //region Variables
    private String username;
    private String password;
    private String name;
    private String lastname;
    private int age;
    //endregion

    //region Constructor's
    public User() {
    }

    public User(String username, String password, String name, String lastname, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }
    //endregion

    //region Setters && Getters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //endregion
}
