package com.bw.guojiny.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bw.guojiny.App;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 8:54
 * @Description: 用途：完成特定功能
 */
public class RetrofitUtil {
    private static final String TAG = "RetrofitUtil";
    //单例模式
    private static RetrofitUtil instance;
    private final OkHttpClient okhttp;
    private final Retrofit retrofit;

    private RetrofitUtil(){
        okhttp = new OkHttpClient.Builder()
                .connectTimeout( 5, TimeUnit.SECONDS )
                .readTimeout( 5, TimeUnit.SECONDS )
                .writeTimeout( 5, TimeUnit.SECONDS )
                .addInterceptor( new HttpLoggingInterceptor().setLevel( HttpLoggingInterceptor.Level.BODY ) )
                .addInterceptor( new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder = request.newBuilder();
                        SpUtil instance = SpUtil.getInstance();
                        String userId = instance.getData( "userId" );
                        if (!TextUtils.isEmpty( userId )) {
                            builder.addHeader( "userId",userId );
                        }
                        String sessionId = instance.getData( "sessionId" );
                        if (!TextUtils.isEmpty( sessionId )) {
                            builder.addHeader( "sessionId",sessionId );
                        }
                        Request newbuild = builder.build();
                        return chain.proceed( newbuild );
                    }
                } )
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl( "http://mobile.bwstudent.com/small/" )
                .client( okhttp )
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .build();
    }
    public static RetrofitUtil getInstance(){
        if (instance == null) {
            synchronized (RetrofitUtil.class){
                if (instance == null) {
                    instance = new RetrofitUtil();
                }
            }
        }
        return instance;
    }

    public ApiService createService(){
        //判断网络信息
        boolean inter = getInter();
        if(!inter){
            Log.i( TAG, "createService: 当前无网络" );
            return null;
        }
        ApiService apiService = retrofit.create( ApiService.class );
        return apiService;
    }
    //判断网络
    public boolean getInter(){
      ConnectivityManager connectivityManager = (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //返回网络
        if (activeNetworkInfo != null) {
            boolean available = activeNetworkInfo.isAvailable();
            return available;
        }
        return false;
    }
}
