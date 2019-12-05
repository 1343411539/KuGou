package android.hhh.com.kugou;

import android.content.Intent;
import android.hhh.com.kugou.huang.SearchAdapter;
import android.hhh.com.kugou.huang.SearchList;
import android.hhh.com.kugou.yu.SongInfo;
import android.hhh.com.kugou.yu.SongInfoService;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private InputStream is;
    private List<SongInfo> songInfos;
    private List<SearchList> searchLists = new ArrayList<>();
    private RecyclerView mRvSearch;
    private ImageButton ibSearch;
    private EditText etSearch;
    String info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
    }

    public void initView(){
        mRvSearch = (RecyclerView) findViewById(R.id.rv_search);
        ibSearch = (ImageButton) findViewById(R.id.ib_search);
        etSearch = (EditText) findViewById(R.id.et_search);

        ibSearch.setOnClickListener(this);
        etSearch.setOnClickListener(this);
    }

    public void getSearch(View v){
        try {
            is = this.getAssets().open("music.json");
            songInfos = SongInfoService.getInfosFromJson(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        info = etSearch.getText().toString();

        for(int i = 0; i<songInfos.size(); i++){
            if(info.equals(songInfos.get(i).getSongName())){
                SearchList searchList = new SearchList(songInfos.get(i).getSongName(), songInfos.get(i).getAuthorName());
                searchLists.add(searchList);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);//activity中使用this,fragment中使用getContext()
        mRvSearch.setLayoutManager(layoutManager);
        SearchAdapter iconAdapter = new SearchAdapter(searchLists);
        mRvSearch.setAdapter(iconAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_search:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.et_search:
                getSearch(v);
                break;
            default:
                break;
        }
    }
}
