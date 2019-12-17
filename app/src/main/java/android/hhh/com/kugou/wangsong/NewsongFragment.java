package android.hhh.com.kugou.wangsong;


import android.hhh.com.kugou.yu.SongInfo;
import android.hhh.com.kugou.yu.SongInfoService;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.hhh.com.kugou.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsongFragment extends Fragment {
    private View mView;
    private InputStream is;
    private List<SongInfo> songInfos;
    private List<MusicList> songLists = new ArrayList<>();
    private RecyclerView mRvSearch;

    public NewsongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_newsong, container, false);
        mRvSearch=mView.findViewById(R.id.rv_music);
        getSearch(mView);//歌曲信息

        return mView;
    }

    public void getSearch(View v){
        try {
            is = getContext().getAssets().open("music.json");
            songInfos = SongInfoService.getInfosFromJson(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i<songInfos.size(); i++){
            MusicList musicList = new MusicList(songInfos.get(i).getSongName(), songInfos.get(i).getAuthorName(),songInfos.get(i).getDuration());
            songLists.add(musicList);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());//activity中使用this,fragment中使用getContext()
        mRvSearch.setLayoutManager(layoutManager);
        MusicListAdapter iconAdapter = new MusicListAdapter(songLists);
        mRvSearch.setAdapter(iconAdapter);
    }
}
