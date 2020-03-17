package com.bw.guojiny.adapter;

import com.bw.guojiny.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:47
 * @Description: 用途：完成特定功能
 */
public class HomeAdapter extends FragmentPagerAdapter {

    private List<Fragment> list = new ArrayList<>(  );
    private String[] arr = {"全部","待付款","待发货","待收货","待评价"};

    public HomeAdapter(@NonNull FragmentManager fm) {
        super( fm );
        list.add( OrderFragment.newInstance( 0 ) );
        list.add( OrderFragment.newInstance( 1 ) );
        list.add( OrderFragment.newInstance( 2 ) );
        list.add( OrderFragment.newInstance( 3 ) );
        list.add( OrderFragment.newInstance( 9 ) );
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get( position );
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arr[position];
    }
}
