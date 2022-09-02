/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbm.crm.entity;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Tebogo
 */
public class LoginDetails {

    private Integer loginDetailsId;
    private String username;
    private String password;
    private int personId;

    public LoginDetails() {
    }

    public LoginDetails(Integer loginDetailsId) {
        this.loginDetailsId = loginDetailsId;
    }

    public LoginDetails(Integer loginDetailsId, String username, String password, int personId) {
        this.loginDetailsId = loginDetailsId;
        this.username = username;
        this.password = password;
        this.personId = personId;
    }

    public Integer getLoginDetailsId() {
        return loginDetailsId;
    }

    public void setLoginDetailsId(Integer loginDetailsId) {
        this.loginDetailsId = loginDetailsId;
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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    
    public LoginDetails fromJSON(String json) {
        try {
            return fromJSON((JSONObject)new JSONParser().parse(json));
        } catch(Exception ex) {
            ex.printStackTrace();
            return new LoginDetails();
        }
    }
    
    public LoginDetails fromJSON(JSONObject jo) {
        LoginDetails ld = new LoginDetails();
        if(jo!=null) {
            ld.setLoginDetailsId(Integer.parseInt(jo.get("loginDetailsId").toString()));
            ld.setUsername(jo.get("username").toString());
            ld.setPassword(jo.get("password").toString());
            ld.setPersonId(Integer.parseInt(jo.get("personId").toString()));
        }
        
        return ld;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginDetailsId != null ? loginDetailsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginDetails)) {
            return false;
        }
        LoginDetails other = (LoginDetails) object;
        if ((this.loginDetailsId == null && other.loginDetailsId != null) || (this.loginDetailsId != null && !this.loginDetailsId.equals(other.loginDetailsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cbm.web.rest.webappmysqlrest.entity.LoginDetails[ loginDetailsId=" + loginDetailsId + " ]";
    }
    
}
