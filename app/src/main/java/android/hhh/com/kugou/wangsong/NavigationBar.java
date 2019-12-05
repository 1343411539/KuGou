package android.hhh.com.kugou.wangsong;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class NavigationBar extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    String[] titles={"新歌","直播","歌单","视频","短视频"};

    public NavigationBar(FragmentManager fm,List<Fragment>fragments){
        super(fm);
        this.fragments=fragments;
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
