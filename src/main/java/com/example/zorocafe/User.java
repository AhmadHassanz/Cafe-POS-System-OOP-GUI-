package com.example.zorocafe;

public class User {
    private String username;
    private String password;
    private UserType role;

    User(String username,String password,UserType role)
    {
        this.username = username;
        this.password = password;
        this.role = role;

    }
    //Setter
    public void setUsername(String username)

    {
        this.username = username;
    }
    public void setPassword(String password)

    {
        this.password = password;
    }
    public void setRole(UserType role)

    {
        this.role = role;
    }
    //Getters
    public String getUsername()
    {
        return username;
    }
    public String getPassword()

    {
        return password;
    }
    public UserType getRole()

    {
        return role;
    }

    //toString
    @Override
    public String toString()
    {
        return String.format("%s %s ",username,password);
    }


}
