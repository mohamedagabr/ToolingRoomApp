package com.elsewedyt.toolingapp.dao;

import com.elsewedyt.toolingapp.Logging.logging;
import com.elsewedyt.toolingapp.db.DbConnect;
import com.elsewedyt.toolingapp.models.User;

import java.sql.Connection;

public class UserDao {
    public User loadUserData(int userId) {
        User user = null;
        return user;
    }

    public int logIn(String userName, String password) {
        int emp_id = -1;
        Connection con = DbConnect.getConnect();
        try {

        }catch (Exception e){
            logging.logException("ERROR",this.getClass().getName(),"logIn",e);
        }finally {
            try {
                con.close();
            }catch (Exception ex){

            }
        }
        return emp_id;
    }
}
