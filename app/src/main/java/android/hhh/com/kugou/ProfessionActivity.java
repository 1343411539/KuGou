package android.hhh.com.kugou;

import android.hhh.com.kugou.xiongli.adapters.ProfessionAdapter;
import android.hhh.com.kugou.xiongli.beans.Data;
import android.hhh.com.kugou.xiongli.beans.ItemBean;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ProfessionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProfessionAdapter mAdapter;
    private String[] texts;
    private ArrayList<ItemBean> mData;
    private Toolbar navigationIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);
        initView();
        navigationIcon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initData();
    }

    //用于初始化数据
    private void initData() {
        mData = new ArrayList<>();
        //创建数据
        for (int i = 0; i < Data.titles.length; i++) {
            ItemBean data = new ItemBean();
            data.title = Data.titles[i];
            data.icon = 0;
            mData.add(data);
        }
        //RecycleView需要设置样式，其实就是设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //创建适配器
        ProfessionAdapter adapter = new ProfessionAdapter(mData);
        recyclerView.setAdapter(adapter);
    }

    //用于初始化控件
    private void initView() {
        navigationIcon = (Toolbar) findViewById(R.id.toolbar_profession);
        recyclerView = (RecyclerView) findViewById(R.id.rv_profession);
    }
}
