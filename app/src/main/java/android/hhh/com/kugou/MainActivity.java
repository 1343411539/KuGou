package android.hhh.com.kugou;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout menuLayout;
    private LinearLayout searchLayout;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态设置 menuLayout和searchLayout 的大小
        DisplayMetrics dm=getResources().getDisplayMetrics();
        int screenWidth=dm.widthPixels;
        int screenHeight=dm.heightPixels;
        menuLayout=(LinearLayout)findViewById(R.id.menu_layout);
        searchLayout=(LinearLayout)findViewById(R.id.search_layout);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(screenWidth,screenHeight/12);
        menuLayout.setLayoutParams(params);
        searchLayout.setLayoutParams(params);
        //构造适配器
        List<Fragment> fragments= new ArrayList<Fragment>();
        fragments.add(new me_Fragment());
        fragments.add(new listen_Fragment());
        fragments.add(new look_Fragment());
        FragAdapter adapter=new FragAdapter(getSupportFragmentManager(),fragments);
        ViewPager vp=(ViewPager)findViewById(R.id.viewpager);
        vp.setAdapter(adapter);
    }
    public class FragAdapter extends FragmentPagerAdapter {
        public FragAdapter(FragmentManager fm, List<Fragment> fragments){
            super(fm);
            fragmentList=fragments;
        }
        @Override
        public Fragment getItem(int arg0){
            return fragmentList.get(arg0);
        }
        @Override
        public int getCount(){
            return fragmentList.size();
        }
    }
}
