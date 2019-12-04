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
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView roundAlphaImage;
    private TextView songNameTV;
    private TextView authorNameTV;
    private TextView durationTV;
    private static SeekBar seekBar;

    private ImageButton lastSongBtn;
    private ImageButton musicPlayBtn;
    private ImageButton nextSongBtn;
    private ImageButton backIBtn;
    private LinearLayout activityMusicPlay;
    MusicService.MyBinder binder;

    private InputStream is;
    private Bitmap bitmap;
    private static TextView timeTV;

    public static Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //处理消息
            Bundle bundle=msg.getData();
            //获取歌曲长度和当前播放位置，并设置到进度条上
            //设置歌名 歌手名
            //System.out.print(binder.toString());
            //msg.replyTo=mServiceMessenger;
            if (msg.what==1) {

            }
            else if(msg.what==2){

                //获取歌曲长度和当前播放位置，并设置到进度条上
                int duration=bundle.getInt("duration");
                int currentposition=bundle.getInt("currentposition");
                int s=bundle.getInt("time");
                int m=s/60;
                s=s%60;
                Log.i("tag","歌曲总长度"+duration);
                Log.i("tag","当前长度"+currentposition);
                seekBar.setMax(duration);
                seekBar.setProgress(currentposition);
                timeTV.setText(m+":"+s);

            }


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        //获取数据
        Intent intent = this.getIntent();
        binder=(MusicService.MyBinder)intent.getSerializableExtra("binder");
        //设置半透明
        roundAlphaImage=(ImageView)findViewById(R.id.round_alpha_image);
        roundAlphaImage.setAlpha(150);
        //
        lastSongBtn=(ImageButton)findViewById(R.id.lastsong_btn);
        musicPlayBtn=(ImageButton)findViewById(R.id.musicplay_IBtn);
        nextSongBtn=(ImageButton)findViewById(R.id.nextsong_btn);
        backIBtn=(ImageButton)findViewById(R.id.back_IBtn);
        activityMusicPlay=(LinearLayout)findViewById(R.id.activity_music_play);
        durationTV=(TextView) findViewById(R.id.duration_TV);
        seekBar=(SeekBar)findViewById(R.id.playSeekBar);
        timeTV=(TextView)findViewById(R.id.time_TV);

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
        //
        reflashData();
        //seekPlayProgress();





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_IBtn:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.musicplay_IBtn:
                try {
                    if(!TextUtils.isEmpty(binder.getTheSongInfo().getAudioFilePath())){
                        if(binder.getMediaPlayer()==null) {
                            musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_white));
                            binder.play();
                            Log.v("click","你点击了播放");
                            //seekPlayProgress();
                        }
                        else if (binder.getMediaPlayer()!=null&&binder.isPlaying()==false){
                            musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_white));
                            binder.goPlay();
                            Log.v("click","你点击了播放");
                            //seekPlayProgress();
                        }
                        else{
                            musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_musicplay));
                            Log.v("click","你点击了暂停");
                           // timer.cancel();
                            binder.pause();

                        }
                    }else {
                        Toast.makeText(this,"找不到音乐文件",Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case  R.id.nextsong_btn:
                binder.next();
                musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_white));
                reflashData();
                break;
            case  R.id.lastsong_btn:
                binder.last();
                musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_white));
                reflashData();
            default:
                break;
        }
    }
    public void reflashData(){
        try {
            if (binder.getMediaPlayer()!=null&&binder.isPlaying())
                musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_white));
            songNameTV.setText(binder.getTheSongInfo().getSongName());
            authorNameTV.setText(binder.getTheSongInfo().getAuthorName());
            durationTV.setText(binder.getTheSongInfo().getDuration());
            //设置beijing图
            binder.MPActivityIsCreated=true;

            is=getClass().getResourceAsStream(binder.getTheSongInfo().getBackgroundImagePath());
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
            Drawable BackgroundDrawable = new BitmapDrawable(bitmap);

            activityMusicPlay.setBackground(BackgroundDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
