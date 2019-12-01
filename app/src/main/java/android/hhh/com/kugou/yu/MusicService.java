package android.hhh.com.kugou.yu;


import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hhh.com.kugou.MainActivity;
import android.media.AudioManager;
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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2019/11/27 0027.
 */

public class MusicService extends Service {
    private static final  String TAG="MusicService";
    public MediaPlayer mediaPlayer;
    private int point=0;
    private InputStream is;
    private List<SongInfo> songInfos;
    private Messenger mActivityMessenger;
    private Messenger mServiceMessenger;



    public MusicService() throws IOException {
    }
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //处理消息
            Bundle bundle=msg.getData();
            //获取歌曲长度和当前播放位置，并设置到进度条上
            //设置歌名 歌手名
            //System.out.print(binder.toString());
            msg.replyTo=mActivityMessenger;

        }
    };

    @Override
    public IBinder onBind(Intent intent){
        Messenger messenger=new Messenger(handler);
        return messenger.getBinder();
    }
    public class MyBinder extends Binder{
        public List<SongInfo> getSongInfos() {
            return songInfos;
        }
        public int getPoint() {
            return point;
        }
        public SongInfo getTheSongInfo() throws IOException {
            Log.v("musicService","获取数据成功"+":"+songInfos.get(point).getSongName());
            sendMes();
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
            sendMes();
        }

        public void last() {
            point = point == 0 ? songInfos.size() - 1 : point - 1;
            play();
            sendMes();
        }
        public void seekPlayProgress(){
     /*1.获取当前歌曲的总时长*/
            final int duration=mediaPlayer.getDuration();

            //计时器对象
            final Timer timer=new Timer();
            final TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    //开启线程定时获取当前播放进度
                    int currentposition=mediaPlayer.getCurrentPosition();
                    //利用message给主线程发消息更新seekbar进度
                    Message ms=Message.obtain();
                    Bundle bundle=new Bundle();
                    bundle.putInt("duration",duration);
                    Log.i("tag","歌曲总长度"+duration);
                    bundle.putInt("currentposition",currentposition);
                    Log.i("tag","当前长度"+currentposition);
                    //设置发送的消息内容
                    ms.setData(bundle);
                    //发送消息

                    handler.sendMessage(ms);
                }
            };
            timer.schedule(task,300,500);
            //当播放结束时停止播放
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.i("tag","取消计时任务");
                    timer.cancel();
                    task.cancel();
                }
            });

        }
        public void stop() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
        }
        public void sendMes(){
            Message ms=Message.obtain();
            Bundle bundle=new Bundle();
            bundle.putString("songName",songInfos.get(point).getSongName());
            bundle.putString("authorName",songInfos.get(point).getAuthorName());
            bundle.putString("backgrondImage",songInfos.get(point).getBackgroundImagePath());
            bundle.putString("backgAlbumImage",songInfos.get(point).getAlbumImagePath());
            ms.setData(bundle);
            ms.what=1;
            try {
                mActivityMessenger.send(ms);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onCreate(){
        super.onCreate();
        try {
            is=this.getAssets().open("music.json");
            songInfos=SongInfoService.getInfosFromJson(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("MusicService","创建服务");
        Log.v("point+songName",point+","+songInfos.get(point).getSongName()+","+songInfos.get(point).getAuthorName()+","+songInfos.get(point).getBackgroundImagePath()+","+songInfos.get(point).getAlbumImagePath()+","+songInfos.get(point).getAudioFilePath());
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
