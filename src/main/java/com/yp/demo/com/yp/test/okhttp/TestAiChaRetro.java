package com.yp.demo.com.yp.test.okhttp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestAiChaRetro {

    public static String getAiChaInfo(String waybill) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("https://biz.trace.ickd.cn/yd/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RetroSendRequest retroSendRequest = retrofit.create(RetroSendRequest.class);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("mailNo",waybill);
        map.put("tk","5bbaddce");
        String t = String.valueOf(System.currentTimeMillis());
        map.put("tm",t);
        map.put("callback","_jqjsp");
        map.put("_"+(t+1)+"=","");
        Call<String> call = retroSendRequest.getAiChaTraceInfo(waybill, map);
        String s = null;
        try {
            Response<String> jsonObjectResponse = call.execute();
//            System.out.println(jsonObjectResponse);
            if (StringUtils.isEmpty(jsonObjectResponse)) {
                return "0";
            }
            s = jsonObjectResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static class SelectWayBill implements Runnable{
        private TestAiChaRetro testAiChaRetro;

        public SelectWayBill(TestAiChaRetro aiChaRetro){
            this.testAiChaRetro = aiChaRetro;
        }
        @Override
        public void run() {
            getAiChaInfo("3839999344061");
            System.out.println(getAiChaInfo("3839999344061"));
            try{
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
    }
    public static void main(String[] args) {
//        TestAiChaRetro ts = new TestAiChaRetro();
//        SelectWayBill selectWayBill = new SelectWayBill(ts);
//        ExecutorService es = Executors.newFixedThreadPool(20);
//        for (int i = 0; i < 100000; i++) {
//           es.execute(selectWayBill);
//        }
//        es.shutdown();
        System.out.println(getAiChaInfo("3839998850701"));
    }
}
