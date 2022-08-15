package com.cbm.crm.entity;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tebogo Sibiya
 */
public class AccountType {

    private Integer accountTypeId;
    private String accountName;
    private String description;

    public AccountType() {
    }

    public AccountType(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public AccountType(Integer accountTypeId, String accountName, String description) {
        this.accountTypeId = accountTypeId;
        this.accountName = accountName;
        this.description = description;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static AccountType fromJSON(String json) {
        try {
            return json!=null&&!json.isEmpty()?fromJSON((JSONObject)new JSONParser().parse(json)):new AccountType();
        } catch(Exception ex) {
            ex.printStackTrace();
            return new AccountType();
        }
    }
    
    public static AccountType fromJSON(JSONObject jo) {
        AccountType at = new AccountType();
        try {
            if(jo!=null) {
                at.setAccountTypeId(jo.containsKey("accountTypeId")?Integer.parseInt(jo.get("accountTypeId").toString()):0);
                at.setAccountName(jo.containsKey("accountName")?jo.get("accountName").toString():"");
                at.setDescription(jo.containsKey("description")?jo.get("description").toString():"");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return at;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountTypeId != null ? accountTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountType)) {
            return false;
        }
        AccountType other = (AccountType) object;
        if ((this.accountTypeId == null && other.accountTypeId != null) || (this.accountTypeId != null && !this.accountTypeId.equals(other.accountTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cbm.web.soap.webappsoapmysql.entity.AccountType[ accountTypeId=" + accountTypeId + " ]";
    }

    
}
