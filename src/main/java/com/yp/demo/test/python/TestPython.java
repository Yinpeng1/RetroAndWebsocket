package com.yp.demo.test.python;

import org.python.util.PythonInterpreter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestPython {

    public static void main(String[] args) throws Exception {
        String depCity = "南京";
        String arrCity = "三亚";
        String depdate = "2018-10-01";
//        testQuNaEr(depCity,arrCity,depdate);
        TestPython ts = new TestPython();
        ts.testXieCheng(depCity, arrCity, depdate);
//        ts.testQuNaEr(depCity, arrCity, depdate);
    }

    public void testXieCheng(String depCity, String arrCity, String depdate) throws Exception{
        System.out.println("正在爬取携程的航班信息，请稍后。。。");
        String[] args1 = new String[] { "python", "C:\\Users\\pyin\\PycharmProjects\\Test\\flight\\xiechengflight.py", depCity, arrCity, depdate};
        try {
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor();
            System.out.println("携程爬取结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testQuNaEr(String depCity, String arrCity, String depdate) throws Exception{
        System.out.println("正在爬取去哪儿的航班信息，请稍后。。。");
        String[] args1 = new String[] { "python", "C:\\Users\\pyin\\PycharmProjects\\Test\\flight\\qunaerFlight.py", depCity, arrCity, depdate};
        try {
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor();
            System.out.println("去哪儿爬取结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
