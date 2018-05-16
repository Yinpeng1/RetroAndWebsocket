package com.yp.demo.com.yp.test.okhttp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import okhttp3.OkHttpClient;
import org.springframework.util.StringUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestYundaRetro {
    public static String getYunDaInfo(String waybill) {
        Gson gson = new GsonBuilder().setLenient().create();
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
            if (StringUtils.isEmpty(jsonObjectResponse)) {
                return "0";
            }
            s = Util.decodeUnicode(jsonObjectResponse.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getYunDaInfo("3839999344061"));
//        try {
//            File imageFile = new File("BeautyGirl.jpg");//图片位置
//            Tesseract instance = new Tesseract();  // JNA Interface Mapping
//            instance.setDatapath("C:\\tesseract-master\\tessdata");//设置tessdata位置
//            instance.setLanguage("osd");//选择字库文件（只需要文件名，不需要后缀名）
//            String result = instance.doOCR(imageFile);//开始识别
//            System.out.println(result);//打印图片内容
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
    }
}
