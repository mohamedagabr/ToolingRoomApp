package com.elsewedyt.toolingapp.dao;

import com.elsewedyt.toolingapp.Logging.logging;
import com.elsewedyt.toolingapp.db.DEF;
import com.elsewedyt.toolingapp.db.DbConnect;
import com.elsewedyt.toolingapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public static User checkLogin(String username,String pass){
        User user = null ;
        Connection con = DbConnect.getConnect();
        try {
            String query = "select emp_id ,user_name,password,full_name from users where user_name = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setEmp_id(rs.getInt(DEF.USERS_EMP_ID));
                user.setUsername(rs.getString(DEF.USERS_USERNAME));
                user.setPassword(rs.getString(DEF.USERS_PASSWORD));
                user.setFullname(rs.getString(DEF.USERS_FULLNAME));
            }
        }catch (Exception e){
            logging.logException("ERROR",UserDao.class.getName(),"checkLogin",e);
        }finally {
            try {
                con.close();
            }catch (Exception ex){

            }
        }
        return user;
    }



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
