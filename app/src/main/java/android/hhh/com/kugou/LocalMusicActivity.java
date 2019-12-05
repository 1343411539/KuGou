package android.hhh.com.kugou;

import android.hhh.com.kugou.xiongli.adapters.LocalMusicAdapter;
import android.hhh.com.kugou.xiongli.beans.Song;
import android.hhh.com.kugou.xiongli.utils.MusicUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicActivity extends AppCompatActivity {
    private ListView mListView;
    private List<Song> list;
    private LocalMusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView_local);
        list = new ArrayList<>();
        //把扫描到的音乐赋值给list
        list = MusicUtil.getMusicData(this);
        adapter = new LocalMusicAdapter(this,list);
        mListView.setAdapter(adapter);
    }
}
