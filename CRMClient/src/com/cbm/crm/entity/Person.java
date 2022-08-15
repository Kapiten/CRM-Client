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
 * @author Tebogo Sibiya
 */
public class Person {

    private Integer personId;
    private String firstname;
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    private String email;
    private String cellnumber1;
    private String cellnumber2;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    private String fax;
    private String gender;
    private String dateCreated;

    public Person() {
    }

    public Person(Integer personId) {
        this.personId = personId;
    }

    public Person(Integer personId, String firstname, String lastname, String email, String cellnumber1, String cellnumber2, String fax, String gender) {
        this.personId = personId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.cellnumber1 = cellnumber1;
        this.cellnumber2 = cellnumber2;
        this.fax = fax;
        this.gender = gender;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellnumber1() {
        return cellnumber1;
    }

    public void setCellnumber1(String cellnumber1) {
        this.cellnumber1 = cellnumber1;
    }

    public String getCellnumber2() {
        return cellnumber2;
    }

    public void setCellnumber2(String cellnumber2) {
        this.cellnumber2 = cellnumber2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public static Person fromJSON(String json) {
        try {
            return json!=null&&!json.isEmpty()?fromJSON((JSONObject)new JSONParser().parse(json)):new Person();
        } catch(Exception ex) {
            ex.printStackTrace();
            return new Person();
        }
    }
    
    public static Person fromJSON(JSONObject jo) {
        Person p = new Person();
        try {
            if(jo!=null) {
                p.setPersonId(Integer.parseInt(jo.get("personId").toString()));
                p.setFirstname(jo.get("firstname").toString());
                p.setLastname(jo.get("lastname").toString());
                p.setGender(jo.get("gender").toString());
                p.setCellnumber1(jo.get("cellnumber1").toString());
                p.setCellnumber2(jo.get("cellnumber2").toString());
                p.setEmail(jo.get("Email").toString());
                p.setFax(jo.get("Fax").toString());
                p.setDateCreated(jo.get("dateCreated").toString());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }     
        return p;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cbm.web.rest.webappmysqlrest.entity.Person[ personId=" + personId + " ]";
    }
    
}
