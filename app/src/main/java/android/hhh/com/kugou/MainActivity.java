package android.hhh.com.kugou;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.view.View;




import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.hhh.com.kugou.yu.*;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;
//import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements  View.OnClickListener,MusicService.Callback{
    private LinearLayout menuLayout;
    private FrameLayout searchLayout;
    private List<Fragment> fragmentList;
    private ViewPager vp;
    private Button meBtn;
    private Button listenBtn;
    private Button lookBtn;
    private ImageButton musicPlayBtn;
    private  CircleImageView circleImageView;
    private Intent intent1;
    private  myConn conn;
    private MusicService service1;
    MusicService.MyBinder binder;
    private  TextView songNameTV;
    private  TextView authorNameTV;
    private static SeekBar seekBar;
    private InputStream is;
    private Bitmap bitmap;
    private Timer timer;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定服;
        Log.v("hhhhhh","运行到这里了");
        conn =new myConn();
        intent1 =new Intent(this,MusicService.class);
        bindService(intent1,conn, BIND_AUTO_CREATE);
        Log.v("hhhh","绑定服务？？");

        //动态设置 menuLayout和searchLayout 的大小
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        menuLayout = (LinearLayout) findViewById(R.id.menu_layout);
        searchLayout = (FrameLayout) findViewById(R.id.search_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, screenHeight / 12);
        menuLayout.setLayoutParams(params);
        searchLayout.setLayoutParams(params);

        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new MeFragment());
        fragments.add(new ListenFragment());
        fragments.add(new LookFragment());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);
        //设定适配器
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(adapter);

        seekBar=(SeekBar)findViewById(R.id.playSeekBar);
        meBtn =(Button)findViewById(R.id.me_btn);
        listenBtn=(Button)findViewById(R.id.listen_btn);
        lookBtn =(Button)findViewById(R.id.look_btn);
        circleImageView=(CircleImageView)findViewById(R.id.circle_image);
        musicPlayBtn=(ImageButton)findViewById(R.id.musicplay_IBtn);
        songNameTV=(TextView)findViewById(R.id.songName_TV);
        authorNameTV=(TextView)findViewById(R.id.authorName_TV);

        meBtn.setTextSize(30);
        meBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        meBtn.setTextColor(Color.WHITE);

        musicPlayBtn.setOnClickListener(this);
        meBtn.setOnClickListener(this);
        listenBtn.setOnClickListener(this);
        lookBtn.setOnClickListener(this);
        circleImageView.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //页面滑动事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                meBtn.setTextSize(26);
                meBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                meBtn.setTextColor(Color.parseColor("#1296db"));
                listenBtn.setTextSize(26);
                listenBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                listenBtn.setTextColor(Color.parseColor("#1296db"));
                lookBtn.setTextSize(26);
                lookBtn.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                lookBtn.setTextColor(Color.parseColor("#1296db"));
                if(position==0){
                    meBtn.setTextSize(30);
                    meBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    meBtn.setTextColor(Color.WHITE);
                }
                else if(position==1){
                    listenBtn.setTextSize(30);
                    listenBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    listenBtn.setTextColor(Color.WHITE);
                }
                else if(position==2){
                    lookBtn.setTextSize(30);
                    lookBtn.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    lookBtn.setTextColor(Color.WHITE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        findViewById(R.id.musicplay_IBtn).setOnClickListener(this);
        findViewById(R.id.nextsong_btn).setOnClickListener(this);

    }
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
    public  void  onClick(View v){
        switch (v.getId()){
            case R.id.me_btn:
                meBtn.setTextColor(Color.WHITE);
                vp.setCurrentItem(0);
                break;
            case R.id.listen_btn:
                vp.setCurrentItem(1);
                break;
            case R.id.look_btn:
                vp.setCurrentItem(2);
                break;
            case R.id.circle_image:
                Intent intent=new Intent(this,MusicPlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("binder", binder);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.musicplay_IBtn:
                try {
                    if(!TextUtils.isEmpty(binder.getTheSongInfo().getAudioFilePath())){
                        if(binder.getMediaPlayer()==null) {
                            musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_black));
                            binder.play();
                            seekPlayProgress();
                            Log.v("click","你点击了播放");
                        }
                        else if (binder.getMediaPlayer()!=null&&binder.isPlaying()==false){
                            musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_black));
                            binder.goPlay();
                            seekPlayProgress();
                            Log.v("click","你点击了播放");
                        }
                        else{
                            musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_musicplay_black));
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
                seekPlayProgress();
                reflashData();
                musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_black));
                break;
            default:
                break;
        }
    }
    public void reflashData(){
        try {
            Log.v("图片路径",binder.getTheSongInfo().getAlbumImagePath());
            songNameTV.setText(binder.getTheSongInfo().getSongName());
            authorNameTV.setText(binder.getTheSongInfo().getAuthorName());
            //设置圆图
            is=getClass().getResourceAsStream(binder.getTheSongInfo().getAlbumImagePath());
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
            Drawable fengmianDrawable = new BitmapDrawable(getResources(), bitmap);
            circleImageView.setImageDrawable(fengmianDrawable);
            if(binder.getMediaPlayer()!=null){
                if(binder.isPlaying()){
                    seekPlayProgress();
                    musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_pause_black));
                }
                else
                    musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_musicplay_black));
            }
            else
                musicPlayBtn.setImageDrawable(getDrawable(R.drawable.ic_musicplay_black));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class myConn implements ServiceConnection{
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder=(MusicService.MyBinder)service;
            service1=binder.getService();
            service1.addCallback(MainActivity.this);

            Log.v("MainActivity","服务成功绑定，内存地址为："+binder.toString());
            //mServiceMessenger = new Messenger(binder);
            //mActivityMessenger=new Messenger(handler);
            reflashData();
        }
        public void onServiceDisconnected(ComponentName name){
            service1=null;
        }
    }


    protected  void onDestroy(){
        unbindService(conn);
        super.onDestroy();
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }*/
    /*@Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }*/

   /* @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }*/

    public class FragAdapter extends FragmentPagerAdapter {
        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentList.get(arg0);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        if(binder!=null)
            reflashData();
    }
    @Override
    public void onResume(){
        super.onResume();
        if(binder!=null)
            reflashData();
    }

}
