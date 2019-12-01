package android.hhh.com.kugou.yu;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hhh.com.kugou.MainActivity;
import android.hhh.com.kugou.R;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class MusicPlayActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView roundAlphaImage;
    private TextView songNameTV;
    private TextView authorNameTV;
    private int point;
    private List<SongInfo> songInfos;
    private ImageButton lastSongBtn;
    private ImageButton musicPlayBtn;
    private ImageButton nextSongBtn;
    private ImageButton backIBtn;
    private Intent intent1;
    private myConn conn;
    private LinearLayout activityMusicPlay;
    MusicService.MyBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        //设置半透明
        roundAlphaImage=(ImageView)findViewById(R.id.round_alpha_image);
        roundAlphaImage.setAlpha(150);
        //获取数据
        String binderJson=getIntent().getStringExtra("binder");
        binder=new Gson().fromJson(binderJson,MusicService.MyBinder.class);
        //
        lastSongBtn=(ImageButton)findViewById(R.id.lastsong_btn);
        musicPlayBtn=(ImageButton)findViewById(R.id.musicplay_IBtn);
        nextSongBtn=(ImageButton)findViewById(R.id.nextsong_btn);
        backIBtn=(ImageButton)findViewById(R.id.back_IBtn);
        activityMusicPlay=(LinearLayout)findViewById(R.id.activity_music_play);


        /*Bitmap bitmap= null;
        try {
        bitmap = BitmapFactory.decodeFile(binder.getTheSongInfo().getBackgroundImagePath());
        Drawable bacgroundDrawable = new BitmapDrawable(getResources(), bitmap);
        activityMusicPlay.setBackground(bacgroundDrawable);*/

        backIBtn.setOnClickListener(this);
        lastSongBtn.setOnClickListener(this);
        musicPlayBtn.setOnClickListener(this);
        nextSongBtn.setOnClickListener(this);

        songNameTV=(TextView)findViewById(R.id.songName_TV);
        authorNameTV=(TextView)findViewById(R.id.authorName_TV);

        try {
            songNameTV.setText(binder.getTheSongInfo().getSongName());
            authorNameTV.setText(binder.getTheSongInfo().getAuthorName());
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_IBtn:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("songInfos", (Serializable) songInfos);
                intent.putExtra("point", point);
                startActivity(intent);
                break;
            case R.id.musicplay_IBtn:
                if(!TextUtils.isEmpty(binder.getSongInfos().get(binder.getPoint()).getAudioFilePath())){
                    if(musicPlayBtn.getDrawable()==getResources().getDrawable(R.drawable.ic_musicplay))
                        musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop));
                    else
                        musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_musicplay));
                    binder.play();
                }else {
                    Toast.makeText(this,"找不到音乐文件",Toast.LENGTH_SHORT).show();
                }
                break;
            case  R.id.nextsong_btn:
                binder.next();
                break;
            case R.id.lastsong_btn:
                binder.last();
            default:
                break;
        }
    }
    private class myConn implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder service){
            binder=(MusicService.MyBinder)service;
        }
        public void onServiceDisconnected(ComponentName name){
        }
    }


    protected  void onDestroy(){
        unbindService(conn);
        super.onDestroy();
    }
}
