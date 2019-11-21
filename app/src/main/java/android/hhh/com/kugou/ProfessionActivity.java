package android.hhh.com.kugou;

import android.hhh.com.kugou.xiongli.adapters.ProfessionAdapter;
import android.hhh.com.kugou.xiongli.beans.Data;
import android.hhh.com.kugou.xiongli.beans.ItemBean;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ProfessionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProfessionAdapter mAdapter;
    private String[] texts;
    private ArrayList<ItemBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);
        recyclerView = (RecyclerView) findViewById(R.id.rv_profession);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //准备数据
        initData();
    }

    //用于初始化数据
    private void initData() {
        mData = new ArrayList<>();
        //创建数据
        for (int i = 0; i < Data.titles.length; i++) {
            ItemBean data = new ItemBean();
            data.title = Data.titles[i];
            data.icon = R.drawable.ic_selected_profession;
            mData.add(data);
        }
        //RecycleView需要设置样式，其实就是设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //创建适配器
        ProfessionAdapter adapter = new ProfessionAdapter(mData);
        recyclerView.setAdapter(adapter);
    }
}
