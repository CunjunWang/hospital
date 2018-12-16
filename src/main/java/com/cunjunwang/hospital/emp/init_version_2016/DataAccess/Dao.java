package com.cunjunwang.hospital.emp.init_version_2016.DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/12.
 */

public class Dao {

    protected static String dbClassName = "com.mysql.jdbc.Driver";
    protected static String serverName = "localhost";
    protected static String port = "3306";
    protected static String dbName = "CPSC304_PROJECT";
    protected static String dbURL = "jdbc:mysql://" + serverName + ":" + port + "/"
            + dbName + "?characterEncoding=utf8&useSSL=false";
    protected static String userName = "root";
    protected static String password = "65261599Duck";
    protected static Connection connection = null;

    // Setup database connection
    static {
        try {
            System.out.println("Start connecting to database...");
            if (connection == null) {
                System.out.println("Finding class...");
                Class.forName(dbClassName);
                System.out.println("Find class!");
            }
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Dao::Caught ClassNotFoundException..." + cnfe.getMessage());
        }
        try {
            connection = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("Connect successfully!");
        } catch (SQLException sqle) {
            System.out.println("Dao::Caught SQLException..." + sqle.getMessage());
        } catch (Exception e) {
            System.out.println("Dao::Caught Exception..." + e.getMessage());
        }
    }

    // default constructor
    public Dao() {
    }

    // Credit to a Chinese Java Tutorial book "Java from beginning to expert",
    // ISBN 978-7-302-28756-8
    // Chapter 28
    // Source code can be viewed at http://en.verysource.com/code/3903828_1/dao.java.html
    // From line 345 to 360
    public static ResultSet findForResultSet(String sql) {
        if (connection == null)
            return null;
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Credit to a Chinese Java Tutorial book "Java from beginning to expert",
    // ISBN 978-7-302-28756-8
    // Chapter 28
    // Source code can be viewed at http://en.verysource.com/code/3903828_1/dao.java.html
    // From line 384 to 404
    public static List findForList(String sql) {
        List<List> list = new ArrayList<>();
        ResultSet resultSet = findForResultSet(sql);
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                List<String> row = new ArrayList<String>();
                for (int i = 1; i <= columnCount; i++) {
                    String str = resultSet.getString(i);
                    if (str != null && !str.isEmpty())
                        str = str.trim();
                    row.add(str);
                }
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean checkLogin(String name, String password, String usertype, String userID) throws SQLException {
        ResultSet resultSet = findForResultSet("SELECT * FROM Userlist where username='" + name + "' AND password='"
                + password + "' AND usertype='" + usertype + "' AND userid=" + userID + ";");
        System.out.println("SELECT * FROM Userlist where username='" + name + "' AND password='"
                + password + "' AND usertype='" + usertype + "' AND userid=" + userID + ";");

        if(resultSet == null){
            return false;
        }
        return resultSet.next();
    }

    public Connection getConnection(){
        return connection;
    }
}
