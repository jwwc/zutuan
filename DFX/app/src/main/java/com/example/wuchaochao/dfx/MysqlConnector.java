package com.example.wuchaochao.dfx;

/**
 * Created by wuchaochao on 2018/1/27.
 */

public interface MysqlConnector {
    public void Insert(String username,String schoolname ,String number,String sex);

    public void delete(String tablename);

    public void query(String schoolname);

    public void create( String schoolname,String activityname,String activity_decrible,String login_time);

}