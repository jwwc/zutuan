package com.example.wuchaochao.dfx;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mysql.jdbc.Statement;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wuchaochao on 2018/1/27.
 */

public class MysqlService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
            return null;
    }

    public boolean Insert(String username,String schoolname ,String number,String sex,String province,String charateristic ) {
        /**
         * 注册表插入，注册表包括 省份，学好，姓名，性别，学校代号（或者学校名称）,头像存放位置,个性签名
         */
        String image_address = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            Connection con = null; //链接本地MYSQL
            con = DriverManager.getConnection("jdbc:mysql://123.206.187.205:3306/school_database", "root", "123456");
            Statement sta = (Statement) con.createStatement();
            boolean flag = sta.execute("INSERT INTO resign_table select null,"+province+","+schoolname+","+number+","+username+","+charateristic+","+sex+","+image_address+"");
            return flag;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;
    }
    public boolean delete(String activityname,String schoolname) {
        /**
         *删除发布的活动，需要传入活动名称
         */
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            Connection con = null; //链接本地MYSQL
            con = DriverManager.getConnection("jdbc:mysql://123.206.187.205:3306/school_database", "root", "123456");
            Statement sta = (Statement) con.createStatement();
            sta.executeUpdate("delete from"+schoolname+"where activity ="+activityname);
            return true;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean query(String schooolname) {
        /**
         * 查询自己学校的活动表
         */
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            Connection con = null; //链接本地MYSQL
            con = DriverManager.getConnection("jdbc:mysql://123.206.187.205:3306/school_database", "root", "123456");
            Statement sta = (Statement) con.createStatement();
            ResultSet resultSet = sta.executeQuery("selec * from"+schooolname);
            while(resultSet.next())
            {
               String result1 = resultSet.getString(1);
            }
            return true;
         } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean create( String schoolname) {
        /**
         * 创建自己学校的活动表,表的内容为，学校名称，活动名称，活动表述，保名时间，文件存储位置
         */
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            Connection con = null; //链接本地MYSQL
            con = DriverManager.getConnection("jdbc:mysql://123.206.187.205:3306/school_database", "root", "123456");
            Statement sta = (Statement) con.createStatement();
           boolean flag = sta.execute("create table "+schoolname+"( id int primary key not null,title varchar(20000) not null,describle varchar(300000),file_address varchar(20000),username varchar(200),number varchar(3000)");
           return  flag;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean update_activity(String title,String schoolname,String activity_decrible,String file_address,String username,String number ){
        /**
         * 修好活动表中的活动内容
         */
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            Connection con = null; //链接本地MYSQL
            con = DriverManager.getConnection("jdbc:mysql://123.206.187.205:3306/school_database", "root", "123456");
            Statement sta = (Statement) con.createStatement();
            boolean flag = sta.execute("update"+schoolname+"set title="+title+",set descirble="+activity_decrible+"file_address="+file_address);
            return flag;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean update_resign(String username,String image,String charateristic){
        /**
         * 修改注册表中的信息，可以修改用户名，头像，个性签名
         */
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            Connection con = null; //链接本地MYSQL
            con = DriverManager.getConnection("jdbc:mysql://123.206.187.205:3306/school_database", "root", "123456");
            Statement sta = (Statement) con.createStatement();
            boolean flag = sta.execute("update resign_table set username="+username+", A Dimage ="+image+"set charateristic ="+charateristic);
            return flag;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insert_activity(String number,String schoolname,String title,String  username,String file_address,String describle) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            Connection con = null; //链接本地MYSQL
            con = DriverManager.getConnection("jdbc:mysql://123.206.187.205:3306/school_database", "root", "123456");
            Statement sta = (Statement) con.createStatement();
            ResultSet resultSet = sta.executeQuery("");
            while (resultSet.next()) {
                String activitytable = resultSet.getString(1);
                if (schoolname == activitytable) {
                    boolean flag = sta.execute("insert into schoolname values(" + null + "," + title + "," + describle + "," + file_address + "," + username + "," + number);
                    return flag;
                }
            }
            boolean flag = create(schoolname);
            return flag;
            //boolean flag = sta.execute("insert into schoolname values("+null+","+title+","+describle+","+file_address+","+username+","+number);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return false;
    }
}
