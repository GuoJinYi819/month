package com.bw.guojiny.mvp.register;

import com.bw.guojiny.base.BasePresenter;
import com.bw.guojiny.bean.ReigsterBean;

import java.util.Map;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:25
 * @Description: 用途：完成特定功能
 */
public class RegisterPresenterImpl extends BasePresenter<IRegisterContract.IRegisterView> implements IRegisterContract.IRegisterPresenter {
    private IRegisterModuleImpl module;
    @Override
    protected void initModule() {
        module = new IRegisterModuleImpl();
    }

    @Override
    public void RegisterUser(Map<String, String> param) {
        module.RegisterUser( param, new IRegisterContract.IRegisterModule.ModuleCallBack() {
            @Override
            public void onSuccess(ReigsterBean bean) {
                baseView.onSuccess( bean );
            }

            @Override
            public void onFailed(String error) {
                baseView.onFailed( error );
            }
        } );
    }
}
