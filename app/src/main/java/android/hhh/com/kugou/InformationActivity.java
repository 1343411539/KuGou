package android.hhh.com.kugou;

import android.hhh.com.kugou.xiongli.DynamicFragment;
import android.hhh.com.kugou.xiongli.InformationFragment;
import android.hhh.com.kugou.xiongli.MusicFragment;
import android.hhh.com.kugou.xiongli.SupermanFragment;
import android.hhh.com.kugou.xiongli.adapters.InfoAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private InfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        initView();
        initAdapter();
    }

    private void initView(){
        mTabLayout = (TabLayout) findViewById(R.id.tab_info);
        viewPager = (ViewPager) findViewById(R.id.vp_info);
    }

    private void initAdapter() {
        //构造适配器
        fragments = new ArrayList<Fragment>();
        fragments.add(new DynamicFragment());
        fragments.add(new MusicFragment());
        fragments.add(new InformationFragment());
        fragments.add(new SupermanFragment());
        adapter = new InfoAdapter(getSupportFragmentManager(), fragments);
        //设定适配器
        viewPager.setAdapter(adapter);
        //将TabLayout与ViewPager关联起来
        mTabLayout.setupWithViewPager(viewPager);
    }
}
