package com.bw.guojiny;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.guojiny.adapter.HomeAdapter;
import com.bw.guojiny.base.BaseActivity;
import com.bw.guojiny.base.BasePresenter;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomePageActivity extends BaseActivity {

    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private Unbinder unbind;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void initView() {
        unbind = ButterKnife.bind( this );
        HomeAdapter homeAdapter = new HomeAdapter( getSupportFragmentManager() );
        viewPager.setAdapter( homeAdapter );
        tabLayout.setupWithViewPager( viewPager );
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String phone = intent.getStringExtra( "phone" );
        tvPhone.setText( phone );
        String pic = intent.getStringExtra( "pic" );
        Glide.with( this ).load( pic )
                .error( R.mipmap.ic_launcher )
                .placeholder( R.mipmap.ic_launcher_round )
                .apply( RequestOptions.bitmapTransform( new CircleCrop() ) )
                .into( ivPic );
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbind != null) {
            unbind.unbind();
        }
    }
}
