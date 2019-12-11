package android.hhh.com.kugou.wangsong;

import android.content.ContentResolver;
import android.database.Cursor;
import android.hhh.com.kugou.R;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MusicLibrary extends AppCompatActivity implements View.OnClickListener{
    private List<MusicList> mDatas=new ArrayList<>();
    private MusicListAdapter adapter;
    private RecyclerView musicRv;
    private ImageView ivBackIcon;
    private TextView tvBackIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_library);

        initView();//初始化控件
        loadLocalMusicData();//加载本地音乐信息
    }

    private void loadLocalMusicData() {
        /* 加载本地存储当中的音乐mp3文件到集合当中*/
//        1.获取ContentResolver对象
        ContentResolver resolver = getContentResolver();
//        2.获取本地音乐存储的Uri地址
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        3 开始查询地址
        Cursor cursor = resolver.query(uri, null, null, null, null);
//        4.遍历Cursor

        while (cursor.moveToNext()) {
            String song = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
            String time = sdf.format(new Date(duration));
//            将一行当中的数据封装到对象当中
            MusicList list = new MusicList(song,singer,time);
            mDatas.add(list);
        }

//      设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        musicRv.setLayoutManager(layoutManager);
//     创建适配器对象
        adapter = new MusicListAdapter(mDatas);
        musicRv.setAdapter(adapter);
//      数据源变化，提示适配器更新
        adapter.notifyDataSetChanged();
    }

    public void initView(){
        musicRv = (RecyclerView) findViewById(R.id.local_music_rv);
        ivBackIcon = (ImageView) findViewById(R.id.iv_backIcon);
        tvBackIcon = (TextView) findViewById(R.id.tv_backIcon);

        ivBackIcon.setOnClickListener(this);
        tvBackIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

}
