package com.bw.guojiny.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.bw.guojiny.App;

import java.security.Key;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:33
 * @Description: 用途：完成特定功能
 */
public class SpUtil {
    private static SpUtil instance;
    private final SharedPreferences sp;
    private final SharedPreferences.Editor edit;

    private SpUtil(){
        sp = App.context.getSharedPreferences( "month", Context.MODE_PRIVATE );
        edit = sp.edit();
    }
    public static SpUtil getInstance(){
        if (instance == null) {
            instance = new SpUtil();
        }
        return instance;
    }

    //存
    public void setData(String kay,String value){
        edit.putString( kay,value );
        edit.commit();
    }
    public String getData(String key){
        String string = sp.getString( key, "" );
        return string;
    }
}
