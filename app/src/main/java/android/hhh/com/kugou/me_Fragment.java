package android.hhh.com.kugou;

import android.hhh.com.kugou.utils.ToastUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Administrator on 2019/11/17 0017.
 */
public class me_Fragment extends Fragment implements View.OnClickListener {

    private LinearLayout head, local, collect, download, recently,
            musicHub, kSong, songMenu, generalize;
    private View button;

    private void initView(){
        head = getActivity().findViewById(R.id.ll_head);
        local = getActivity().findViewById(R.id.ll_local);
        collect = getActivity().findViewById(R.id.ll_collect);
        download = getActivity().findViewById(R.id.ll_download);
        recently = getActivity().findViewById(R.id.ll_recently);
        musicHub = getActivity().findViewById(R.id.ll_music_hub);
        kSong = getActivity().findViewById(R.id.ll_k_song);
        songMenu = getActivity().findViewById(R.id.ll_create_song_menu);
        generalize = getActivity().findViewById(R.id.ll_generalize);
        button = getActivity().findViewById(R.id.btn_user_defined);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);//false代表不用添加到根目录去
        return view;
    }

    //通过getActivity()获得Fragment依附的Activity对象
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化控件
        initView();
        // 添加监听
        setOnclick();
    }

    private void setOnclick() {
        head.setOnClickListener(this);
        local.setOnClickListener(this);
        collect.setOnClickListener(this);
        download.setOnClickListener(this);
        recently.setOnClickListener(this);
        musicHub.setOnClickListener(this);
        kSong.setOnClickListener(this);
        songMenu.setOnClickListener(this);
        generalize.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_head:
                break;
            case R.id.ll_local:
                break;
            case R.id.ll_collect:
                break;
            case R.id.ll_download:
                break;
            case R.id.ll_recently:
                break;
            case R.id.ll_music_hub:
                break;
            case R.id.ll_k_song:
                break;
            case R.id.ll_create_song_menu:
                break;
            case R.id.ll_generalize:
                break;
            case R.id.btn_user_defined:
                ToastUtils.showLong(getActivity(), "hello world!");
                break;
            default:
                break;
        }
    }
}
