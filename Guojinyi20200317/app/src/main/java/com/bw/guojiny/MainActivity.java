package com.bw.guojiny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.guojiny.base.BaseActivity;
import com.bw.guojiny.bean.LoginBean;
import com.bw.guojiny.mvp.login.ILoginContract;
import com.bw.guojiny.mvp.login.LoginPresenterImpl;

import java.util.HashMap;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity<LoginPresenterImpl> implements ILoginContract.ILoginView {
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.editPwd)
    EditText editPwd;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    private Unbinder unbind;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //绑定
        unbind = ButterKnife.bind( this );
    }
    @OnClick({R.id.btnRegister, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                Intent intent = new Intent( MainActivity.this, RegisterActivity.class );
                startActivityForResult( intent,100 );
                break;
            case R.id.btnLogin:
                HashMap<String, String> hashMap = new HashMap<>();
                String phone = editPhone.getText().toString().trim();
                String pwd = editPwd.getText().toString().trim();
                hashMap.put( "phone", "15621273708" );
                hashMap.put( "pwd", "gjy2000" );
                presenter.loginUser( hashMap );
                break;
        }
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (data != null) {
            String phone = data.getStringExtra( "phone" );
            String pwd = data.getStringExtra( "pwd" );
            editPhone.setText( phone );
            editPwd.setText( pwd );
        }
    }

    @Override
    protected LoginPresenterImpl initPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    public void onSuccess(LoginBean bean) {

    }

    @Override
    public void onFailed(String error) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbind != null) {
            unbind.unbind();
        }
    }
}
