package com.bw.guojiny.mvp.register;

import com.bw.guojiny.base.IBaseView;
import com.bw.guojiny.bean.ReigsterBean;

import java.util.Map;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:05
 * @Description: 用途：完成特定功能
 */
public interface IRegisterContract {
    interface IRegisterView extends IBaseView{
        void onSuccess(ReigsterBean bean);
        void onFailed(String error);
    }
    interface IRegisterModule{
        void RegisterUser(Map<String, String> param, ModuleCallBack moduleCallBack);
        interface ModuleCallBack{
            void onSuccess(ReigsterBean bean);
            void onFailed(String error);
        }
    }
    interface IRegisterPresenter{
        void RegisterUser(Map<String, String> param);
    }
}
