package com.yp.demo.com.yp.test.okhttp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sourceforge.tess4j.Tesseract;
import okhttp3.OkHttpClient;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestTessecract {

    public static String getYunDaInfo(String waybill, String hh, String yzm) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://ykjcx.yundasys.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RetroSendRequest retroSendRequest = retrofit.create(RetroSendRequest.class);
        Map<String, String> map = new LinkedHashMap<>();
        String t = String.valueOf(System.currentTimeMillis());
        Call<String> call = retroSendRequest.getTraceInfoInYunDa(waybill ,hh, yzm);
//        Call<String> call = retroSendRequest.getTraceInfoInYunDa(waybill, type);
        String s = null;
        try {
            Response<String> jsonObjectResponse = call.execute();
            System.out.println(jsonObjectResponse);
            if (StringUtils.isEmpty(jsonObjectResponse)) {
                return "0";
            }
            s = jsonObjectResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void saveJpg() throws Exception {
        //new一个URL对象
        URL url = new URL("http://ykjcx.yundasys.com/zb1qBpg2.php");
        //打开链接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File("BeautyGirl.jpg");
        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    public static String getResultFromJpg() throws Exception{
        File imageFile = new File("BeautyGirl.jpg");//图片位置
        Tesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath("C:\\tesseract-master\\tessdata");//设置tessdata位置
        instance.setLanguage("osd");//选择字库文件（只需要文件名，不需要后缀名）
        String result = instance.doOCR(imageFile);//开始识别
        System.out.println(result+"========截取前");
        String realResult="";
        if (!StringUtils.isEmpty(result)){
            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i)>=48 && result.charAt(i)<=57){
                    realResult+=result.charAt(i);
                } else {
                    return realResult;
                }
            }
        }
        return  realResult;
    }

    public static void main(String[] args) throws Exception{
        saveJpg();
        String result = getResultFromJpg();
        System.out.println(result+"========截取后");
        System.out.println(getYunDaInfo("3839999344061", "23",result));
    }
}
