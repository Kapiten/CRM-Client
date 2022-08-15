/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbm.crm.entity;

import org.json.simple.JSONObject;

/**
 *
 * @author Tebogo Sibiya
 */
public class DbTransactions {

    private Integer dbTransactionId;
    private String description;
    private int personId;
    private String dateDone;

    public DbTransactions() {
    }

    public DbTransactions(Integer dbTransactionId) {
        this.dbTransactionId = dbTransactionId;
    }

    public DbTransactions(Integer dbTransactionId, String description, int personId, String dateDone) {
        this.dbTransactionId = dbTransactionId;
        this.description = description;
        this.personId = personId;
        this.dateDone = dateDone;
    }

    public Integer getDbTransactionId() {
        return dbTransactionId;
    }

    public void setDbTransactionId(Integer dbTransactionId) {
        this.dbTransactionId = dbTransactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getDateDone() {
        return dateDone;
    }

    public void setDateDone(String dateDone) {
        this.dateDone = dateDone;
    }
    
    public DbTransactions fromJSON(JSONObject jo) {
        DbTransactions dbt = new DbTransactions();
        try {
            if(jo!=null) {
                dbt.setDbTransactionId(Integer.parseInt(jo.get("dbTransactionId").toString()));
                dbt.setDescription(jo.get("description").toString());
                dbt.setPersonId(Integer.parseInt(jo.get("personId").toString()));
                dbt.setDateDone(jo.get("dateDone").toString());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return dbt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dbTransactionId != null ? dbTransactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DbTransactions)) {
            return false;
        }
        DbTransactions other = (DbTransactions) object;
        if ((this.dbTransactionId == null && other.dbTransactionId != null) || (this.dbTransactionId != null && !this.dbTransactionId.equals(other.dbTransactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cbm.web.rest.webappmysqlrest.entity.DbTransactions[ dbTransactionId=" + dbTransactionId + " ]";
    }
    
}
