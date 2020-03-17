package com.bw.guojiny.mvp.login;

import com.bw.guojiny.bean.LoginBean;
import com.bw.guojiny.net.ApiService;
import com.bw.guojiny.net.RetrofitUtil;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:07
 * @Description: 用途：完成特定功能
 */
public class LoginModuleImpl implements ILoginContract.ILoginModule {
    @Override
    public void loginUser(Map<String, String> param, ModuleCallBack moduleCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        if (service != null) {
            Observable<LoginBean> observable = service.loginUser( param );
            observable.subscribeOn( Schedulers.io() )
                    .observeOn( AndroidSchedulers.mainThread() )
                    .subscribe( new Observer<LoginBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBean value) {
                            moduleCallBack.onSuccess( value );
                        }

                        @Override
                        public void onError(Throwable e) {
                            moduleCallBack.onFailed( e.getMessage() );
                        }

                        @Override
                        public void onComplete() {

                        }
                    } );
        }
    }
}
