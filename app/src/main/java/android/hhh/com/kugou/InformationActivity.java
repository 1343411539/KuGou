package android.hhh.com.kugou;

import android.content.Intent;
import android.hhh.com.kugou.xiongli.adapters.InfoAdapter;
import android.hhh.com.kugou.xiongli.utils.CopyContentUtil;
import android.hhh.com.kugou.xiongli.utils.ExitApplicationUtil;
import android.hhh.com.kugou.xiongli.utils.ToastUtils;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private InfoAdapter adapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView nickname, editInfo, kuGouID, id, tv_dianZan, privacy, exit;
    private ImageView QRCode;
    private LinearLayout ll_dianZan;
    private Toolbar toolbarInfo;
    private Intent intent;
    private TextView sex;
    private ImageView iconSex;

    int amount = 0;// 设置点赞数量的默认值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        //将这个activity放入list中，用于后面直接退出程序
        ExitApplicationUtil.getInstance().addActivity(this);

        initView();
        initAdapter();

        //设置ToolBar返回键的点击事件
        toolbarInfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置酷狗ID的长按事件，实现长按复制ID
        id.setOnLongClickListener(new View.OnLongClickListener() {
            private CopyContentUtil copy;

            @Override
            public boolean onLongClick(View v) {
                copy = new CopyContentUtil(getApplicationContext(), kuGouID, id);
                copy.init();
                return true;
            }
        });
        setOnclick();
/*        if(String.valueOf(sex.getText()).equals("男")){
            iconSex.setImageResource(R.drawable.ic_sex_man);
        }else if(String.valueOf(sex.getText()).equals("女")){
            iconSex.setImageResource(R.drawable.ic_sex_woman);
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_info:
                finish();
                break;
            case R.id.tv_privacy:
                ToastUtils.showShort(InformationActivity.this, "点击账号隐私");
                break;
            case R.id.tv_exit:
                ExitApplicationUtil.getInstance().exit();
                break;
            case R.id.iv_QR_code:
                intent = new Intent(InformationActivity.this, QRCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_editInfo:
                intent = new Intent(InformationActivity.this, EditInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_dianZan:
                tv_dianZan.setText(++amount + "人赞过");
        }
    }

    private void setOnclick() {
        privacy.setOnClickListener(this);
        exit.setOnClickListener(this);
        QRCode.setOnClickListener(this);
        editInfo.setOnClickListener(this);
        ll_dianZan.setOnClickListener(this);
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.vp);
        toolbarInfo = (Toolbar) findViewById(R.id.toolbar_info);
        privacy = (TextView) findViewById(R.id.tv_privacy);
        exit = (TextView) findViewById(R.id.tv_exit);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.CollapsingToolbarLayout);
        nickname = (TextView) findViewById(R.id.tv_nickname);
        kuGouID = (TextView) findViewById(R.id.tv_kuGouID);
        id = (TextView) findViewById(R.id.tv_ID);
        QRCode = (ImageView) findViewById(R.id.iv_QR_code);
        editInfo = (TextView) findViewById(R.id.tv_editInfo);
        ll_dianZan = (LinearLayout) findViewById(R.id.ll_dianZan);
        tv_dianZan = (TextView) findViewById(R.id.tv_dianZan);
        tv_dianZan.setText(amount + "人赞过");
        iconSex = (ImageView) findViewById(R.id.iv_selected_sex);
        sex = (TextView) findViewById(R.id.tv_editInfo_sex);
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
