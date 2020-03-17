package com.bw.guojiny.mvp.order;

import com.bw.guojiny.base.BasePresenter;
import com.bw.guojiny.bean.OrderBean;

import java.util.Map;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:56
 * @Description: 用途：完成特定功能
 */
public class OrderPresenterImpl extends BasePresenter<IOrderContract.IOrderView> implements IOrderContract.IOrderPresenter {
    private OrderMoudleImpl moudle;
    @Override
    protected void initModule() {
        moudle = new OrderMoudleImpl();
    }

    @Override
    public void getOrder(Map<String, String> param) {
        moudle.getOrder( param, new IOrderContract.IOrderModule.ModuleCallBack() {
            @Override
            public void onSuccess(OrderBean bean) {
                baseView.onSuccess( bean );
            }

            @Override
            public void onFailed(String error) {
                baseView.onFailed( error );
            }
        } );
    }
}
