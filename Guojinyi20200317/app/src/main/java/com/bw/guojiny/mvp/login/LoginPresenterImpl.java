package com.bw.guojiny.mvp.login;

import com.bw.guojiny.base.BasePresenter;
import com.bw.guojiny.bean.LoginBean;

import java.util.Map;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:09
 * @Description: 用途：完成特定功能
 */
public class LoginPresenterImpl extends BasePresenter<ILoginContract.ILoginView> implements ILoginContract.ILoginPresenter {
    private LoginModuleImpl module;
    @Override
    protected void initModule() {
        module = new LoginModuleImpl();
    }

    @Override
    public void loginUser(Map<String, String> param) {
        module.loginUser( param, new ILoginContract.ILoginModule.ModuleCallBack() {
            @Override
            public void onSuccess(LoginBean bean) {
                baseView.onSuccess( bean );
            }

            @Override
            public void onFailed(String error) {
                baseView.onFailed( error );
            }
        } );
    }
}
