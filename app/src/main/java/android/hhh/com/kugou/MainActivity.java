package android.hhh.com.kugou;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;


//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;
//import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements  View.OnClickListener{
    private LinearLayout menuLayout;
    private FrameLayout searchLayout;
    private List<Fragment> fragmentList;
    private ViewPager vp;
    private Button meBtn;
    private Button listenBtn;
    private Button lookBtn;
    private ImageButton musicPlayBtn;
    private CircleImageView circleImageView;
    private Intent intent1;
    private  myConn conn;
    MusicService.MyBinder binder;
    private TextView songNameTV;
    private TextView authorNameTV;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定服务 获取音乐
        conn =new myConn();
        intent1 =new Intent(this,MusicService.class);
        bindService(intent1,conn,BIND_AUTO_CREATE);
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
        //设置歌名 歌手名
        try {
            System.out.print(binder.toString());
            songNameTV.setText(binder.getTheSongInfo().getSongName());
            authorNameTV.setText(binder.getTheSongInfo().getAuthorName());
            //设置圆图
            Bitmap bitmap= BitmapFactory.decodeFile(binder.getTheSongInfo().getAlbumImagePath());
            Drawable fengmianDrawable = new BitmapDrawable(getResources(), bitmap);
            circleImageView.setImageDrawable(fengmianDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }



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
                intent.putExtra("binder",new Gson().toJson(binder));
                startActivity(intent);
                break;
            case R.id.musicplay_IBtn:
                try {
                    if(!TextUtils.isEmpty(binder.getTheSongInfo().getAudioFilePath())){
                        if(musicPlayBtn.getDrawable()==getResources().getDrawable(R.drawable.ic_musicplay))
                            musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop));
                        else
                            musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_musicplay));
                        binder.play();
                    }else {
                        Toast.makeText(this,"找不到音乐文件",Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case  R.id.nextsong_btn:
                binder.next();
                break;
            default:
                break;
        }
    }

    private class myConn implements ServiceConnection{
        public void onServiceConnected(ComponentName name, IBinder service){
            binder=(MusicService.MyBinder)service;
            Log.i("MainActivity","服务成功绑定，内存地址为："+binder.toString());
        }
        public void onServiceDisconnected(ComponentName name){
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
}
