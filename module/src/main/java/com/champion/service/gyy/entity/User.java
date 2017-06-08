package com.champion.service.gyy.entity;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/6 0006 10:09
 */
public class User implements java.io.Serializable{

    private static final long serialVersionUID = 3473679586082000054L;

    private Long id;

    private String username;

    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
