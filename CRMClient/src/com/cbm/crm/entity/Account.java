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
public class Account {

    private Integer accountId;
    private String accountNo;
    private int accountTypeId;
    private int personId;
    private int roleId;

    public Account() {
    }

    public Account(Integer accountId) {
        this.accountId = accountId;
    }

    public Account(Integer accountId, String accountNo, int accountTypeId, int personId, int roleId) {
        this.accountId = accountId;
        this.accountNo = accountNo;
        this.accountTypeId = accountTypeId;
        this.personId = personId;
        this.roleId = roleId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public Account fromJSON(String json) {
        try {
            return fromJSON((JSONObject)new JSONParser().parse(json));
        } catch(Exception ex) {
            ex.printStackTrace();
            return new Account();
        }
    }
    
    public Account fromJSON(JSONObject jo) {
        Account account = new Account();
        try {
            if(jo!=null) {
                account.setAccountId(Integer.parseInt(jo.get("accountId").toString()));
                account.setAccountNo(jo.get("accountNo").toString());
                account.setAccountTypeId(Integer.parseInt(jo.get("accountTypeId").toString()));
                account.setPersonId(Integer.parseInt(jo.get("personId").toString()));
                account.setRoleId(Integer.parseInt(jo.get("roleId").toString()));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return account;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cbm.web.rest.webappmysqlrest.entity.Account[ accountId=" + accountId + " ]";
    }
    
}
