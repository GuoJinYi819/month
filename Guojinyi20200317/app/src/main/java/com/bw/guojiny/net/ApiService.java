package com.bw.guojiny.net;

import com.bw.guojiny.bean.LoginBean;
import com.bw.guojiny.bean.OrderBean;
import com.bw.guojiny.bean.ReigsterBean;

import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 8:58
 * @Description: 用途：完成特定功能
 */
public interface ApiService {
    //登入
    @POST("user/v1/login")
    @FormUrlEncoded
    Observable<LoginBean> loginUser(@FieldMap Map<String,String> param);

    @POST("user/v1/register")
    @FormUrlEncoded
    Observable<ReigsterBean> registerUser(@FieldMap Map<String,String> param);

    @GET("order/verify/v1/findOrderListByStatus")
    Observable<OrderBean> getOrderList(@QueryMap Map<String,String> param);
}

