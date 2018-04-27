package com.yp.demo.com.yp.test.okhttp;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetroConfig {


    @Bean
    public Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().client(new OkHttpClient())
                .baseUrl("http://baidu.kuaidi100.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }

}
