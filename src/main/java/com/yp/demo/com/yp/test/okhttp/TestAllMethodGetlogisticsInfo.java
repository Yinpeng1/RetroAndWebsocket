package com.yp.demo.com.yp.test.okhttp;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TestAllMethodGetlogisticsInfo {

    //线程本地保存random，多线程会产生很对一样的随机数
    private static ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
    //线程安全，但是会造成系统抢占资源
    private static Random random = new Random();

    /**
     * 从爱查快递查询
     * @param waybill
     * @return
     */
    public  String getlogisticsInfoFromAiCha(String waybill){
         Retrofit retrofit = new Retrofit.Builder().client(new OkHttpClient()).baseUrl("https://biz.trace.ickd.cn/yd/")
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
            System.out.println(jsonObjectResponse);
            s = jsonObjectResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 从快递100的另外一个接口查询
     * @param waybill
     * @return
     */
    public  String getLogisticsInfoFromOtherKuaidiInterface(String waybill) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("https://sp0.baidu.com/9_Q4sjW91Qh3otqbppnN2DJv/pae/channel/data/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RetroSendRequest retroSendRequest = retrofit.create(RetroSendRequest.class);
        Map<String, String> map = new LinkedHashMap<>();
        String t = String.valueOf(System.currentTimeMillis());
        map.put("cb","jQuery110204249189975275043"+t);
        map.put("appid","4001");
        map.put("com","yunda");
        map.put("nu", waybill);
        map.put("vcode","");
        map.put("token","");
        map.put("_",t+2);
        Call<String> call = retroSendRequest.getKuaiDiTraceInfo(map);
        String s = null;
        try {
            Response<String> jsonObjectResponse = call.execute();
            System.out.println(jsonObjectResponse);
            s = Util.decodeUnicode(jsonObjectResponse.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 从快递的接口查询
     * @param waybill
     * @return
     */
    public  String getLogisticsInfoFromKuaiDi(String waybill) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) {
//        System.out.println(getlogisticsInfoFromAiCha("3839998850701"));
//        System.out.println(getLogisticsInfoFromKuaiDi("3839998850701"));
//        System.out.println(getLogisticsInfoFromOtherKuaidiInterface("3839998850701"));
    }
}
