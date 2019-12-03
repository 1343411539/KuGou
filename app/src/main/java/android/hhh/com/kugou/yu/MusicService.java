package android.hhh.com.kugou.yu;


import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;

import android.hhh.com.kugou.MainActivity;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2019/11/27 0027.
 */

public class MusicService extends Service implements Serializable{
    private static final  String TAG="MusicService";
    public MediaPlayer mediaPlayer;
    private int point;
    private InputStream is;
    private List<SongInfo> songInfos;
    private MyBinder binder;
    public int time;
    public int currentposition;


    public interface Callback {
}

    /**
     * 往回调接口集合中添加一个实现类
     * @param callback
     */
    public void addCallback(Callback callback) {
        list.add(callback);
    }

    private List<Callback> list;
    public MusicService() throws IOException {
    }
    @Override
    public void onCreate(){
        super.onCreate();
        binder=new MyBinder();
        list = new ArrayList<Callback>();
        try {
            point=0;
            is=this.getAssets().open("music.json");
            songInfos=SongInfoService.getInfosFromJson(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("MusicService","创建服务");
        Log.v("point+songName",point+","+songInfos.get(point).getSongName()+","+songInfos.get(point).getAuthorName()+","+songInfos.get(point).getBackgroundImagePath()+","+songInfos.get(point).getAlbumImagePath()+","+songInfos.get(point).getAudioFilePath());
    }

    @Override
    public IBinder onBind(Intent intent){
        return this.binder;
    }
    public class MyBinder extends Binder implements Serializable{
        public List<SongInfo> getSongInfos() {
            return songInfos;
        }
        public int getPoint() {
            return point;
        }
        public SongInfo getTheSongInfo() throws IOException {
            Log.v("point", String.valueOf(point));
            Log.v("musicService","获取数据成功"+":"+songInfos.get(point).getSongName());
            return songInfos.get(point);
        }
        public MusicService getService(){
            return MusicService.this;
        }
        //播放音乐
        public void play(){
            try{
                if (mediaPlayer==null)
                    mediaPlayer=new MediaPlayer();//创建一个Mediaplayer播放器

                mediaPlayer.reset();
                time=0;
                AssetManager assetMg =getAssets();
                    //指定播放路径
                Log.v(TAG,"更换音乐");
                AssetFileDescriptor fileDescriptor = assetMg.openFd(songInfos.get(point).getAudioFilePath());
                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                    //准备播放
                mediaPlayer.prepare();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                    public void onPrepared(MediaPlayer mp){
                            //开始播放
                            mediaPlayer.start();
                        }
                    });
                seekPlayProgress();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public  void goPlay(){
            int position = getCurrentProgress();
            mediaPlayer.seekTo(position);
            try{
                mediaPlayer.prepare();
            }catch (Exception e){
                    e.printStackTrace();
            }
            mediaPlayer.start();
            seekPlayProgress();
        }
        public void pause(){
            if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                mediaPlayer.pause();//暂停播放
            }else if (mediaPlayer!=null&&(!mediaPlayer.isPlaying())){
                mediaPlayer.start();
            }
        }
        public void next() {
            if(point == songInfos.size() - 1)
                point=0;
            else  point++;
            play();
        }

        public void last() {
            if(point == 0)
                point=songInfos.size()-1;
            else  point--;
            play();
        }
        public boolean isPlaying(){
            return mediaPlayer.isPlaying();
        }
        public MediaPlayer getMediaPlayer(){
            return mediaPlayer;
        }
        public void seekPlayProgress(){
     /*1.获取当前歌曲的总时长*/
            final int duration=getMusicDuration();

            //计时器对象
            final Timer timer=new Timer();
            final TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    //开启线程定时获取当前播放进度
                    if (mediaPlayer==null||mediaPlayer.isPlaying()==false)
                    {
                        timer.cancel();
                        Log.i("tag","取消计时任务");
                    }
                    currentposition=getCurrentProgress();
                    //利用message给主线程发消息更新seekbar进度
                    Message ms=Message.obtain();
                    Message ms2=Message.obtain();
                    Bundle bundle=new Bundle();
                    Bundle bundle2=new Bundle();
                    bundle.putInt("duration",duration);
                    bundle2.putInt("duration",duration);
                    Log.i("tag","歌曲总长度"+duration);
                    bundle.putInt("currentposition",currentposition);
                    bundle2.putInt("currentposition",currentposition);
                    Log.i("tag","当前长度"+currentposition);
                    time++;
                    bundle.putInt("time",time);
                    bundle2.putInt("time",time);
                    Log.i("tag","已播放时间"+time);
                    //设置发送的消息内容
                    ms.setData(bundle);
                    ms2.setData(bundle);
                    ms2.what=2;
                    ms.what=2;
                    //发送消息
                    MainActivity.handler.sendMessage(ms);
                    // MusicPlayActivity.handler.sendMessage(ms);

                }
            };
            timer.schedule(task,1000,1000);
            //当播放结束时停止播放

        }
        public void stop() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
        }
    }


//获取音乐长度
    public int getMusicDuration()
    {
        int rtn = 0;
        if (mediaPlayer != null)
        {
            rtn = mediaPlayer.getDuration();
        }

        return rtn;
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
