package com.elsewedyt.toolingapp.dao;

import com.elsewedyt.toolingapp.Logging.logging;
import com.elsewedyt.toolingapp.db.DEF;
import com.elsewedyt.toolingapp.db.DbConnect;
import com.elsewedyt.toolingapp.models.User;
import com.elsewedyt.toolingapp.models.UserContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static com.elsewedyt.toolingapp.services.SettingService.APP_BUNDLE;
import static com.elsewedyt.toolingapp.services.WindowUtils.ALERT;
import static com.elsewedyt.toolingapp.services.WindowUtils.ALERT_ERROR;

public class UserDao {
    public static User checkLogin(String username, String pass) {
        User user = null;
        Connection con = DbConnect.getConnect();
        PreparedStatement ps = null;
        try {
            String query = "select emp_id,user_name,password,full_name from users where user_name = ? and password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setEmp_id(rs.getInt(DEF.USERS_EMP_ID));
                user.setUsername(rs.getString(DEF.USERS_USERNAME));
                user.setPassword(rs.getString(DEF.USERS_PASSWORD));
                user.setFullname(rs.getString(DEF.USERS_FULLNAME));
                UserContext.setCurrentUser(user);
            }else{
                ALERT("",APP_BUNDLE().getString("Login Error"),ALERT_ERROR);
            }
        } catch (Exception e) {
            //logging.logException("ERROR", UserDao.class.getName(), "checkLogin", e);
            logging.logExpWithMessage("ERROR", UserDao.class.getName(), "checkLogin", e,"sql",ps.toString());

        } finally {
            try {
                con.close();
            } catch (Exception ex) {

            }
        }
        return user;
    }
   public static ObservableList<User> getUsers(){
        Connection con = DbConnect.getConnect();
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            String query = "select id,emp_id,user_name,password,full_name,phone,role,active,creation_date from tooling.users ";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
               list.add(new User(rs.getInt(DEF.USERS_ID),
                       rs.getInt(DEF.USERS_EMP_ID),
                       rs.getString(DEF.USERS_USERNAME),
                       rs.getString(DEF.USERS_PASSWORD),
                       rs.getString(DEF.USERS_FULLNAME),
                       rs.getString(DEF.USERS_PHONE),
                       rs.getInt(DEF.USERS_ROLE),
                       rs.getInt(DEF.USERS_ACTIVE),
                       rs.getString(DEF.USERS_CREATION_DATE)));
            }
        }catch (Exception ex){
            logging.logException("ERROR", UserDao.class.getName(), "getUsers", ex);
        }
        return list;
   }

    public User loadUserData(int userId) {
        User user = null;
        return user;
    }
    public int logIn(String userName, String password) {
        int userId = -1;
        return userId;
    }
    public static boolean insertUser(User us){
        Connection con = DbConnect.getConnect();
        PreparedStatement ps = null ;
        try {
           String query = "insert into tooling.users (emp_id,user_name,password,full_name,phone,role,active,creation_date) values(?,?,?,?,?,?,?,?) " ;
           ps = con.prepareStatement(query);
           ps.setInt(1,us.getEmp_id());
           ps.setString(2,us.getUsername());
           ps.setString(3,us.getPassword());
           ps.setString(4,us.getFullname());
           ps.setString(5,us.getPhone());
           ps.setInt(6,us.getRole());
           ps.setInt(7,us.getActive());
           ps.setString(8,us.getCreation_date());
           return ps.execute();

        }catch (Exception e){
            logging.logExpWithMessage("ERROR", UserDao.class.getName(), "insertUser", e,"sql",ps.toString());
        }finally {
            try {
                con.close();
                ps.close();
            }catch (Exception ex){
                logging.logException("ERROR", UserDao.class.getName(), "insertUser close :", ex);
            }
        }
        return true ;
    }
    public static  User getUserByEmpId(int emp_id){
        User us = new User() ;
        Connection con = DbConnect.getConnect();
        PreparedStatement ps = null ;
        try {
            String query = "select * from tooling.users  where emp_id = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1,emp_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                us.setId(rs.getInt(DEF.USERS_ID));
                us.setEmp_id(rs.getInt(DEF.USERS_EMP_ID));
                us.setUsername(rs.getString(DEF.USERS_USERNAME));
                us.setPassword(rs.getString(DEF.USERS_PASSWORD));
                us.setFullname(rs.getString(DEF.USERS_FULLNAME));
                us.setPhone(rs.getString(DEF.USERS_PHONE));
                us.setRole(rs.getInt(DEF.USERS_ROLE));
                us.setActive(rs.getInt(DEF.USERS_ACTIVE));
                us.setCreation_date(DEF.USERS_CREATION_DATE);
            }
        }catch (Exception e){
            logging.logExpWithMessage("ERROR", UserDao.class.getName(), "getUserByEmpId", e,"sql",ps.toString());
        }finally {
            try {
                con.close();
                ps.close();
            }catch (Exception ex){
                logging.logException("ERROR", UserDao.class.getName(), "getUserByEmpId close :", ex);
            }
        }

        return us;
    }
    public static boolean updateUser(User us){
        Connection con = DbConnect.getConnect();
        PreparedStatement ps = null ;
        try {
        String query = "update tooling.users set password = ?,full_name = ?,phone = ?, role = ?,active = ? where emp_id = ? ";
        ps = con.prepareStatement(query);
        //ps.setString(1,us.getUsername());
        ps.setString(1,us.getPassword());
        ps.setString(2,us.getFullname());
        ps.setString(3,us.getPhone());
        ps.setInt(4,us.getRole());
        ps.setInt(5,us.getActive());
        ps.setInt(6,us.getEmp_id());
        return ps.execute();
        }catch (Exception e){
            logging.logExpWithMessage("ERROR", UserDao.class.getName(), "updatetUser", e,"sql",ps.toString());
        }finally {
            try {
                con.close();
                ps.close();
            }catch (Exception ex){
                logging.logException("ERROR", UserDao.class.getName(), "updatetUser close :", ex);
            }
        }
        return true;
    }
    public static boolean deleteUser(int emp_id){
        Connection con = DbConnect.getConnect();
        PreparedStatement ps = null ;
        try {
            String query = "delete from tooling.users  where emp_id = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1,emp_id);
            return ps.execute();
        }catch (Exception e){
            logging.logExpWithMessage("ERROR", UserDao.class.getName(), "deleteUser", e,"sql",ps.toString());
        }finally {
            try {
                con.close();
                ps.close();
            }catch (Exception ex){
                logging.logException("ERROR", UserDao.class.getName(), "deleteUser close :", ex);
            }
        }
        return true;
    }

    }


