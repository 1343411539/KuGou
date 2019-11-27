package android.hhh.com.kugou.yu;

import android.hhh.com.kugou.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class MusicPlayActivity extends AppCompatActivity {
    private ImageView roundAlphaImage;
    private TextView songNameTV;
    private TextView authorNameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        //设置半透明
        roundAlphaImage=(ImageView)findViewById(R.id.round_alpha_image);
        roundAlphaImage.setAlpha(150);

        //接收数据
        String songJson=getIntent().getStringExtra("book");
        SongInfo songInfo=new Gson().fromJson(songJson,SongInfo.class);
        //显示数据
        songNameTV=(TextView)findViewById(R.id.songName_TV);
        authorNameTV=(TextView)findViewById(R.id.authorName_TV);

        songNameTV.setText(songInfo.getSongName());
        authorNameTV.setText(songInfo.getAuthorName());


    }
}
