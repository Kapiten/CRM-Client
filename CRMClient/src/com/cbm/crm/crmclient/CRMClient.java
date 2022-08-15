/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbm.crm.crmclient;

import com.cbm.crm.entity.LoginDetails;
import com.cbm.crm.entity.Role;
import com.cbm.crm.entity.client.LoginDetailsClient;
import com.cbm.crm.entity.client.RoleClient;

/**
 *
 * @author Sakkie
 */
public class CRMClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello, CRM World.");
        try {
            LoginDetailsClient client = new LoginDetailsClient();
            LoginDetails ld = client.findByUsername_JSON(LoginDetails.class, "cbm_eg@example.com");
            
            client.close();
            System.out.println("ld="+ld.getUsername());
            /*
                    
            RoleClient client = new RoleClient();
            Role r = client.findByName_JSON(Role.class, "Guest");
            System.out.println("_id="+r.getRoleId()
            +"\nname="+r.getRoleName()
            +"\ndescription="+r.getDescription());
            client.close();
            
        AccountTypeClient client  = new AccountTypeClient();
            AccountType at = client.findByName_JSON(AccountType.class, "Standard");
            System.out.println("_id="+at.getAccountTypeId()
            +"\nname="+at.getAccountName()
            +"\ndescription="+at.getDescription());
            client.create_JSON(new AccountType(0, "Micro", "Micro account."));
            System.out.println("Added Account Type: Micro.");
            client.create_JSON(new AccountType(0, "Standard", "Standard account."));
            System.out.println("Added Account Type: Standard.");
            AccountType at = client.find_JSON(AccountType.class, "14");
            System.out.println("Found account with 1: Name="+at.getAccountName());
            JSONArray ja = (JSONArray)new JSONParser().parse(client.findAll_JSON(String.class));
            System.out.println("Found all AccountType records, count="+ja.size());
            for(int a=0; a<ja.size(); a++) {
                JSONObject jo = (JSONObject)ja.get(a);
                AccountType atJo = AccountType.fromJSON(jo);
                System.out.println("Found records: "+(a+1)+"="+atJo.getAccountTypeId()+" "+atJo.getAccountName());
                client.remove(atJo.getAccountTypeId()+"");
                System.out.println("Removed record id: "+atJo.getAccountTypeId());
            }
        client.close();
            */
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
