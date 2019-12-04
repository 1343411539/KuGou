package android.hhh.com.kugou;

import android.content.Intent;
import android.hhh.com.kugou.xiongli.adapters.InfoAdapter;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private InfoAdapter adapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView nickname, editInfo, kuGouID, id, tv_dianZan;
    private ImageView QRCode;
    private LinearLayout ll_dianZan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        initView();
        initAdapter();
        setOnclick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_editInfo:
                Intent intent = new Intent(InformationActivity.this, EditInfoActivity.class);
                startActivity(intent);
        }
    }

    private void setOnclick(){
        editInfo.setOnClickListener(this);
    }
    private void initView(){
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.vp);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.CollapsingToolbarLayout);
        nickname = (TextView) findViewById(R.id.tv_nickname);
        kuGouID = (TextView) findViewById(R.id.tv_kuGouID);
        id = (TextView) findViewById(R.id.tv_ID);
        QRCode = (ImageView) findViewById(R.id.iv_QR_code);
        editInfo = (TextView) findViewById(R.id.tv_editInfo);
        ll_dianZan = (LinearLayout) findViewById(R.id.ll_dianZan);
        tv_dianZan = (TextView) findViewById(R.id.tv_dianZan);
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
