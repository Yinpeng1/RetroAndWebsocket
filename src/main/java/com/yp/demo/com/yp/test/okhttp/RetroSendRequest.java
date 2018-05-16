package com.yp.demo.com.yp.test.okhttp;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;


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
    Call<String> getTraceInfo(@Query("type") String type, @Query("postid") String postid, @Query("temp") String tmp);

    @Headers({
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
            "Accept-Encoding: gzip, deflate",
            "Accept-Language: zh-CN,zh;q=0.9",
            "Cookie: PHPSESSID=60nn11d9d17utkivpkv1v2hpq4; JSESSIONID=nggZh7YGhcB12TQT3LHzDPTZMN5wQFhCr2jnpJT5j6gmtS402l1n!-1412866116",
            "Host: ykjcx.yundasys.com",
            "Proxy-Connection: keep-alive",
            "Upgrade-Insecure-Requests: 1",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36"
    })
    @GET("go.php")
//    Call<String> getTraceInfoInYunDa(@Body Map<String, String> params);
    Call<String> getYzmInfoInYunDa(@Query("wen") String wen);


    @POST("go_wsd.php")
    @FormUrlEncoded
    Call<String> getTraceInfoInYunDa(@Field("wen") String waybill, @Field("hh") String hh, @Field("yzm") String yzm);

    @Headers({
            "Accept: */*",
            "Accept-Encoding: gzip, deflate, br",
            "Accept-Language: zh-CN,zh;q=0.9",
            "Connection: keep-alive",
            "Cookie: Hm_lvt_39418dcb8e053c84230016438f4ac86c=1526433568; Hm_lpvt_39418dcb8e053c84230016438f4ac86c=1526433568",
            "Host: biz.trace.ickd.cn",
            "Referer: https://www.ickd.cn/yd.html",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36"
    })
    @GET("{pathMailNo}")
    Call<String> getAiChaTraceInfo(@Path("pathMailNo") String pathMailNo, @QueryMap Map<String, String> map);

    @Headers({
            "Accept: */*",
            "Accept-Encoding: gzip, deflate, br",
            "Accept-Language: zh-CN,zh;q=0.9",
            "Connection: keep-alive",
            "Cookie: BAIDUID=1F6A837B1730B3CCB9E65C1757365239:FG=1; BIDUPSID=1F6A837B1730B3CCB9E65C1757365239; PSTM=1525403957;" +
                    " BDSFRCVID=2yDsJeC62CX4KF7AnX41UONONe5rbsOTH6aog4EkYtCMiBS4b8qBEG0PDx8g0Kubz7pbogKK0eOTHk6P; " +
                    "H_BDCLCKID_SF=tJIJoIKKfCt3f-opMtTEh4bHjHQ2-P4XKKOLVKoX3POkeq8CDxbijMPzjPQXXT0DQmOw2hrHJ4oDqp72y5jHhPb3QfRk-UJu2mrg_n_y-lopsIJMQbAWbT8U5f5EtbOaaKviaKJHBMb1jIQMe4bK-Tr3DN8ttfK; " +
                    "H_PS_PSSID=1426_21092_26350_20929; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; PSINO=5",
//            "Host: sp0.baidu.com",
//            "Referer: https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E9%9F%B5%E8%BE%BE&oq=%25E5%259C%25A8%25E7%25BA%25BFjson%25E8%25A7%25A3%25E6%259E%2590&rsv_pq=9a8e383a00005814&rsv_t=a95ckwTybDITz1UFiVZhzeHNbZU3YtxbmjpfbEwVAcla%2BlsanfJFexyggfM&rqlang=cn&rsv_enter=1&inputT=6253&rsv_sug3=371&rsv_sug1=262&rsv_sug7=100&bs=%E5%9C%A8%E7%BA%BFjson%E8%A7%A3%E6%9E%90",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36"
    })
    @GET("asyncqury")
    Call<String> getKuaiDiTraceInfo(@QueryMap Map<String, String> map);

    @GET("index-ajaxselectcourierinfo-{pathMailNo}-yunda.html")
    Call<String> getKuaiDiInterfaceTraceInfo(@Path("pathMailNo") String pathMailNo);
    @GET
    Call<String> testCount(@Url String url);
}

