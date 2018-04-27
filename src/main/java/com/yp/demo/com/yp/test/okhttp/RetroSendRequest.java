package com.yp.demo.com.yp.test.okhttp;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetroSendRequest {

    @Headers({
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
            "Content-Type: text/html; charset=UTF-8",
//            "Accept-Encoding: gzip, deflate",
            "Accept-Language: zh-CN,zh;q=0.9",
            "Connection: keep-alive",
            "Cookie: BDUSS=tJNX43UHNYMnhSUVNYUTY3UUJLTWJ4ZTdFclJuajNMTlFJSy1PSGRXZ2w5VjVaSVFBQUFBJCQAAAAAAAAAAAEAAACSvswLeGx4eGNjAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACVoN1klaDdZaE; __cfduid=d72ae7447e545cedac5c40edf8fd13b871509018547; BAIDUID=F926151FB05EEF6694BAFD28E0EDCA31:FG=1; BIDUPSID=B5111F6DD20945404242A3036E8694A3; PSTM=1520211510; MCITY=-289%3A; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; H_PS_PSSID=1438_21115_18560_22157; BDSFRCVID=PZksJeC62w16IROAC3ztrUEW7e388q7TH6ao32OXxnnTOyxv70epEG0PDf8g0Kub56lwogKK0eOTHkrP; H_BDCLCKID_SF=tJAOoIDMJIt3fP36q4bKbJ8XMfb-etJXfaRzB-OF5l8-h40ljxrCXU_dDp6JbqQ4Qbv-oKPy-C3xOKQphT-KXpkbhxTnWbcObJkj04JN3KJmsbL9bT3v5tj0jxvf2-biWbR-2Mbd2-ombRO4-TF5e5vyDfK; PSINO=5",
            "Upgrade-Insecure-Requests: 1",
            "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0"
    })
    @GET("query")
    Call<String> getTraceInfo(@Query("type") String type, @Query("postid")String postid, @Query("temp")String tmp);


    @GET
    Call<String> testCount(@Url String url);
}

