package android.hhh.com.kugou.xiongli.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by vip98 on 2019/11/27.
 */

public class InfoAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    String[] titles = {"动态", "音乐", "资料", "超人"};

    public InfoAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
