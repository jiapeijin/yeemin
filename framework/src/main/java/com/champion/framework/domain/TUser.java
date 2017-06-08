package com.champion.framework.domain;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/7 0007 14:29
 */
public class TUser implements java.io.Serializable{

    private static final long serialVersionUID = 7131822789284088219L;

    private String id;

    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
