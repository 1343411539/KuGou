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
    private SeekBar seekBar;

    private ImageButton lastSongBtn;
    private ImageButton musicPlayBtn;
    private ImageButton nextSongBtn;
    private ImageButton backIBtn;
    private LinearLayout activityMusicPlay;
    MusicService.MyBinder binder;
    private Timer timer;

    public void seekPlayProgress(){
        //计时器对象
        timer=new Timer();
        final TimerTask task=new TimerTask() {
            @Override
            public void run() {
                //开启线程定时获取当前播放进度
                int duration=binder.getService().getMusicDuration();
                int currentposition = binder.getService().getCurrentProgress();
                Log.i("tag", "当前长度" + currentposition);
                int s=binder.getService().time;
                int m=s/60;
                s=s%60;
                //timeTV.setText(m+":"+s);
                seekBar.setMax(duration);
                seekBar.setProgress(currentposition);
            }
        };
        timer.schedule(task,1000,1000);
        //当播放结束时停止播放

    }
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
        seekPlayProgress();





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
                            seekPlayProgress();
                        }
                        else if (binder.getMediaPlayer()!=null&&binder.isPlaying()==false){
                            musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_white));
                            binder.goPlay();
                            Log.v("click","你点击了播放");
                            seekPlayProgress();
                        }
                        else{
                            musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_musicplay));
                            Log.v("click","你点击了暂停");
                            timer.cancel();
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
            InputStream is=getClass().getResourceAsStream(binder.getTheSongInfo().getBackgroundImagePath());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            Drawable BackgroundDrawable = new BitmapDrawable(bitmap);

            activityMusicPlay.setBackground(BackgroundDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
