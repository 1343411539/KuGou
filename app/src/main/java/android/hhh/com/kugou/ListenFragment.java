package android.hhh.com.kugou;

import android.content.Context;
import android.content.Intent;

import android.hhh.com.kugou.huang.SearchList;
import android.hhh.com.kugou.wangsong.Love;

import android.hhh.com.kugou.wangsong.MusicLibrary;
import android.hhh.com.kugou.wangsong.MusicListAdapter;
import android.hhh.com.kugou.wangsong.SongSheet;

import android.hhh.com.kugou.yu.SongInfo;
import android.hhh.com.kugou.yu.SongInfoService;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2019/11/17 0017.
 */
public class ListenFragment extends android.support.v4.app.Fragment {
    private View mView;
    private Banner banner;
    private ImageButton musiclibrary_ib, songsheet_ib, love_ib;
    private InputStream is;
    private List<SongInfo> songInfos;
    private List<SearchList> searchLists = new ArrayList<>();
    private RecyclerView mRvSearch;

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

        getSearch(mView);
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
        title.add("图1");
        title.add("图2");
        title.add("图3");
        title.add("图4");
        title.add("图5");

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
                Log.i("tag", "你点了第"+position+"张轮播图");
            }
        });

        //必须最后调用的方法，启动轮播图。
        banner .start();
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
        love_ib=mView.findViewById(R.id.love_ib);
        love_ib.setOnClickListener(new ButtonListener());
        mRvSearch=mView.findViewById(R.id.rv_music);

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
    public void getSearch(View v){
        try {
            is = getContext().getAssets().open("music.json");
            songInfos = SongInfoService.getInfosFromJson(is);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i = 0; i<songInfos.size(); i++){
            SearchList searchList = new SearchList(songInfos.get(i).getSongName(), songInfos.get(i).getAuthorName());
            searchLists.add(searchList);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());//activity中使用this,fragment中使用getContext()
        mRvSearch.setLayoutManager(layoutManager);
        MusicListAdapter iconAdapter = new MusicListAdapter(searchLists);
        mRvSearch.setAdapter(iconAdapter);
    }
}