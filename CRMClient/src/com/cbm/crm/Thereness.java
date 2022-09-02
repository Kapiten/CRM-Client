/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbm.crm;

import com.cbm.crm.entity.Account;
import com.cbm.crm.entity.AccountType;
import com.cbm.crm.entity.Person;
import com.cbm.crm.entity.Role;
import java.util.ArrayList;

/**
 *
 * @author Tebogo
 */
public class Thereness {
    private static Thereness be;
    //public ArrayList<Person> people = new ArrayList<>();
    //public ArrayList<Account> accounts = new ArrayList<>();
    //public ArrayList<AccountType> accountTypes = new ArrayList<>();
    //public ArrayList<Role> roles = new ArrayList<>();
    
    public Person person;
    public Account account;
    public AccountType accountType;
    public Role role;
    
    public String dateFormat = "dd MM yyyy hh:mm:ss:SSS";
    public int[] current= new int[4];
    public String theString = "This is the String";
    
    private Thereness() {
        
    }
    
    public static Thereness be() {
        return be==null?new Thereness():be;
    }
    
    public void save() {
        
    }
    
}
