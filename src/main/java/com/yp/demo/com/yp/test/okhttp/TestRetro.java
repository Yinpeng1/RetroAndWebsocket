package com.yp.demo.com.yp.test.okhttp;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.util.StringUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

//这是原来的快递100接口
public class TestRetro {

    public static final String TMP = "0.9824289731522113";

    public static String getInfo(String type, String postid) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://baidu.kuaidi100.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RetroSendRequest retroSendRequest = retrofit.create(RetroSendRequest.class);
//        Call<String> call = retroSendRequest.getTraceInfo("ems","1169116743242","0.9824289731522113");
        Call<String> call = retroSendRequest.getTraceInfo(type, postid, TMP);
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

    public static void testCount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        RetroSendRequest retroSendRequest = retrofit.create(RetroSendRequest.class);
        Call<String> call = retroSendRequest.testCount("http://localhost:8080/");
        try {
            Response<String> jsonObjectResponse = call.execute();
            System.out.println(jsonObjectResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
            System.out.println(getInfo("yunda","3839999344061"));
    }

}
