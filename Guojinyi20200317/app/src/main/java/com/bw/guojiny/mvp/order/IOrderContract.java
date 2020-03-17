package com.bw.guojiny.mvp.order;

import com.bw.guojiny.base.IBaseView;
import com.bw.guojiny.bean.OrderBean;

import java.util.Map;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:05
 * @Description: 用途：完成特定功能
 */
public interface IOrderContract {
    interface IOrderView extends IBaseView{
        void onSuccess(OrderBean bean);
        void onFailed(String error);
    }
    interface IOrderModule{
        void getOrder(Map<String, String> param, ModuleCallBack moduleCallBack);
        interface ModuleCallBack{
            void onSuccess(OrderBean bean);
            void onFailed(String error);
        }
    }
    interface IOrderPresenter{
        void getOrder(Map<String, String> param);
    }
}
