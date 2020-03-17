package com.bw.guojiny.mvp.login;

import com.bw.guojiny.base.IBaseView;
import com.bw.guojiny.bean.LoginBean;

import java.util.Map;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:05
 * @Description: 用途：完成特定功能
 */
public interface ILoginContract {
    interface ILoginView extends IBaseView{
        void onSuccess(LoginBean bean);
        void onFailed(String error);
    }
    interface ILoginModule{
        void loginUser(Map<String,String> param,ModuleCallBack moduleCallBack);
        interface ModuleCallBack{
            void onSuccess(LoginBean bean);
            void onFailed(String error);
        }
    }
    interface ILoginPresenter{
        void loginUser(Map<String,String> param);
    }
}
