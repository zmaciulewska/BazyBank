package com.pabwoopj;


import DataAccessObject.AccountDao;
import DataAccessObject.jdbcAccountDao;
import models.Account;
import java.sql.DatabaseMetaData;

import java.util.List;

public class App {


    public static void main(String[] argv) {
        AccountDao accDao = new jdbcAccountDao();
        Account a=new Account(3, 2, "nic", 43);

        accDao.delete(a);
        List<Account> accList = accDao.findAll();
        for( Account acc : accList ) {
            System.out.println(acc.toString());
        }



    }

}