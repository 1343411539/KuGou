package android.hhh.com.kugou.yu;


import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2019/11/27 0027.
 */

public class MusicService extends Service {
    private static final  String TAG="MusicService";
    public MediaPlayer mediaPlayer;
    private int point;
    private List<SongInfo> songInfos;

    MusicService() throws IOException {
        InputStream is=this.getAssets().open("music.json");
        songInfos=SongInfoService.getInfosFromJson(is);
        point=0;
        System.out.println("获取数据成功"+":"+songInfos.get(point));

    }
    @Override
    public IBinder onBind(Intent intent){
        return new MyBinder();
    }
    public class MyBinder extends Binder{
        public List<SongInfo> getSongInfos() {
            return songInfos;
        }
        public int getPoint() {
            return point;
        }
        public SongInfo getTheSongInfo() throws IOException {
            Log.v("musicService","获取数据成功"+":"+songInfos.get(point));
            return songInfos.get(point);
        }
        //播放音乐
        public void play(){
            try{
                if (mediaPlayer==null){
                    //创建一个Mediaplayer播放器
                    mediaPlayer=new MediaPlayer();
                    //指定参数为音频文件
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    //指定播放路径
                    mediaPlayer.setDataSource(songInfos.get(point).getAudioFilePath());
                    //准备播放
                    mediaPlayer.prepare();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                        public void onPrepared(MediaPlayer mp){
                            //开始播放
                            mediaPlayer.start();
                        }
                    });
                }else if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                    this.pause();

                }
                else if(mediaPlayer!=null&&!mediaPlayer.isPlaying()){
                    int position = getCurrentProgress();
                    mediaPlayer.seekTo(position);
                    try{
                        mediaPlayer.prepare();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    mediaPlayer.start();

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void pause(){
            if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                mediaPlayer.pause();//暂停播放
            }else if (mediaPlayer!=null&&(!mediaPlayer.isPlaying())){
                mediaPlayer.start();
            }
        }
        public void next() {
            point = point == songInfos.size() - 1 ? 0 : point + 1;
            play();
        }

        public void last() {
            point = point == 0 ? songInfos.size() - 1 : point - 1;
            play();
        }
        public void stop() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
        }
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("MusicService","创建服务");
    }




    //获取当前进度
    public int getCurrentProgress(){
        if (mediaPlayer!=null&mediaPlayer.isPlaying()){
            return mediaPlayer.getCurrentPosition();
        }
        else if (mediaPlayer!=null & (!mediaPlayer.isPlaying())){
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }
    public void onDestroy(){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        super.onDestroy();
    }

}
