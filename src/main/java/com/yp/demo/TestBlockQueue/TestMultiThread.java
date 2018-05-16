package com.yp.demo.TestBlockQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMultiThread {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static class AddData{
        private static String URL;
        private static String USER;
        private static String PASSWORD;
        private static Connection CONNECTION ;

        static {
             URL="jdbc:mysql://127.0.0.1:3306/imooc?useUnicode=true&amp;characterEncoding=utf-8";
             USER="root";
             PASSWORD="";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public  void  add() throws Exception{
            //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            String s="insert into user(id,user_name,user_password) values(1,?,123)";
            PreparedStatement pst = null;
            try {
                pst=CONNECTION.prepareStatement(s);
                pst.setString(1,"nihao");
                pst.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                pst.close();
            }
        }

        public void close() throws Exception {
            CONNECTION.close();
        }
    }

    public static class MutiAdd implements Runnable{
        private AddData addData;
        private static Object lock = new Object();

        private MutiAdd(AddData addData){
            this.addData = addData;
        }

        @Override
        public void run() {
//            synchronized (lock){
                try {
                    addData.add();
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//            }
        }
    }
    public static void main(String[] args) throws Exception{
        AddData addData = new AddData();
        MutiAdd mutiAdd = new MutiAdd(addData);
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i =0; i < 10; i++){
            es.execute(mutiAdd);
        }
        countDownLatch.await();
        es.shutdown();
    }
}
