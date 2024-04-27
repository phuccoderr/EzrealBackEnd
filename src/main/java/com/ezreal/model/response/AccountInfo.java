package com.ezreal.model.response;

public class AccountInfo {
    private String name;
    private String picture;
    private String email;
    private boolean is_violate;

    public AccountInfo() {
    }

    public AccountInfo(String name, String picture, String email,Boolean is_violate) {
        this.name = name;
        this.picture = picture;
        this.email = email;
        this.is_violate = is_violate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String avatar) {
        this.picture = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIs_violate() {
        return is_violate;
    }

    public void setIs_violate(boolean is_violate) {
        this.is_violate = is_violate;
    }
}
