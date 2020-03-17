package com.bw.guojiny.base;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 8:49
 * @Description: 用途：完成特定功能
 */
public abstract class BasePresenter<view> {
    protected view baseView;

    public BasePresenter() {
        initModule();
    }

    protected void attachView(view v){
        this.baseView = v;
    }
    protected void detachView(){
        if (baseView != null) {
            baseView = null;
        }
    }
    protected abstract void initModule();
}
