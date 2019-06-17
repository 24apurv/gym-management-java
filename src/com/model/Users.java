
package com.model;

public class Users {
    
    private String username;
    private String password;
    private String usersname;
    private String branch;
    private String privilege;

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

    public String getUsersname() {
        return usersname;
    }

    public void setUsersname(String usersname) {
        this.usersname = usersname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public Users(String username, String password, String usersname, String branch, String privilege) {
        this.username = username;
        this.password = password;
        this.usersname = usersname;
        this.branch = branch;
        this.privilege = privilege;
    }

    public Users() {
    }

    @Override
    public String toString() {
        return "Users{" + "username=" + username + ", password=" + password + ", usersname=" + usersname + ", branch=" + branch + ", privilege=" + privilege + '}';
    }
    
    
}
