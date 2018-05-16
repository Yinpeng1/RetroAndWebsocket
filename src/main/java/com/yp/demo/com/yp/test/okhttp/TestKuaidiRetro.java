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
import java.util.LinkedHashMap;
import java.util.Map;

//这是调用快递的接口（不是快递100）
public class TestKuaidiRetro {

    public static String getYunDaInfo(String waybill) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://www.kuaidi.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RetroSendRequest retroSendRequest = retrofit.create(RetroSendRequest.class);
        Call<String> call = retroSendRequest.getKuaiDiInterfaceTraceInfo(waybill);
        String s = null;
        try {
            Response<String> jsonObjectResponse = call.execute();
            System.out.println(jsonObjectResponse);
            s = Util.decodeUnicode(jsonObjectResponse.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getYunDaInfo("3839998850701"));
    }
}
