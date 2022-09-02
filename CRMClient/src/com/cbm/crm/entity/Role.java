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
public class Role {

    private Integer roleId;
    private String roleName;
    private String description;

    public Role() {
    }

    public Role(Integer roleId) {
        this.roleId = roleId;
    }

    public Role(Integer roleId, String roleName, String description) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static Role fromJSON(String json) {
        try {
            return json!=null&&!json.isEmpty()?fromJSON((JSONObject)new JSONParser().parse(json)):new Role();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return new Role();
    }
    
    public static Role fromJSON(JSONObject jo) {
        Role role = new Role();
        try {
            if(jo!=null) {
                role.setRoleId(Integer.parseInt(jo.get("roleId").toString()));
                role.setRoleName(jo.get("roleName").toString());
                role.setDescription(jo.get("description").toString());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cbm.web.rest.webappmysqlrest.entity.Role[ roleId=" + roleId + " ]";
    }
    
}
