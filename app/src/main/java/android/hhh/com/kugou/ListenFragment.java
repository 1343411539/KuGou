package android.hhh.com.kugou;

import android.content.Context;
import android.content.Intent;

import android.hhh.com.kugou.wangsong.LivebroadcastFragment;
import android.hhh.com.kugou.wangsong.Love;

import android.hhh.com.kugou.wangsong.MusicLibrary;
import android.hhh.com.kugou.wangsong.MusiclistFragment;
import android.hhh.com.kugou.wangsong.NavigationBar;
import android.hhh.com.kugou.wangsong.NewsongFragment;
import android.hhh.com.kugou.wangsong.ShortvideoFragment;
import android.hhh.com.kugou.wangsong.SongSheet;

import android.hhh.com.kugou.wangsong.VideoFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;


import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2019/11/17 0017.
 */
public class ListenFragment extends android.support.v4.app.Fragment {
    private View mView;
    private Banner banner;
    private ImageButton musiclibrary_ib, songsheet_ib, love_ib;
    private NavigationBar adapter;
    private List<Fragment> fragments;
    private TabLayout mTabLayout;
    private ViewPager viewPager;

    public ListenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_listen, null);
        setView();//轮转播放图片添加

        initView();//按钮点击事件

        initAdapter();
        return mView;
    }

    public void setView() {
        banner = mView.findViewById(R.id.banner);
        ArrayList<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.background1);
        imgs.add(R.drawable.background2);
        imgs.add(R.drawable.background3);
        imgs.add(R.drawable.background4);
        imgs.add(R.drawable.background5);

        ArrayList<String> title = new ArrayList<>();
        title.add("芒种");
        title.add("Gaints");
        title.add("天气之子");
        title.add("最美的太阳");
        title.add("Good Time");

        //设置内置样式.
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyImageLoader());
        //设置图片的集合
        banner.setImages(imgs);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(title);
        //设置轮播间隔时间
        banner.setDelayTime(5000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置。
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                String url = null;
                switch (position) {
                    case 0:
                        url = "https://www.kugou.com/mvweb/html/mv_1462149.html";
                        break;
                    case 1:
                        url = "https://www.kugou.com/mvweb/html/mv_612033.html";
                        break;
                    case 2:
                        url = "https://www.kugou.com/mvweb/html/mv_1519481.html";
                        break;
                    case 3:
                        url = "https://www.kugou.com/mvweb/html/mv_597709.html";
                        break;
                    case 4:
                        url = "https://www.kugou.com/mvweb/html/mv_592191.html";
                        break;
                }
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        //必须最后调用的方法，启动轮播图。
        banner.start();
    }


    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(Integer.parseInt(path.toString()));
        }
    }


    private void initView() {
        musiclibrary_ib = mView.findViewById(R.id.musiclibrary_ib);
        musiclibrary_ib.setOnClickListener(new ButtonListener());
        songsheet_ib = mView.findViewById(R.id.songsheet_ib);
        songsheet_ib.setOnClickListener(new ButtonListener());
        love_ib = mView.findViewById(R.id.love_ib);
        love_ib.setOnClickListener(new ButtonListener());
        mTabLayout = mView.findViewById(R.id.tabLayout_music);
        viewPager = mView.findViewById(R.id.viewpager);

    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.musiclibrary_ib:
                    startActivity(new Intent(getActivity(), MusicLibrary.class));
                    break;
                case R.id.songsheet_ib:
                    startActivity(new Intent(getActivity(), SongSheet.class));
                    break;
                case R.id.love_ib:
                    startActivity(new Intent(getActivity(), Love.class));
                    break;

                default:
                    break;
            }
        }
    }

    private void initAdapter() {
        //构造适配器
        fragments = new ArrayList<Fragment>();
        fragments.add(new NewsongFragment());
        fragments.add(new LivebroadcastFragment());
        fragments.add(new MusiclistFragment());
        fragments.add(new VideoFragment());
        fragments.add(new ShortvideoFragment());
        adapter = new NavigationBar(getChildFragmentManager(), fragments);
        //设定适配器
        viewPager.setAdapter(adapter);
        //将TabLayout与ViewPager关联起来
        mTabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);//预加载页面数
    }
}